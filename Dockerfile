FROM openjdk:17
COPY ".target/quiz_3ercorte-1.jar" "app.jar"
EXPOSE 8140
ENTRYPOINT [ "java", "-jar", "app.jar" ]