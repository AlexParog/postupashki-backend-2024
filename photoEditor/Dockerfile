# Используем базовый образ Liberica JDK 17
FROM bellsoft/liberica-openjdk-debian:17

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR файл в контейнер
COPY target/hm_1_photoEditor-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду для запуска вашего Spring Boot приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

# Указываем порт, который будет использовать ваше приложение
EXPOSE 8080