# ProjetoCaixaEletronico
Projeto desenvolvido durante o terceiro ano da minha faculdade, nesse projeto criou-se um sistema de caixa eletrônico utilizando os princípios de orientação a objetos em Java.


Sumário 📑 
=================
   1. [Principais tecnologias](#Principais-tecnologias)
   2. [Pré requisitos para executar o projeto](#Pré-requisitos-para-executar-o-projeto)
   3. [Rodando o projeto](#Rodando-o-projeto)

# Principais tecnologias
- Java
- SpringBoot

# Pré requisitos para executar o projeto 
- [JDK](https://www.oracle.com/java/technologies/downloads/#java18)
- Editor de código. Recomenda-se o [SpringTools](https://spring.io/tools)

# Rodando o projeto ⚙️

- Clone este repositório 
Caso tenha o [git](https://git-scm.com/downloads) instalado, digite o comando abaixo no seu terminal. 
Se não, faça o download do repositório e descompacte o arquivo.

`````
    git clone https://github.com/NataliaRamalho/ProjetoCaixaEletronico.git
`````

- Abra o projeto no editor de codigo, caso esteja usando o [SpringTools](https://spring.io/tools), import o projeto selecionando Import -> Maven -> Existing Maven Projects -> Next -> Selecione o local onde salvou o projeto -> Finish.

- Abra o arquivo BackendApplication.java em (backend/src/main/java/com/BackendApplication.java)

- Execute o projeto.

- Parabéns o projeto esta executando em http://localhost:8080/ .

# Acessando o banco de teste 

- Com o projeto rodando, acesse a url http://localhost:8080/h2-console

- Abra o arquivo application-test.properties em backend\src\main\resources\application-test.properties 

- Copie a url do arquivo application-test.properties 

- Substitua no 'JDBC URL' em http://localhost:8080/h2-console 

- Clique em connect

- Parabéns você acessou o banco de teste.


⏰ Projeto desenvolvido em fev/2021


