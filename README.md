# Sistema MOTIVA — Sprint 4

## Descrição

O Sistema MOTIVA é uma solução para monitoramento e priorização de manutenção em trechos de rodovias.

Nesta Sprint 4, o sistema foi migrado de uma aplicação com persistência JDBC para uma API REST utilizando Spring Boot, Spring Data JPA e Oracle.

A aplicação está organizada em camadas:

- `model` — entidades JPA
- `repository` — acesso aos dados utilizando Spring Data JPA
- `service` — regras de negócio
- `controller` — endpoints da API REST

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Web
- Maven
- Oracle Database
- Git/GitHub

## Execução

Para executar o projeto, utilize:

```bash
mvn spring-boot:run
```

A API fica disponível em:

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/trechos` | Lista todos os trechos |
| GET | `/api/trechos/{id}` | Busca um trecho pelo ID |
| POST | `/api/trechos` | Cria um trecho |
| PUT | `/api/trechos/{id}` | Atualiza um trecho |
| DELETE | `/api/trechos/{id}` | Remove um trecho |
| GET | `/api/equipes` | Lista todas as equipes |
| GET | `/api/equipes/{id}` | Busca uma equipe pelo ID |
| POST | `/api/equipes` | Cria uma equipe |
| PUT | `/api/equipes/{id}` | Atualiza uma equipe |
| DELETE | `/api/equipes/{id}` | Remove uma equipe |
| GET | `/api/intervencoes` | Lista todas as intervenções |
| GET | `/api/intervencoes/{id}` | Busca uma intervenção pelo ID |
| POST | `/api/intervencoes` | Cria uma intervenção |
| PUT | `/api/intervencoes/{id}` | Atualiza uma intervenção |
| DELETE | `/api/intervencoes/{id}` | Remove uma intervenção |
| POST | `/api/relatorios` | Gera e persiste um relatório |
| GET | `/api/relatorios` | Lista o histórico de relatórios |

## Exemplos de requisições

### Listar trechos

```bash
curl http://localhost:8080/api/trechos
```

### Criar equipe

```bash
curl -X POST http://localhost:8080/api/equipes -H "Content-Type: application/json" -d "{\"nome\":\"Equipe Norte\"}"
```

### Listar intervenções

```bash
curl http://localhost:8080/api/intervencoes
```

### Criar intervenção

```bash
curl -X POST http://localhost:8080/api/intervencoes -H "Content-Type: application/json" -d "{\"tipo\":\"Roçada Mecanizada\",\"descricao\":\"Intervenção de roçada mecanizada\",\"idTrecho\":21}"
```

### Gerar relatório

```bash
curl -X POST http://localhost:8080/api/relatorios
```

### Consultar histórico

```bash
curl http://localhost:8080/api/relatorios
```

## Regras de prioridade

A classificação dos trechos é realizada na camada `Service`:

- URGENTE: nível de vegetação >= 80
- CRÍTICO: nível de vegetação >= 50
- ATENÇÃO: nível de vegetação >= 25
- NORMAL: nível de vegetação < 25

O relatório é gerado a partir dos trechos atualmente cadastrados e é persistido no banco de dados.

## Derived Queries

O projeto utiliza Spring Data JPA para criação de consultas derivadas pelo nome dos métodos.

Exemplo:

```java
findByNivelVegetacaoGreaterThanEqual(double minimo)
```

Essa abordagem permite que o Spring Data JPA gere a consulta automaticamente, sem SQL manual no código da aplicação.

## Respostas às perguntas de reflexão

### 1. Por que o Repository é uma interface e não uma classe?

O Repository é uma interface porque o Spring Data JPA fornece a implementação automaticamente em tempo de execução. Dessa forma, não precisamos escrever manualmente os métodos de acesso ao banco.

### 2. O pattern DAO da Sprint 3 morreu na migração ou só mudou de forma?

O pattern DAO não morreu. Ele mudou de forma. Na Sprint 3, criávamos manualmente as classes DAO e escrevíamos o código JDBC para acessar o banco. Na Sprint 4, o `JpaRepository` assume esse papel e o Spring Data JPA fornece a implementação necessária.

### 3. Por que a validação de nivelVegetacao >= 0 deve ficar no Service e não no Controller?

Porque a validação é uma regra de negócio. O Service concentra as regras da aplicação, enquanto o Controller deve apenas receber as requisições HTTP e encaminhá-las para a camada responsável pelo processamento.

### 4. No JDBC puro vocês escreviam SQL. Onde está o SQL do findByTipo()? Quem o gerou?

O SQL não é escrito manualmente no projeto. O método `findByTipo()` é interpretado pelo Spring Data JPA, que gera a consulta necessária automaticamente em tempo de execução.

## Testes realizados

Foram realizados testes utilizando cURL para verificar o funcionamento da API REST, incluindo operações de consulta, criação e geração de relatórios.

Os testes realizados incluem:

- GET de trechos
- GET de equipes
- POST de equipe
- GET de intervenções
- POST de intervenção
- POST de relatório
- GET de relatório/histórico

## Observação

O endpoint de consulta de relatórios por período não foi implementado nesta versão.

## Git

O projeto utiliza Git para controle de versão e GitHub para armazenamento do código-fonte.

Arquivos gerados pelo Maven e arquivos específicos da IDE devem permanecer fora do repositório por meio do `.gitignore`.