# Fair-API

##### A Fair-API é uma de gerenciamento de feiras livres. Com ela, você pode cadastrar, excluir, editar, consultar, importar dados de feiras via csv.

## Tecnologias
- Spring Boot
- Gradle
- Kotlin
- Java 11
- PostgreSQL
- Docker

## Como rodar a localmente
```sh
docker-compose up
```

## Testes e cobertura de testes
Primeiro faça o build da aplicação. O build já roda a etapa de testes.
```sh
./gradlew clean build
```
Para visualizar a cobertura de testes, acesse:
`http://localhost:63342/springboot-fair-api/build/reports/jacoco/test/html/index.html`


## Arquitetura utilizada

A api utiliza os conceitos da Clean Architecture como referência. Onde isolamos nosso dominio e regras de negocio do restante da aplicação, conseguindo assim manter nosso código coeso e desacoplado. A arquitetura é dividita em 3 partes:
 - application - também chamado de adapter, é onde ficam os controllers. Recebem as requisições externas, convertem os dto e passam os dados para camada de services.
 - domain - contém o core da aplicação, é responsável por toda a lógica de negócio.
 - infra - contem os frameworks utilizados na aplicação, como as entidades mapeadas no banco em caso de utilizar orm, o framework do banco de dados, comunicação com apis externas, mensageria e etc.

Segue uma imagem de um arquitetura limpa simplificada:
<p align="center">
<img src="https://miro.medium.com/max/765/0*J8pxLe88qYFN7wUf.png"  alt="clean arch" />
</p>

Referências:
 - https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
 - Livro: Clean Architecture: A Craftsman’s Guide to Software Structure — ISBN: 0134494164
 - https://medium.com/luizalabs/descomplicando-a-clean-architecture-cf4dfc4a1ac6


## Documentação dos Endpoints e OpenAPI

Swagger: `http://localhost:8080/swagger-ui.html`
OpenAPI: `http://localhost:8080/api-docs`

## Melhorias Sugeridas
- Validar layout do CSV
- Processamento de arquivo csv assincrono utilizando mensageria(ex: AWS SQS, RabbitMQ) + bucket (ex: AWS S3)
- Teste de integração da camada infra
## Live Demo
A aplicação está atualmente publicada na AWS, simulando um ambiente de teste de boa disponbilidade, utilizando:
 - ECS com Fargate.
 - Load Balancer com 2 container
 - Cluster em 2 zonas de disponibilidade
 
Url: `http://fair-Publi-1GIIUS47EWTQ2-84110573.us-west-1.elb.amazonaws.com`
Health check: `http://fair-Publi-1GIIUS47EWTQ2-84110573.us-west-1.elb.amazonaws.com/actuator/health`

## Endpoints
### Cadastrar uma feira

**POST - /v1/fairs**
Request:
```json
{
    "longitude": -3.784596,
    "latitude": 5.4178495,
    "sector": "Setor Teste",
    "area": "Area Teste",
    "districtCode": 1,
    "districtName": "Bacabal",
    "subCityHallCode": 2,
    "subCityHallName": "Centro",
    "region5": "Leste",
    "region8": "Norte",
    "name": "Feira da Cohab",
    "register": "4041-0",
    "street": "RUA ANTONIO LOBO",
    "number": "626",
    "neighborhood": "COHAB",
    "reference": "PROX AO HOSPITAL"
}
```
Response:
```
Status: 201 - Created
```
```json
{
    "id": 3,
    "longitude": -3.784596,
    "latitude": 5.4178495,
    "sector": "Setor Teste",
    "area": "Area Teste",
    "districtCode": 1,
    "districtName": "Bacabal",
    "subCityHallCode": 2,
    "subCityHallName": "Centro",
    "region5": "Leste",
    "region8": "Norte",
    "name": "Feira da Cohab",
    "register": "4041-0",
    "street": "RUA ANTONIO LOBO",
    "number": "626",
    "neighborhood": "COHAB",
    "reference": "PROX AO HOSPITAL",
    "active": true,
    "createdAt": "2021-09-08T00:42:09.248151",
    "updatedAt": "2021-09-08T00:42:09.248159"
}
```
### Excluir uma feira
**DELETE - /v1/fairs/{id}**
Response:
```
Status: 204 - No Content
```

### Atualizar uma feira

**PUT - /v1/fairs/{id}**
Request:
```json
{
    "longitude": -3.784596,
    "latitude": 5.4178495,
    "sector": "test sector atualizado",
    "area": "test area atualizadao",
    "districtCode": 1,
    "districtName": "Bacabal",
    "subCityHallCode": 2,
    "subCityHallName": "Centro",
    "region5": "Leste Editado",
    "region8": "Norte",
    "name": "Feira da Cohab",
    "register": "4041-0",
    "street": "RUA ANTONIO LOBO",
    "number": "626",
    "neighborhood": "COHAB",
    "reference": "PROX AO HOSPITAL"
}
```
Response:
```
Status: 200 - OK
```
```json
{
    "id": 7,
    "longitude": -3.784596,
    "latitude": 5.4178495,
    "sector": "test sector atualizado",
    "area": "test area atualizadao",
    "districtCode": 1,
    "districtName": "Bacabal",
    "subCityHallCode": 2,
    "subCityHallName": "Centro",
    "region5": "Leste Editado",
    "region8": "Norte",
    "name": "Feira da Cohab",
    "register": "4041-0",
    "street": "RUA ANTONIO LOBO",
    "number": "626",
    "neighborhood": "COHAB",
    "reference": "PROX AO HOSPITAL",
    "active": true,
    "createdAt": "2021-09-07T19:01:53.4474663",
    "updatedAt": "2021-09-07T19:01:53.4474663"
}
```

### Listar várias feiras - Paginado
Filtros disponíveis:
 - distrito -> district
 - regiao5 -> region5 
 - nome_feira -> fairName
 - bairro -> neighborhood

**GET - /v1/fairs?page=1&size=2&district=Bacabal**

Response:
```
Status: 200 - OK
```
```json
{
    "content": [
        {
            "id": 1,
            "longitude": -3.784596,
            "latitude": 5.4178495,
            "sector": "Setor Teste",
            "area": "Area Teste",
            "districtCode": 1,
            "districtName": "Bacabal",
            "subCityHallCode": 2,
            "subCityHallName": "Centro",
            "region5": "Leste",
            "region8": "Norte",
            "name": "Feira da Cohab",
            "register": "4041-0",
            "street": "RUA ANTONIO LOBO",
            "number": "626",
            "neighborhood": "COHAB",
            "reference": "PROX AO HOSPITAL",
            "active": true,
            "createdAt": "2021-09-08T00:53:55.60978",
            "updatedAt": "2021-09-08T00:53:55.609789"
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": ,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 1,
    "size": 1,
    "number": 0,
    "empty": false
}
```

### Carregar os dados do arquivo csv
**POST - /v1/fairs/import-from-file**
Reponse:
```
Status: 201 - Created
```