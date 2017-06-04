import java.io.*;
import java.net.*;
import java.util.*;

/* $Id: Envelope.java,v 1.8 1999/09/06 16:43:20 kangasha Exp $ */

/**
 * SMTP envelope for one mail message.
 *
 * @author Jussi Kangasharju
 */
public class Envelope {
    /* SMTP-sender of the message (in this case, contents of From-header. */
    public String Sender;

    /* SMTP-recipient, or contents of To-header. */
    public String Recipient;

    /* Target MX-host */
    public String DestHost;
    public InetAddress DestAddr;

	private static final String mailServer = "mail.smtp2go.com";
    public static final int SMTP_PORT = 2525;		// Recomendado pelo SMTP2GO

	// credenciais para o AUTH LOGIN
	public String SMTP_USER;
	public String SMTP_PASS;	

    /* The actual message */
    public Message Message;

    /* Create the envelope. */
    public Envelope(Message message, String SMTPuser, String SMTPpass) throws UnknownHostException {
		/* Get sender and recipient. */
		Sender = message.getFrom();
		Recipient = message.getTo();

		/* Get message. We must escape the message to make sure that 
		   there are no single periods on a line. This would mess up
		   sending the mail. */
		Message = escapeMessage(message);

		/* Take the name of the local mailserver and map it into an
		 * InetAddress */
		DestHost = mailServer;
		try {
			DestAddr = InetAddress.getByName(DestHost);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + DestHost);
			System.out.println(e);
			throw e;
		}
		
		SMTP_USER = SMTPuser;
		SMTP_PASS = SMTPpass;

		return;
    }

    /* Escape the message by doubling all periods at the beginning of
       a line. */
    private Message escapeMessage(Message message) {
		String escapedBody = "";
		String token;
		StringTokenizer parser = new StringTokenizer(message.Body, "\n", true);

		while(parser.hasMoreTokens()) {
			token = parser.nextToken();
			if(token.startsWith(".")) {
			token = "." + token;
			}
			escapedBody += token;
		}
		message.Body = escapedBody;
		return message;
		}

		/* For printing the envelope. Only for debug. */
		public String toString() {
		String res = "Sender: " + Sender + '\n';
		res += "Recipient: " + Recipient + '\n';
		res += "MX-host: " + DestHost + ", address: " + DestAddr + '\n';
		res += "Message:" + '\n';
		res += Message.toString();
	
		return res;
    }
}
