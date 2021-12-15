FROM mcr.microsoft.com/java/jdk:8u265-zulu-alpine
LABEL mantainer="Ronal Ricse"
ARG nombreArtefacto=Service_Demo-0.0.1.jar
ENV nombreArtefacto ${nombreArtefacto}
RUN apk update && apk upgrade \
	&& apk add ca-certificates \
	&& update-ca-certificates \
	&& apk add --update tzdata
ENV TZ=America/Lima
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY /target/${nombreArtefacto} /opt/
WORKDIR /opt/
CMD ["sh", "-c", "java -jar ${nombreArtefacto}"]
RUN rm -rf /var/cache/apk/*