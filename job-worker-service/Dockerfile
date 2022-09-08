FROM adoptopenjdk/openjdk11:ubi
ENV APP_FILE job-worker-service.jar
ENV APP_HOME /app
COPY build/libs/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]