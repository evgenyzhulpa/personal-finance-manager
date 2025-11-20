## Personal Finance Manager

Personal Finance Manager is a Spring Boot 3 application for tracking users and their financial transactions. The project exposes a REST API, persists data in PostgreSQL, and serves a small Vue-powered single-page UI (Users, Transactions, Home) from `src/main/resources/static`.

### Tech stack
- Java 21, Gradle, Spring Boot 3.5 (Web, Data JPA)
- PostgreSQL 12 (Docker compose provided)
- MapStruct 1.5, Lombok
- Vue 3 (CDN) + vanilla HTML/CSS/JS for the lightweight frontend
- JUnit 5, Mockito, AssertJ for tests

### Project layout
```
src/main/java/com/example/personal_finance_manager
├── controller         # REST controllers
├── dto                # request/response payloads
├── mapper             # MapStruct mappers
├── model              # JPA entities/enums
├── repository         # Spring Data repositories
├── service            # Service interfaces + impls
└── utils              # Shared helpers

src/main/resources
├── application.yaml   # Spring configuration
└── static             # index.html, users.html, transactions.html, styles, api.js

docker/
├── docker-compose.yaml
└── init.sql           # optional seed data / schema adjustments
```

### Prerequisites
- JDK 21+
- Gradle wrapper (included) or local Gradle 8+
- Docker (optional, for PostgreSQL)
- Environment variables: `DB_USER`, `DB_PASSWORD`, `DB_NAME` (if you reuse docker compose defaults)  
  Spring reads `DB_USER`/`DB_PASSWORD`; update `application.yaml` if you change host/port/db.

### Getting started
1. **Start PostgreSQL (optional but recommended)**
   ```bash
   cd docker
    docker compose up -d
   ```
   Default connection: `jdbc:postgresql://localhost:5432/app_db` (see `application.yaml`). Adjust schema/DB name in either the YAML file or `docker/init.sql`.

2. **Run the application**
   ```bash
   ./gradlew bootRun
   ```
   The service starts on `http://localhost:8080`.

3. **Open the frontend**
   - Home: `http://localhost:8080/index.html`
   - Users: `http://localhost:8080/users.html`
   - Transactions: `http://localhost:8080/transactions.html`
   The pages use the REST API under `/api/...`.

### REST API
#### Users (`/api/users`)
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET    | `/api/users` | List all users |
| GET    | `/api/users/{id}` | Fetch user by ID |
| GET    | `/api/users/{id}/with-transactions` | Fetch user with all transactions |
| POST   | `/api/users` | Create user (body: `username`, `email`, `password`) |
| PUT    | `/api/users/{id}` | Update user (partial updates supported) |
| DELETE | `/api/users/{id}` | Remove user |

#### Transactions (`/api/transactions`)
| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| GET    | `/api/transactions` | List all transactions |
| GET    | `/api/transactions/{id}` | Fetch transaction by ID |
| POST   | `/api/transactions` | Create transaction (body includes `amount`, `type`, `category`, `description`, `date`, `userId`) |
| PUT    | `/api/transactions/{id}` | Update transaction |
| DELETE | `/api/transactions/{id}` | Remove transaction |

Common responses include `ErrorResponse { message }` for errors such as `404 Not Found`.

### Frontend
- Files are located in `src/main/resources/static`.
- Uses Vue 3 (via CDN) and a custom CSS inspired by Skillbox branding with animated gradients.
- `api.js` wraps calls to `/api/users` and `/api/transactions`.
- No separate build step: Spring Boot serves the static assets directly.

### Testing
```
./gradlew test
```

### Development tips
- Descriptive JavaDocs are provided for all beans (controllers, services, repositories, helpers, mappers).
- MapStruct handles most DTO/entity conversions; `TransactionMapperDelegate` wires `userId` → `User`.
- `UserEntityHelper` centralizes “load user by ID (optionally with transactions)” logic.
- Run `docker compose down` to stop the database when you are done.

### Possible next steps
- Authentication/authorization (Spring Security).
- Aggregated reports/analytics endpoints.
- Filtering/pagination for transactions.
- Frontend build tooling (Vite / Vue CLI) if the UI becomes more complex.

For questions or improvements, feel free to open issues or extend the project! 

