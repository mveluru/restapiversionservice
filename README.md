# restapiversionservice

Spring Boot 3.5 (Java 25, Maven) demo of four REST API versioning strategies. Moved out of `bankingservices`.

- Port `8084`, context path `/restapi` (base URL `http://localhost:8084/restapi`)

| Method | Endpoint Path | Strategy |
| :--- | :--- | :--- |
| `GET` | `/apiversion/v1/api`, `/apiversion/v2/api` | URI path |
| `GET` | `/apiversion/api?v1`, `/apiversion/api?v2` | Request parameter |
| `GET` | `/apiversion/api` + header `X-API-VERSION: 1` / `2` | Custom header |
| `GET` | `/apiversion/api` + `Accept: application/vnd.company.app-v1+json` / `v2` | Content negotiation |

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)
mvn test
mvn spring-boot:run
curl -s http://localhost:8084/restapi/apiversion/v1/api
```
