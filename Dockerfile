FROM openjdk:11
ADD target/movieapp.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://moviemongodb:27017","-jar","app.jar"]