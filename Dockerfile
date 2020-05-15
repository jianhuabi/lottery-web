FROM openjdk:8-jre-alpine

ENV APP_JAR_NAME lottery-web
ENV APP_HOME /opt/${APP_JAR_NAME}
ENV APP_JAR ${APP_JAR_NAME}.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD target/*.jar ${APP_HOME}/${APP_JAR}
ADD docker-entrypoint.sh /docker-entrypoint.sh
WORKDIR ${APP_HOME}
ENV TZ=Asia/Shanghai LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8 _JAVA_OPTIONS='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

RUN apk upgrade --update && apk add su-exec && \
        sh -c 'touch ${APP_HOME}/${APP_JAR}' && \
        chmod a+x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 8090 5005 9090