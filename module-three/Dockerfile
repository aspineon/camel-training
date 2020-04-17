FROM openjdk:11.0.4-stretch
ADD target/orders.jar /
CMD java -jar orders.jar \
    -Dspring.profiles.active=$ACTIVE_PROFILE