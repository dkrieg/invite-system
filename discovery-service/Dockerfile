FROM adoptopenjdk/openjdk11:ubi
ENV APP_FILE discovery-service.jar
ENV APP_HOME /app
EXPOSE 8981
EXPOSE 8982
COPY build/libs/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]