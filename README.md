# radar: Technology Radar for Sobrado

source for the Sobrado Tech Radar

Uses [Zalando Tech Radar](https://opensource.zalando.com/tech-radar/), inspired by [Thoughtworks](https://www.thoughtworks.com/radar) 


source:  
- https://opensource.zalando.com/tech-radar/radar.js 
- legacy: https://github.com/zalando/tech-radar/blob/master/docs/radar.js 


## Running the application in dev mode

Requirements:
- Java 17 or newer
- Maven 3.8.1 or newer
- quarkus command line tool (optional)
- PostgreSQL 13 or newer
  - running locally 
  - or: Docker or Podman running on your machine

You can run your application in dev mode that enables live coding using:
```shell script
quarkus dev
or... 
./mvnw compile quarkus:dev
```

in the current setup, it requires a PostgreSQL Database to be available as described in `src/main/resources/application.properties`. 
alternatively, you can use Quarkus DevServices to deploy a PostgreSQL Database in a Docker Container autoamtically; cf Demo-App. 


## Deployment Units

The Radar uses a PostgreSQL Database defined in `src/main/resources/application.properties`.  
Schemas in `src/main/resources/db/migration` are run by Flyway at startup and validated by Hibernate. 


# 🚀 Deploy to private OpenShift with private Gitlab


- use .gitlab-ci.yml to deploy to OpenShift
- configure new DB by hand, deploying Application Properties as ConfigMap and Secrets

# 🚀 Deploy to Kubernetes with Github Actions

- use .github/workflows/main.yml to deploy to Kubernetes
- tbd 




# Next Steps

- Build simple GUI for adding new Technologies per year 
- User Management using Keycloak Tools Reaml 



# Legacy Manual Deploy to vsnh OpenShift

Build jar with Java 11 (Java 17 not yet supported by builder image)   
jar Upload  
expose PostgreSQL Secrets in `BD` or `B` etc. to Env Variables similar to `application.properties`. 

# 📒 Legacy Quarkus Documentation

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/radar-0.0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
