# Star Wars Planets

Desafio de programação envolvendo o desenvolvimento e consumo de APIs REST.

## Resumo

Esta API foi desenvolvida com Spring WebFlux para Java. O Maven foi escolhido como o gerenciador deste projeto. O MySql 8.0.23 foi eleito como o banco de dados.

### Serviços
#### POST /add

adiciona um planeta ao banco de dados

##### Exemplo de requisição

```
POST /add
{
    "name": "Tatooine",
    "climate": "clima",
    "terrain": "terreno"
}
```


#### DELETE /planet/{id}

remove um planeta do banco de dados dado um ID


#### GET /planet/{id}

retorna um objeto JSON contendo dados de um planeta


#### GET /search/{name}

retorna uma lista de planetas cujo nome contém o parâmetro fornecido

#### GET /planets

lista todos os planetas do banco de dados

#### GET /swapiplanets

lista todos os planetas da api swapi.dev

## Execução

É necessário fazer o build do projeto com o Maven antes de construir a imagem docker.

```bash
mvn package
```

Após o build, a pasta ```/target``` deve conter o executável do projeto. Para executá-lo com uma instância de MySQL, basta emitir o comando abaixo para subir também uma imagem de banco de dados.

```bash
docker-compose up
```

## Testes

Para executar testes, basta executar no terminal os comandos abaixo.

### Adicionar planetas
``` bash
curl --location --request POST 'localhost:8989/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tatooine",
    "climate": "clima",
    "terrain": "terreno"
}'
```

``` bash
curl --location --request POST 'localhost:8989/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "abc",
    "climate": "clima",
    "terrain": "terreno"
}'
```

### Remover planeta

``` bash
curl --location --request DELETE 'localhost:8989/planet/2'
```

### Obter um planeta

``` bash
curl --location --request GET 'localhost:8989/planet/1'
```

### Buscar planetas

``` bash
curl --location --request GET 'localhost:8989/search/at'
```

### Listar todos os planetas

``` bash
curl --location --request GET 'localhost:8989/planets'
```

### Listar todos os planetas da API swapi.dev

``` bash
curl --location --request GET 'localhost:8989/swapiplanets'
```

## Observações

Para facilitar a testagem, esta API está configurada para *dropar* o banco de dados ao fim de sua execução. Para manter a persistência do banco de dados, basta alterar valor do parâmetro ```spring.jpa.hibernate.ddl-auto``` para ```update``` no arquivo ```application.properties```