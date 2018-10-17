#############
# Build App
FROM openjdk:10-jre-slim as build
MAINTAINER Simon Karalyus <skaralyus@gmail.com>

ENV APP_HOME=/root/dev/

RUN mkdir -p $APP_HOME/src/main/java
RUN mkdir -p $APP_HOME/src/main/resources
RUN mkdir -p $APP_HOME/src/test/java
RUN mkdir -p $APP_HOME/src/test/resources

WORKDIR $APP_HOME
COPY gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
COPY gradle.properties $APP_HOME/gradle.properties
# download gradle
RUN ./gradlew --continue

# for cache all dependencies
RUN touch "class GradleCache {}" > $APP_HOME/src/main/java/GradleCache.java
RUN touch "name=value" > $APP_HOME/src/main/resources/gradlecache.properties
RUN touch "class GradleCacheTest {}" > $APP_HOME/src/test/java/GradleCacheTest.java
RUN touch "name=value" > $APP_HOME/src/test/resources/gradlecache.properties

COPY build.gradle $APP_HOME

# download dependencies
RUN ./gradlew build --continue

ADD src $APP_HOME/src
RUN ./gradlew build

#############
# Final image
FROM openjdk:10-jre-slim

ARG VERSION=0.0.1-SNAPSHOT

ENV SERVICE_NAME discovery
ENV VERSION $VERSION
ENV APP_JAR $SERVICE_NAME-$VERSION.jar

ENV DISCOVERY_SERVICE_PORT=8761

ENV JAVA_OPTS=""

WORKDIR /root/
COPY --from=build /root/dev/build/libs/*.jar /usr/src/masmovil/$APP_JAR

WORKDIR /usr/src/masmovil
EXPOSE 8761

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar $APP_JAR"]

