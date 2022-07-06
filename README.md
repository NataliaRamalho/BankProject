# Projeto Banco
Api desenvolvida em Java utilizando Spring Boot que possui algumas funções de um banco.

Sumário 📑 
=================
   1. [Principais tecnologias](#Principais-tecnologias)
   2. [Pré requisitos para executar o projeto](#Pré-requisitos-para-executar-o-projeto)
   3. [Rodando o projeto](#Rodando-o-projeto)
   4. [Acessando o banco de teste](#Acessando-o-banco-de-teste)
   5. [Rotas da aplicação ](#Rotas-da-aplicação )

# Principais tecnologias
- Java
- Spring Boot

# Pré requisitos para executar o projeto 
- [JDK](https://www.oracle.com/java/technologies/downloads/#java18)
- Editor de código. Recomenda-se o [SpringTools](https://spring.io/tools)

# Rodando o projeto ⚙️

- Clone este repositório 
Caso tenha o [git](https://git-scm.com/downloads) instalado, digite o comando abaixo no seu terminal. 
Se não, faça o download do repositório e descompacte o arquivo.

`````
    git clone https://github.com/NataliaRamalho/BankProject.git
`````

- Abra o projeto no editor de codigo, caso esteja usando o [SpringTools](https://spring.io/tools), import o projeto selecionando Import -> Maven -> Existing Maven Projects -> Next -> Selecione o local onde salvou o projeto -> Finish.

- Abra o arquivo BackendApplication.java em (backend/src/main/java/com/BackendApplication.java)

- Execute o projeto.

- Parabéns o projeto esta executando em http://localhost:8080/ .

# Acessando o banco de teste 

- Com o projeto rodando, acesse a url http://localhost:8080/h2-console

- Cole em 'JDBC URL' a string: 'jdbc:h2:mem:testdb' 

- Clique em connect

- Parabéns você acessou o banco de teste.

# Rotas da aplicação 
**Base url: http://localhost:8080**

**1. Users**
    <li> (GET) /users </li>        
    <li> (POST) /users </li>   

`````
    {
        "accountNumber": 100,
        "balance": 0,
        "email": "teste@gmail.com",
        "name": "teste",
        "password": "123"
    }
`````
<li> (POST) /users/login </li>   

```
    {
        "email": "teste@gmail.com",
        "password": "123"
    }
```


**2. Operations**

    - (GET) /operations 
    </br>

    - (POST) /operations/{user_id}
     ```
           {
                "type": 0,
                "value": 250,
                "instant": "2022-07-05 00:00:00"
            }
     ```

- Caso tenha o [insomnia](https://insomnia.rest/download) basta importar o arquivo "routes" que terá todas as rotas da aplicação

⏰ Projeto desenvolvido em fev/2021