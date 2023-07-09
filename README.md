# ControlY Technology Discussion and Q&A Forum

A Technology Discussion and Q&A Forum is a Java project that uses Gradle as the build system and Spring Boot as the framework to provide an online platform where users can ask questions, get answers, and interact with the community on various technology topics.

## Prerequisites

Before running the project, make sure you have the following set up in your development environment:

- Java Development Kit (JDK) 8 or higher
- Gradle
- Database (e.g., PostgreSQL) and the correct credentials to access it

## Project Setup

1. Clone the project repository to your local environment:

```bash
  git clone https://github.com/codelabzproject/new-backend
```

2. Navigate to the project's root directory:

```bash
  cd new-backend
```


3. Configure the database credentials:

Open the `application.yml` file located in `src/main/resources` and update the database settings with your credentials:

```yaml
spring:
  datasource:
    url: {your url}
    username: {your username}
    password: {your-password}
```

4. Run the project using Gradle:

```bash
 ./gradlew bootRun
```

5. The development server will start, and you can access the forum at http://localhost:8080.

## Dependencies

The project uses the following dependencies:

```groovy
dependencies {
	implementation 'commons-io:commons-io:2.13.0'
	implementation 'org.glassfish.jersey.media:jersey-media-json-jackson:3.1.2'
	implementation 'org.mapstruct:mapstruct:1.5.5.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'
	annotationProcessor "org.projectlombok:lombok-mapstruct-binding:0.2.0"
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0'
	implementation 'org.springframework.boot:spring-boot-starter-actuator'
	implementation 'org.springframework.boot:spring-boot-starter-cache'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-mail'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'org.postgresql:postgresql'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
	implementation 'org.springframework.boot:spring-boot-starter-mail:3.1.1'
}
```

Make sure to update the dependencies as needed during the project's development.