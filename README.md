# misoboy-oauth

> Spring Security OAuth2 study project implementing an Authorization Server, Resource Server, and OAuth2 client in a multi-module Maven setup.

[![Java](https://img.shields.io/badge/Java-8+-orange?logo=java&logoColor=white)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.x-6DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![OAuth2](https://img.shields.io/badge/OAuth2-Spring_Security-6DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-security-oauth)

## Overview

A hands-on learning project for **Spring Security OAuth2**, structured as a multi-module Maven project covering the core OAuth2 components:

| Module | Description |
|--------|-------------|
| `misoboy-oauth-server` | OAuth2 Authorization Server (issues tokens) |
| `misoboy-oauth-api` | Resource Server (protected REST API) |
| `misoboy-oauth-common` | Shared models and utilities |

## OAuth2 Flow

```
Client App
    │
    ├─► Authorization Server (/oauth/token)
    │       └─► Returns access_token
    │
    └─► Resource Server (API) with Bearer token
            └─► Returns protected resources
```

## Getting Started

```bash
git clone https://github.com/misoboy/misoboy-oauth.git
cd misoboy-oauth

# Build all modules
./mvnw clean package

# Start the Authorization Server
java -jar misoboy-oauth-server/target/*.jar

# Start the Resource Server
java -jar misoboy-oauth-api/target/*.jar
```

## Endpoints

### Authorization Server (port 8081)
- `POST /oauth/token` — Get access token (password grant)
- `GET /oauth/check_token` — Validate token

### Resource Server (port 8080)
- `GET /api/**` — Protected endpoints (requires Bearer token)

## License

MIT

