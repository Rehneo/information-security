# Лабораторная работа №1

Проект на **Spring Boot**, реализующий базовый API для работы с пользователями и постами. Проект демонстрирует хранение паролей, аутентификацию через JWT и защиту от XSS.

---

## 🚀 Функционал

* **Регистрация пользователей**
* **Аутентификация** (`POST /api/v1/auth`) — выдача JWT-токена при успешном логине.
* **Получение всех постов** (`GET /api/v1/posts`) — возвращает список постов в системе.
* Используется **PostgreSQL** как база данных.
* **Автоматическая проверка качества и безопасности кода** через GitHub Workflows с SpotBugs и OWASP Dependency Check.

---

## 📦 API эндпоинты

### 1. `POST /api/v1/auth`

Аутентификация пользователя.

**Request:**

```json
{
  "username": "alice",
  "password": "password123"
}
```

**Response (200 OK):**

```json
{
  "jwtToken": "eyJhbGciOiJIUzI1NiIsInR...",
}
```

**Response (401 Unauthorized):**

```json
"Invalid credentials"
```

---

### 2. `GET /api/v1/posts`

Получение всех постов в системе. Требует `Authorization: Bearer <JWT>` в заголовке.

**Request:**

```http
GET /api/v1/posts
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "title": "My first post",
    "content": "Hello, world!",
    "createdAt": "2025-09-30T15:30:00Z"
  },
  {
    "id": 2,
    "title": "Second post",
    "content": "Some content here",
    "createdAt": "2025-10-01T08:10:00Z"
  }
]
```

**Response (401 Unauthorized):**

```json
"Unauthorized"
```

---

## 🔒 Реализованные меры безопасности

### 1. Хранение паролей

* Пароли **никогда не хранятся в открытом виде**.
* Используется **BCrypt** для хэширования паролей.

### 2. Аутентификация и авторизация

* При успешном логине пользователю выдаётся **JWT-токен**.
* Все защищённые эндпоинты (`/api/**`) требуют заголовок `Authorization: Bearer <token>`.
* Проверка токена выполняется в кастомном **JWT-фильтре**, который извлекает `username` из токена и добавляет его в `SecurityContext`.
* Доступ к эндпоинтам проверяется через Spring Security (`authenticated()`).

### 3. Защита от SQL Injection

* Используется **Spring Data JPA / Hibernate**, которые автоматически подставляют параметры запросов.
* Репозитории не формируют строки SQL вручную — все запросы параметризованы.

### 4. Защита от XSS

* Все данные, возвращаемые API, проходят экранирование через `HtmlUtils.htmlEscape(...)`.
* Это предотвращает возврат вредоносных HTML/JavaScript-тегов.

### 5. Общие меры

* `CSRF` отключён, так как используется stateless REST API.
* Сессии не используются (`SessionCreationPolicy.STATELESS`).
* JWT Secret хранится в настройках и может быть вынесен в переменные окружения.
* Роли пользователей (`USER`, `ADMIN`) задаются через enum и могут использоваться для разграничения доступа.

### 5. CI/CD и статический анализ

- **SpotBugs** (SAST) проверяет код на потенциальные ошибки и баги безопасности.
- **OWASP Dependency Check** анализирует зависимости проекта на известные уязвимости.
- Настроен GitHub Workflow, который автоматически выполняет эти проверки при push и pull request в `main`.
- Отчёты SpotBugs и OWASP загружаются как артефакты, что позволяет отслеживать результаты проверки качества и безопасности.
<img width="1418" height="1548" alt="image" src="https://github.com/user-attachments/assets/c4f5251e-5821-48ca-a819-3330365aabab" />

<img width="1356" height="1142" alt="image" src="https://github.com/user-attachments/assets/4e510d85-57a6-41ce-8936-12e5bfaa371f" />


---
## ✅ Примеры запросов

1. Авторизация:

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"password123"}'
```

2. Получение постов:

```bash
curl http://localhost:8080/api/posts \
  -H "Authorization: Bearer <JWT>"
```

---
