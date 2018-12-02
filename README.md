## Trabalho para a disciplina de Programação Web II do IFRS Campus Porto Alegre

### Pré-requisitos
- JDK 1.8
- MySQL Server 5.7+

### Instalação
- Criar um banco de dados com o nome ```sistema_pwii```.
- Executar o script ```schema.sql``` para criação das tabelas.
- Alterar as credenciais do banco de dados no arquivo ```persistence.xml```, caso necessário.

### Como executar
Para executar o projeto basta executar o comando a seguir:
```
./mvnw tomcat7:run
```
As dependências necessárias serão baixadas e o projeto será executado em um servidor Tomcat 7 embutido.

Após executar o projeto é possível acessar a aplicação pela seguinte URL:
```
http://localhost:9595/trabalho-pw-ii
```
