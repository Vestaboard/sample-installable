FROM openjdk:11 AS BUILD_IMAGE
WORKDIR /app
COPY build.gradle.kts gradlew gradlew.bat ./
COPY . .
RUN ./gradlew build

FROM openjdk:11-jre
WORKDIR /root/
COPY --from=BUILD_IMAGE /app/build/libs/shadow-1.0-SNAPSHOT.jar .
EXPOSE 7005
