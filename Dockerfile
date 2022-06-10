FROM amazoncorretto:16
COPY target/CurrencyService-1.0-SNAPSHOT.jar CurrencyService-1.0-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/CurrencyService-1.0-SNAPSHOT.jar"]