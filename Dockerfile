FROM openjdk:8-alpine

COPY target/uberjar/htmx-cool.jar /htmx-cool/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/htmx-cool/app.jar"]
