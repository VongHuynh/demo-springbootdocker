# Getting Started with Spring Boot, Maven and Docker

### Prerequisite

- JDK 17
- Maven 3.8.1
- Intellij IDEA
- Docker Desktop

### Clone the repo

```
git clone https://github.com/VongHuynh/demo-springbootdocker.git
```

### If have mysql at local, create a database with name `demo_cloud` and then import data from file `./src/main/resources/init.sql`
### If don't have mysql in your local. Install mysql use docker. in source code folder run:
```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name mysql -v ./src/main/resources/init.sql:/docker-entrypoint-initdb.d/init.sql mysql:8.0
```

### Execute the below command to build the package:

```
./mvnw package
```

### Running the app packages as JAR file

```
java -jar target/spring-boot-docker-0.0.1-SNAPSHOT.jar 
```

You can now access the Hello World page via the web browser  http://localhost:8080


# Use docker
## Dockerfile explain
#### Stage 1: Builder
- FROM maven:3.8.1-openjdk-17-slim AS builder: This sets the base image to be used for the builder stage. In this case, it uses the Maven image with OpenJDK 17 and a slim version of the operating system.

- WORKDIR /app: Sets the working directory inside the container to /app. This is where the subsequent commands will be executed.

- COPY pom.xml ./: Copies the pom.xml file from the local directory into the container's /app directory.

- COPY src ./src: Copies the source code from the local src directory into the container's /app/src directory.

- RUN mvn clean install: Executes the Maven command mvn clean install inside the container, which builds the Spring Boot application and generates the executable JAR file.

#### Stage 2: Minimal runtime environment
- FROM eclipse-temurin:17-jre-jammy: Specifies the base image for the second stage, which is a minimal Java runtime environment based on the Eclipse Temurin (formerly AdoptOpenJDK) image with Java 17.

- WORKDIR /app: Sets the working directory to /app inside the container.

- COPY --from=builder /app/target/*.jar /app/app.jar: Copies the generated JAR file from the builder stage (/app/target/*.jar) to the current directory (/app) in the second stage and renames it as app.jar.

- EXPOSE 8080: Exposes port 8080 to allow inbound connections to the container.

- ENTRYPOINT ["java", "-jar", "/app/app.jar"]: Specifies the command that should be run when the container starts. It runs the Java command to execute the app.jar file.


## To build a image docker
```
docker build -t spring-boot-demo .
```

## Run image 
```
docker run -d -p 8080:8080 --name spring-boot-app spring-boot-demo
```
## Check image in your server/pc
```
docker image ls
```
## Check container in your server/pc

```
docker ps -a
```
Then, you can access app by http://localhost:8080

# Deploy spring boot application to server simple
### Prerequisite
- A VPS/Instance
- Installed docker

### SSH to server
```
ssh -p 22 <your_user>@<your_ip>
```
example: ssh -p 22 root@127.12.1.1
then, enter a password

### Install Docker in your server
```
apt install docker.io
```

### Install mysql for your server
```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name mysql -v ./src/main/resources/init.sql:/docker-entrypoint-initdb.d/init.sql mysql:8.0
```
### Build a image docker on your server
```
docker build -t spring-boot-demo .
```
### Finally, start your container on your server 
```
docker run -d -p 8080:8080 --name spring-boot-app spring-boot-demo
```

### Then build image and run that image as directed above
You can now access the Hello World page via the web browser:  http://<your_ip_server>:8080


# Or More simple way,  Use docker compose to deploy =))
### install docker-compose for your ubuntu server
```
apt-get update && apt-get install docker-compose-plugin
```
in folder include docker-compose file, only run:
```
docker-compose up -d
```
