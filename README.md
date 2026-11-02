# Lost and Found API

REST API for reporting and finding lost items on campus. Built with Spring Boot, Spring Data JPA and PostgreSQL.

## Run it

1. Install Java 22 and PostgreSQL.
2. Create a database called `lostandfound`.
3. Set your database username and password in `lostandfound/src/main/resources/application.properties`.
4. From the `lostandfound` folder run `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).

The API runs on `http://localhost:8081`. Tables are created automatically.

## Endpoints

| Method | URL | What it does |
|--------|-----|--------------|
| POST | `/api/items` | Report a lost or found item |
| GET | `/api/items` | List all items |
| GET | `/api/items/search?name=&category=&location=` | Search. Use any filters you like, together or alone |
| GET | `/api/items/{id}` | Get one item |
| PUT | `/api/items/{id}` | Update an item |
| PATCH | `/api/items/{id}/status` | Mark an item `OPEN` or `RECOVERED` |
| DELETE | `/api/items/{id}` | Delete an item |

## Example request

POST `/api/items`

```json
{
  "name": "Black backpack",
  "description": "Has a laptop sticker on the front",
  "category": "Bags",
  "location": "Library",
  "type": "LOST",
  "contact": "0999000000"
}
```

`type` is `LOST` or `FOUND`. `status` defaults to `OPEN` and `dateReported` defaults to today if you leave them out.

Change status: PATCH `/api/items/1/status` with `{ "status": "RECOVERED" }`

## Responses

- 201 item created, 204 item deleted
- 400 bad input (missing fields, wrong enum value, future date)
- 404 item does not exist
