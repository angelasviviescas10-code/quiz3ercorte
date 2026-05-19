FROM eclipse-temurin:21-jdk
COPY "target/quiz_3ercorte-1.jar" "app.jar"
EXPOSE 8140
ENTRYPOINT [ "java", "-jar", "app.jar" ]