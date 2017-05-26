# a-mail-user-agent-inJava
Aqui um guia rápido para usar o git:

##1º - Instalar o git
  Baixar o git [aqui](https://git-scm.com/downloads), para Windows é o velho clique em avançar de sempre(eu acho hue).</br>
  Serão instalados o 'Git Bash' e o 'Git GUI':</br>
    O 'Git Bash' é o modo clássico do Git, abre um terminal com ambiente configurado.</br>
    O 'Git GUI' é uma interface gráfica bonitinha.
  
  É recomendável fazer umas configurações iniciais setando seu nome e email.</br> 
  Basta abrir o 'Guit Bash' e digitar os comandos:</br>
  `git config --global.user.name 'SeuNomeAquiEntreAsAspasSimples'`</br>
  `git config --global.user.eamil 'seu@email.aqui'`
    
##2º - Clonar o repositório
  Agora que o git está instalado, você tem de criar um pasta onde vai trabalhar com o projeto no seu PC.</br>
  Aqui eu vou seguir os passos com o 'Git Bash', mas imagino que com o 'Git GUI' deva até ser mais simples.
  
  Com o terminal (Guit Bash) aberto, é só navegar até a pasta em que você vai trabalhar, usando o clássico
  comando `cd nomeDaPata/` para abrir a pasta e o `cd ..` para sair da pasta atual.</br>
  
  Agora que está na pasta certa use o comando `git clone` para clonar o projeto na sua pasta atual:</br> 
  `https://github.com/Vitor-Felix/a-mail-user-agent-inJava.git`</br>
  Pronto você já tem os arquivos do projeto localmente.
  
##3º - Ciclo básico em que trabalharemos
  Vamos deixar bem simples e não usaremos mais do que os comandos:</br>
  `git add`, `git commit`, `git pull`, `git push` e em uma casualidade, o `git merge`.
  
  Se não me engano o Guit tem três estágios para você poder subir seu código para o repositório do trabalho final.</br>
  Quando você está editando um código aí, você está no **working directory** e quando terminar a edição, usa:</br>
  `git add arquivoEditado.tipo`, que vai passar o arquivo do working directory para a **staging area**, que é meio
  que uma área antes de você subir para o arquivo editado para o servidor, como por exemplo, algo que você fez hoje
  de noite até de madrugada mas quer dormir para finalizar no outro dia de manhã, deixa ele na staging area. :)
  
  Agora se você já terminou tudo que ia fazer e quer colocar as atualizações no projeto original, então você vai passar</br>
  da staging area para o **git directory**. Para isto basta dar o comando:</br>
  `git commit arquivoEditado.tipo -m "Correcao do bug do registro"`.</br>
  No caso o parâmetro -m é para que você possa escrever uma breve mensagem entre as aspas, do que foi feito neste commit.</br>
  O git exige uma mensagem sempre que for dado um commit, se você esquecer, ele vai abrir uma tela no VIM que é bem chato</br>
  de escrever por lá, ou seja, para facilitar não esqueça o `-m "mensagem do que foi feito"` ao final do commit.
  
  Porém fazer o commit não faz ele ser unido ao projeto original, para isto você deve dar o comando: `git push`</br>
  que irá pedir seu email e senha do Git Hub, se tudo der certo você terá adcionado seu trabalho ao repositório em que</br>
  todos estão trabalhando. :D
  
#4 Resumo do nosso ciclo e troubleshooting
###1. Antes de começar a trabalhar, confira se alguém fez alguma modificação antes:</br> 
  `git pull`</br>
  **comando muito útil** para acompanhar seus arquivos: `git status`

###2. Agora que você está com o projeto atualizado, começe seu trabalho. Se você já terminou:</br>
  `git add arquivo.tipo`</br>
  caso queira tirar o arquivo da staging area: `git reset HEAD arquivo.tipo`

###3. Agora você terminou e quer deixar na área de transferência:</br>
  `git commit arquivo.tipo`</br>
  comando se quiser ver os commits feitos: `git hist`
  
###4. Agora que já fez todo o trabalho e quer apenas juntar suas modificações com o projeto:
  `git push`
  
##Indico a leitura:
1. Um "curso" rápido e interessante (vídeo):  https://www.youtube.com/watch?v=WVLhm1AMeYE
2. Guia rápido:                               http://rogerdudler.github.io/git-guide/index.pt_BR.html
3. Documentação do git:                       https://git-scm.com/doc


