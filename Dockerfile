FROM adoptopenjdk/openjdk11:ubi
EXPOSE 8080
ADD /target/forum-0.0.1-SNAPSHOT.jar forum_v01.jar
ENTRYPOINT ["java", "$JAVA_OPTS -XX:+UseContainerSupport", "-Xmx300m -Xss512k -XX:CICompilerCount=2", "-Dserver.port=$PORT", "-Dspring.profiles.active=prd", "-jar", "forum_v01.jar"]
#RUN mkdir /opt/app
#COPY japp.jar /opt/app
#CMD ["java", "-jar", "/opt/app/japp.jar"]