FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY /app .

RUN gradle installDist

EXPOSE 7070

CMD ./build/install/app/bin/app