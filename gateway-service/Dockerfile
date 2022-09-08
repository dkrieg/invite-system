FROM adoptopenjdk/openjdk11:ubi
ENV APP_FILE gateway-service.jar
ENV APP_HOME /app
EXPOSE 8983
COPY build/libs/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]