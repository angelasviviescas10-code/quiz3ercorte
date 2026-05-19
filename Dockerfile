FROM eclipse-temurin:17-jdk
COPY "target/quiz_3ercorte-1.jar" "app.jar"
EXPOSE 8140
ENTRYPOINT [ "java", "-jar", "app.jar" ]