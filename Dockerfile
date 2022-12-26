FROM maven:latest
WORKDIR /MacroAndMealsAPI
COPY . /MacroAndMealsAPI
ARG DB_USERNAME
ENV DB_USERNAME ${DB_USERNAME}
ARG DB_PASSWORD
ENV DB_PASSWORD ${DB_PASSWORD}
ARG DB_HOST
ENV DB_HOST ${DB_HOST}
ARG DB_NAME
ENV DB_NAME ${DB_NAME}
RUN mvn clean install -DskipTests=true
EXPOSE 8080
CMD ["java","-jar", "/MacroAndMealsAPI/target/macrosAndMeals-api-1.0-SNAPSHOT.jar", "server"]