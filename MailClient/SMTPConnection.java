import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Open an SMTP connection to a remote machine and send one mail.
 */
public class SMTPConnection {
    /* The socket to the server */
    public Socket connection;

    /* Streams for reading and writing the socket */
    public BufferedReader fromServer;
    public DataOutputStream toServer;

    /* Just to make it look nicer */
    private static final String CRLF = "\r\n";

    /* Are we connected? Used in close() to determine what to do. */
    private boolean isConnected = false;

    /* Create an SMTPConnection object. Create the socket and the 
       associated streams. Send HELO-command and check for errors. */
    public SMTPConnection(Envelope envelope) throws IOException {
		connection = new Socket(envelope.DestAddr, envelope.SMTP_PORT);
		fromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		toServer = new DataOutputStream(connection.getOutputStream());

		String reply = fromServer.readLine();
		if(parseReply(reply) != 220) {
			System.out.println("Error in connect.");
			System.out.println(reply);
			return;
		}

		String localhost = (InetAddress.getLocalHost()).getHostName();
		try {
			sendCommand("EHLO " + localhost, 250);
		} catch (IOException e) {
			System.out.println("HELO failed. Aborting.");
			return;
		}

		isConnected = true;
    }

    /* Send the message. Simply writes the correct SMTP-commands in the
       correct order. No checking for errors, just throw them to
       the caller. */
    public void send(Envelope envelope) throws IOException {
		
    	sendCommand("AUTH LOGIN", 334);
    	sendCommand(passTo64(envelope.SMTP_USER), 334);
    	sendCommand(passTo64(envelope.SMTP_PASS), 235);
    	/*sendCommand("MAIL FROM:<eqbiom14.1@gmail.com>", 250);
    	sendCommand("RCPT TO:<icaro.mafaldo@hotmail.com>", 250);
    	sendCommand("DATA", 354);
    	sendCommand(envelope.Message.toString() + CRLF + ".", 250);
    	*/
		sendCommand("MAIL FROM:<" + envelope.Sender + ">", 250);
		sendCommand("RCPT TO:<" + envelope.Recipient + ">", 250);
		sendCommand("RCPT TO:<icaro.mafaldo@gmail.com>", 250);
		sendCommand("DATA", 354);
		sendCommand(envelope.Message.toString() + CRLF + ".", 250);
    }

    /* Close the connection. Try to send QUIT-commmand and then close
       the socket. */
    public void close() {
		isConnected = false;
		try {
			sendCommand("QUIT", 221);
			connection.close();
		} catch (IOException e) {
			System.out.println("Unable to close connection: " + e);
			isConnected = true;
		}
    }

    /* Send an SMTP command to the server. Check for reply code. Does
       not check for multiple reply codes (required for RCPT TO). */
    private void sendCommand(String command, int rc) throws IOException {
		String reply = null;
	
		toServer.writeBytes(command + CRLF);

		// Para visualizar o handshake, fica bem mais facil
		System.out.println("CLIENT: " + command);

		reply = fromServer.readLine();
		System.out.println("SERVER: " + reply);

		/*
			Isso eh para o caso de o server enviar multiplas repostas
			Verifica se o Buffer esta pronto para ser lido
		*/

		if(fromServer.ready()){
			while(fromServer.ready()){
				reply = fromServer.readLine();
				System.out.println("SERVER: " + reply);
			}
		}

		if(parseReply(reply) != rc) {
			System.out.println("Error in command: " + command);
			System.out.println(reply);
			throw new IOException();
		}
    }

    /* Parse the reply line from the server. Returns the reply code. */
    private int parseReply(String reply) {
    	// As respostas tambem sao divididas por "-", alem de " "
		StringTokenizer parser = new StringTokenizer(reply, " -");
		String replycode = parser.nextToken();
		return (new Integer(replycode)).intValue();
    }

    // Aqui esta o para autenticacao de login e senha "codificador" (String BASE64)
    private String passTo64(String phrase){
    	byte[] encodedBytes = Base64.getEncoder().encode(phrase.getBytes());

    	return new String(encodedBytes);
    }

    /* Destructor. Closes the connection if something bad happens. */
    protected void finalize() throws Throwable {
		if(isConnected) {
			close();
		}
		super.finalize();
    }
}

