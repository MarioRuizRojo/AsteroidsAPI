FROM adoptopenjdk/maven-openjdk11 as javacompiler

#copy sources
COPY . /

#compile it
RUN mvn package 

FROM mongo

#copy jar file from stage before
COPY --from=javacompiler target /target

#start mongo and deploy it
CMD sudo service mongod start && java -jar /target/nasa-client-0.0.1-SNAPSHOT.jar

EXPOSE 8090
#to test the app:
#http://localhost:8090/api/largestbyyear?year=1995
#http://localhost:8090/api/tenclosest?start_date=1995-01-01&end_date=1995-01-08