FROM maven:3.6.1-amazoncorretto-8 as BUILD

#ADD m2.tar.gz /root

COPY . /usr/src/app
RUN mvn -Dmaven.repo.local=/root/m2 --batch-mode -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-alpine

ENV APP_JAR_NAME lottery-web
ENV APP_HOME /opt/${APP_JAR_NAME}
ENV APP_JAR ${APP_JAR_NAME}.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY --from=BUILD /usr/src/app/target/*.jar ${APP_HOME}/${APP_JAR}
ADD docker-entrypoint.sh /docker-entrypoint.sh
WORKDIR ${APP_HOME}
ENV TZ=Asia/Shanghai LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8 _JAVA_OPTIONS='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

RUN apk upgrade --update && apk add su-exec && \
        sh -c 'touch ${APP_HOME}/${APP_JAR}' && \
        chmod a+x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lottery-web.jar"]

EXPOSE 8090 5005 9090