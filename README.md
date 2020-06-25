# Forum

Forum é um projeto teste para entrar no banco Bexs

[TOC]

## BackEnd
### Requisitos
#### Para buildar o backend é necessario:
 - Ter instalado o [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
 - Ter instalado o [Apache Maven](https://maven.apache.org/)

#### Para executar o projeto é necessário:
 - Ter instalado localmente ou em um server remoto o banco de dados MySql versão 5.x

### Executando o projeto
#### Preparação
##### Perfis de execução
Tenha em mente que existem dois perfis de execução disponiveis:
 - **dev**: Perfil de execução voltado para ser usado no fluxo de desenvolvimento. (Recria o schema das tabelas a cada execução)
 - **prod**: Perfil de execução voltado para ser usado no ambiente de produção. (Mantém o schema)

##### Configuração
Para executar o projeto, deve-se primeiro definir as configurações de acesso ao banco de dados. 
Essa configuração podem ser feitas internamente ou externamente ao projeto. 
Os arquivos de configuração ficam dentro da pasta *resources* e são diferentes para cada perfil de execução escolhido.
 - Arquivo do perfil *dev*: [application-dev.properties](https://github.com/DanielHGimenez/teste-bexs/blob/master/backend/src/main/resources/application-dev.properties)
 - Arquivo do perfil *prod*: [application-prod.properties](https://github.com/DanielHGimenez/teste-bexs/blob/master/backend/src/main/resources/application-prod.properties)

> Atenção: o backend roda na porta 8080 por padrão. 
> Para mudar a porta da aplicação adicione **server.port={PORTA}** ao arquivo de configuração, 
> substituindo **{PORTA}** pela porta desejada.

###### internamente
Para realizar a configuração internamente deve-se preencher os dados faltantes do arquivo de configuração
do perfil de execução escolhido.

###### externamente
Para realizar a configuração internamente deve-se preencher os dados faltantes do arquivo de configuração
do perfil de execução escolhido e coloca-lo dentro de umas pasta *config* que deve ser criada na mesma 
pasta onde será executado o backend. Caso esteja executando o projeto e não um arquivo *.jar*, essa pasta
ficará na raiz do projeto.

#### Execução
O backend pode ser executado através do projeto, usando do o plugin do Spring Boot (não indicado para ambientes de produção)
ou através de um arquivo *.jar* gerado pelo build do Apache Maven.

**Para executar com o plugin do Spring Boot**, basta executar o comando a seguir na pasta **backend** do projeto, substituindo
o *{PROFILE}* pelo perfil de execução escolhido:
```sh
mvn spring-boot:run -Drun.profiles={PROFILE}
```

**Para executar através de um arquivo *.jar***, devemos gerar o arquivo executando o comando a seguir:
```sh
mvn clean install
```
Um arquivo *.jar* será gerado dentro da pasta *target* na raiz da pasta *backend*. Para executar a aplicação execute o comando a seguir,
substituindo *{PROFILE}* pelo perfil de execução escolhido, e substituindo *{ARQUIVO_JAR}* pelo caminho até o arquivo *.jar*:
```sh
java -Dspring.profiles.active={PROFILE} -jar {ARQUIVO_JAR}
```

# FrontEnd
### Requisitos
#### Para buildar o backend é necessario:
 - Ter instalado o [Node](https://nodejs.org/en/).

#### Para executar o projeto é necessário:
 - Caso queira expor em formato de produção, é necessario ter um servidor Http. Por exemplo: [Apache](https://httpd.apache.org/).

### Executando o projeto
#### Preparação
##### Configuração
Para executar o projeto, deve-se primeiro definir as configurações de acesso a API definidos no arquivo [ApiConfig.json](https://github.com/DanielHGimenez/teste-bexs/blob/master/frontend/src/main/config/ApiConfig.json). 

#### Execução
O frontend pode ser executado através do projeto, usando o *Node* (não indicado para ambientes de produção)
ou através de um server http que armazena os arquivos estáticos gerados pelo build.

**Para executar o projeto através do *Node***, basta executar os comandos a seguir na pasta **frontend**:
```sh
npm i
npm start
```

**Para executar o projeto através de um server http**, execute os comandos a seguir: 
```sh
npm i
npm run build
```
Será gerada uma pasta chamada **build** dentro da pasta **frontend**. Mova todos os arquivos contidos 
dentro dessa pasta para a pasta que expõe os arquivos estáticos do servidor http instalado.