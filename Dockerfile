FROM bellsoft/liberica-openjdk-alpine:latest
RUN apk add -U tzdata
ENV TZ America/Lima
RUN ln -snf /usr/share/zoneinfo/${TZ} /etc/localtime
RUN echo "${TZ}" > /etc/timezone
VOLUME /tmp
EXPOSE 8002
ADD ./target/ms-item-0.0.1-SNAPSHOT.jar ms-item.jar
ENTRYPOINT ["java", "-jar", "/ms-item.jar"]