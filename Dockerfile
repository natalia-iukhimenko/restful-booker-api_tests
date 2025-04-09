FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . /app
RUN mvn dependency:resolve
ENTRYPOINT ["mvn", "test"]