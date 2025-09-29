Feito por Samuel Santos Souza

# Projeto-To-Do
Este projeto é um **backend de lista de tarefas (To Do)** desenvolvido em **Spring Boot**, utilizando **PostgreSQL** como banco de dados e configurado para rodar com **Docker Compose**.  

Ele é uma **API RESTful** que permite criar, listar, atualizar e excluir tarefas do dia a dia.  
---

## 🚀 Tecnologias utilizadas
- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Validation
- **PostgreSQL**
- **Docker & Docker Compose**
- **JUnit 5** (testes unitários e de integração)
- **Maven** (gerenciamento de dependências)

---

## 📂 Estrutura do projeto
to-do-backend/
├── src/
│ ├── main/
│ │ ├── java/com/example/todo/
│ │ │ ├── controller/ # Endpoints REST
│ │ │ ├── entity/ # Entidades JPA (models)
│ │ │ ├── repository/ # Interfaces de acesso ao banco
│ │ │ ├── service/ # Regras de negócio
│ │ │ └── TodoApplication.java # Classe principal
│ │ └── resources/
│ │ ├── application.properties # Configuração do Spring
│ └── test/java/com/example/todo/ # Testes automatizados
├── docker-compose.yml
├── pom.xml
└── README.md

yaml
Copiar código

---

## ⚙️ Como rodar o projeto

### 1️⃣ Pré-requisitos
- [Java 17](https://adoptium.net/)  
- [Maven](https://maven.apache.org/)  
- [Docker](https://www.docker.com/)  

Verifique as versões instaladas:
```bash
java -version
mvn -v
docker -v
2️⃣ Clonar o repositório
bash
Copiar código
git clone https://github.com/seu-usuario/to-do-backend.git
cd to-do-backend
3️⃣ Subir o banco de dados com Docker
O docker-compose.yml já está configurado com PostgreSQL.
Execute:

bash
Copiar código
docker-compose up -d
O banco ficará disponível em:

Host: localhost

Porta: 5432

Usuário: postgres

Senha: postgres

Banco: tododb

4️⃣ Rodar a aplicação
Na raiz do projeto, execute:

bash
Copiar código
mvn spring-boot:run
A API estará disponível em:
👉 http://localhost:8080

📌 Endpoints principais
Método	Endpoint	Descrição
GET	/api/todos	Lista todas as tarefas
GET	/api/todos/{id}	Busca uma tarefa por ID
POST	/api/todos	Cria uma nova tarefa
PUT	/api/todos/{id}	Atualiza uma tarefa
DELETE	/api/todos/{id}	Remove uma tarefa

Exemplo de JSON para criar uma tarefa
json
Copiar código
{
  "title": "Estudar Spring Boot",
  "description": "Ler documentação e praticar com projeto",
  "completed": false
}
🧪 Rodando os testes
bash
Copiar código
mvn test
🐳 Rodando backend + banco com Docker
Se quiser rodar backend + banco de dados direto no Docker, adicione um Dockerfile e rode:

bash
Copiar código
docker-compose up --build
