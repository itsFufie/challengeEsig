# challengeEsig
Coding challenge for ESIG Java Developer position.


## Orientacoes

Para rodar o projeto é em primeiro lugar necessario estar de acordo comas dependencias: banco de dados PostgreSQL, algum servidor java capaz de dar deploy em arquivos WAR eg.: Tomcat, Jboss/Wilfly, etc e o Maven

Na raiz do projeto execute mvn install que sera responsavel por gerar o arquivo WAR, com este arquivo WAR utilize a ferramenta de sua preferencia para dar deploy na aplicacao, 
eu pessoalmente usei o Jboss/Wildfly e foi necessario fazer algumas configuracoes no arquivo Standalone.xml para que ele reconhecesse a persistence-unit do meu postgres,
caso o projeto falhe em executar na sua maquina sera necessario configurar o persistence-unit ou alterando o arquivo persistence.xml no projeto ou alterando o standalone.xml do Jboss


## Pontos De Avaliacao concluidos/nao-concluidos

Eu nao tive muito tempo para a execucao da ativade tecnica por tanto me limitei aos requisitos minimos

Uma aplicacao Java Web utilizando JSF com uma funcao de listar salarios e calcular salarios das pessoas. 

Tambem implementei um crud do modelo Pessoas.

Implementei o botao recalcular salarios na tela porem em um cenario real optaria por manter este calculo acontecendo uma vez a cada atualizacao de dados nas tabelas, dessa maneira o usuario nao precisa clicar pra atualizar toda vez que alterar algo.
