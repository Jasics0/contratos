# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copia solo el archivo pom.xml para descargar dependencias
COPY pom.xml .

# Descarga las dependencias
RUN mvn dependency:go-offline

# Copia el resto de los archivos y compila la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa final
FROM amazoncorretto:21-alpine-jdk

WORKDIR /app
# Copia solo los archivos necesarios, incluido el JAR compilado
COPY --from=build /app/target/contratos-1.0-jar-with-dependencies.jar /app/contratos.jar

# Copia el archivo de configuración Hibernate desde la ubicación de origen
COPY /src/main/resources/hibernate.cfg.xml /app/

# Expone el puerto si tu aplicación usa algún puerto específico
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "contratos.jar"]
