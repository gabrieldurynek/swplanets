# Star Wars Planets

Desafio de programação envolvendo o desenvolvimento e consumo de APIs REST.

## Resumo

Esta API foi desenvolvida com Spring WebFlux para Java.

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

Para construir a imagem docker e executá-la, basta baixar este repositório e executar o comando abaixo

```bash
docker-compose up
```

## Observações

Para facilitar a testagem, esta API está configurada para *dropar* o banco de dados ao fim de sua execução. Para manter a persistência do banco de dados, basta alterar valor do parâmetro ```spring.jpa.hibernate.ddl-auto``` para ```update``` no arquivo ```application.properties```