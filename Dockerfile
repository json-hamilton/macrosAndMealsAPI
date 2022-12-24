FROM maven:latest
WORKDIR /MacroAndMealsAPI
COPY . /MacroAndMealsAPI
RUN mvn clean install -DskipTests=true
EXPOSE 8080
CMD ["java","-jar", "/MacroAndMealsAPI/target/macrosAndMeals-api-1.0-SNAPSHOT.jar", "server"]