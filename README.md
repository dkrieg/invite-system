## Invite System Run Setup
1. From the project root
   1. gradlew build -x test
   2. docker-build.bat (on Windows) or docker-build.sf (on Mac or Linux)
   3. wait for script to complete...it takes time but is fairly reliable
2. Access the API Gateway Swagger UI
   1. http://localhost:8983/webjars/swagger-ui/index.html
   2. Ensure that you receive documentation for each service listed in the 'Select Definition'
3. Try out some endpoints
   1. From 'addresses'
      1. Execute 'Get All States'
   2. From 'amenities'
      1. Execute 'Get All Amenity Types'
   3. From 'benefits'
      1. Execute 'Get All Benefit Types'
   4. From 'members'
      1. Execute 'Get All Members'
4. Once all these tests are verified, you can execute a 'process flow'
   1. Details to follow