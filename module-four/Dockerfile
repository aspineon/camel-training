FROM openjdk:11.0.4-stretch
ADD target/camel.jar /
CMD java -jar camel.jar \
    -Dfiles.path=$FILES_PATH \
    -Dorders-service=$ORDERS_SERVICE