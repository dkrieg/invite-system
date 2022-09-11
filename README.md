## Invite System Run Setup

1. From the project root
    1. gradlew build -x test
    2. docker-build.bat (on Windows) or docker-build.sh (on Mac or Linux)
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

### Important URLs

1. gateway-service - http://localhost:8983/webjars/swagger-ui/index.html
    - an API gateway that the UI will interact with for all REST API calls
2. discovery-service - http://localhost:8981/
    - a Eureka server that provides 'location transparency' for the micro-services environment
3. Camunda Tasklist - http://localhost:8082/
    - a system admin screen to view tasks in Camunda. This is different from the custom '
      'management-ui' app in the 'invite-system' which offers 'business user-friendly experience'
4. Camunda Operate - http://localhost:8081/
    - a system admin screen to view processes in Camunda.

### New Organization Process

1. http://localhost:8983/processes/start/new-organization

```
Starts the 'New Organization Process'
```

2. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

3. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Organization' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-organization-form'
The form should use the following variables to display:
  - communities - if not empty, display a 'select' element
  - markets - if not empty, display a 'select' element
  - segments - display a 'select' element
  - providerGroups - display a 'select' element
  - states - display a 'select' element
  
  The form should provide 'input' elements for:
  - *Name
  - Community (if rendered - need better requirements understanding on this)
  - Market (if rendered - need better requirements understanding on this)
  - *Provider Group
  - *Address Line 1
  - *City
  - *State - default element is 'Select State'
  - *Zip Code - initially disabled until a State is selected.  
               When a State is selected, this 'select' element is populated via
               http://localhost:8983/addresses/{state}/zip-codes
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new organization is submitted
```

4. http://localhost:8983/processes/submit/new-organization

```
Fill out form with following information and submit new organizations - i.e. executed on Submit button
{
  "name": "Peachtree City Club A",
  "providerGroupId": 2,
  "segment": "Blue",
  "address": {
    "line1": "123 Somewhere",
    "city": "Peachtree City",
    "state": "GA",
    "zipCode": {
      "postalCode": "30269"
    }
  }
}

Fill out form with following information and submit new organizations - i.e. executed on Submit button
{
  "name": "Peachtree City Club B",
  "providerGroupId": 2,
  "segment": "Blue",
  "address": {_
    "line1": "123 Somewhere",
    "city": "Peachtree City",
    "state": "GA",
    "zipCode": {
      "postalCode": "30269"
    }
  }
}

Fill out form with following information and submit new organizations - i.e. executed on Submit button
{
  "name": "Peachtree City Club C",
  "providerGroupId": 2,
  "segment": "Blue",
  "address": {
    "line1": "123 Somewhere",
    "city": "Peachtree City",
    "state": "GA",
    "zipCode": {
      "postalCode": "30269"
    }
  }
}
```

5. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

6. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

7. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Amenity' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-amenity-form'
The form should use the following variables to display:
  - amenityTypes - display a 'select' element
  - organization - display contents on the form page
  
  The form should display text for following organization information:
  - Organization - {organziation.name}
  - Community - (rendered if not empty) {organization.community.name}
  - Market - (rendered if not empty) {organization.community.name}
  - Provider Group - {organziation.providerGroup.name}
  - Segment - {organziation.segment.name}
  - Address Line 1 - {organization.address.line1}
  - City - {organization.address.city}
  - State - {organization.address.state}
  - Zip Code - {organization.address.zipCode.postalCode}
  
  The form should provide 'input' elements for:
  - *Amenity Name
  - *Amenity Type
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new amenity is submitted  
```

8. http://localhost:8983/processes/submit/new-amenity

```
Assuming the Organization is "Peachtree City Club A"
  - Select 'GOLF' from Amenity Types
  - Using {organization.id} - not rendered on form
  
Fill out form with following information and submit new amenity - i.e. executed on Submit button
{
  "name": "Golf Course A",
  "type": "GOLF",
  "organizationId": 1
}
```

9. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

10. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

11. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Amenity' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-amenity-form'
The form should use the following variables to display:
  - amenityTypes - display a 'select' element
  - organization - display contents on the form page
  
  The form should display text for following organization information:
  - Organization - {organziation.name}
  - Community - (rendered if not empty) {organization.community.name}
  - Market - (rendered if not empty) {organization.community.name}
  - Provider Group - {organziation.providerGroup.name}
  - Segment - {organziation.segment.name}
  - Address Line 1 - {organization.address.line1}
  - City - {organization.address.city}
  - State - {organization.address.state}
  - Zip Code - {organization.address.zipCode.postalCode}
  
  The form should provide 'input' elements for:
  - *Amenity Name
  - *Amenity Type
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new amenity is submitted  
```

12. http://localhost:8983/processes/submit/new-amenity

```
Assuming the Organization is "Peachtree City Club B"
  - Select 'GOLF' from Amenity Types
  - Using {organization.id} - not rendered on form
  
Fill out form with following information and submit new amenity - i.e. executed on Submit button
{
  "name": "Golf Course B",
  "type": "GOLF",
  "organizationId": 2
}
```

13. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

14. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

15. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Amenity' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-amenity-form'
The form should use the following variables to display:
  - amenityTypes - display a 'select' element
  - organization - display contents on the form page
  
  The form should display text for following organization information:
  - Organization - {organziation.name}
  - Community - (rendered if not empty) {organization.community.name}
  - Market - (rendered if not empty) {organization.community.name}
  - Provider Group - {organziation.providerGroup.name}
  - Segment - {organziation.segment.name}
  - Address Line 1 - {organization.address.line1}
  - City - {organization.address.city}
  - State - {organization.address.state}
  - Zip Code - {organization.address.zipCode.postalCode}
  
  The form should provide 'input' elements for:
  - *Amenity Name
  - *Amenity Type
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new amenity is submitted  
```

16. http://localhost:8983/processes/submit/new-amenity

```
Assuming the Organization is "Peachtree City Club C"
  - Select 'GOLF' from Amenity Types
  - Using {organization.id} - not rendered on form
  
Fill out form with following information and submit new amenity - i.e. executed on Submit button
{
  "name": "Golf Course C",
  "type": "GOLF",
  "organizationId": 3
}
```

17. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

18. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

19. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Benefit Package' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-benefit-package-form'
The form should use the following variables to display:
  - benefits - display a 'multi-select' element
  - organization - display contents on the form page
  
  The form should display text for following organization information:
  - Organization - {organziation.name}
  - Community - (rendered if not empty) {organization.community.name}
  - Market - (rendered if not empty) {organization.community.name}
  - Provider Group - {organziation.providerGroup.name}
  - Segment - {organziation.segment.name}
  - Address Line 1 - {organization.address.line1}
  - City - {organization.address.city}
  - State - {organization.address.state}
  - Zip Code - {organization.address.zipCode.postalCode}
  
  The form should provide 'input' elements for:
  - *Benefit Package Name
  - *Benefits - {benefit.description}
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new benefit package is submitted  
```

20. http://localhost:8983/processes/submit/new-benefit-package

```
Assuming the Organization is "Peachtree City Club A"
  - Select Benefit - {"code": "B0001", "description": "4 rounds per month within the Community, max of 2 per club"}
  - Using {organization.id} - not rendered on form
  
Fill out form with following information and submit new benefit package - i.e. executed on Submit button
{
  "name": "Package A",
  "organizationId": 1,
  "benefitsIds": [
    "B0001"
  ]
}
```

21. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

22. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

23. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Benefit Package' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-benefit-package-form'
The form should use the following variables to display:
  - benefits - display a 'multi-select' element
  - organization - display contents on the form page
  
  The form should display text for following organization information:
  - Organization - {organziation.name}
  - Community - (rendered if not empty) {organization.community.name}
  - Market - (rendered if not empty) {organization.community.name}
  - Provider Group - {organziation.providerGroup.name}
  - Segment - {organziation.segment.name}
  - Address Line 1 - {organization.address.line1}
  - City - {organization.address.city}
  - State - {organization.address.state}
  - Zip Code - {organization.address.zipCode.postalCode}
  
  The form should provide 'input' elements for:
  - *Benefit Package Name
  - *Benefits - {benefit.description}
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new benefit package is submitted  
```

24. http://localhost:8983/processes/submit/new-benefit-package

```
Assuming the Organization is "Peachtree City Club B"
  - Select Benefit - {"code": "B0002", "description": "Sunday Noon - Friday Noon; 7 Day advance tee times"}
  - Using {organization.id} - not rendered on form
  
Fill out form with following information and submit new benefit package - i.e. executed on Submit button
{
  "name": "Package B",
  "organizationId": 2,
  "benefitsIds": [
    "B0002"
  ]
}
```

25. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

26. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

27. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Benefit Package' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-benefit-package-form'
The form should use the following variables to display:
  - benefits - display a 'multi-select' element
  - organization - display contents on the form page
  
  The form should display text for following organization information:
  - Organization - {organziation.name}
  - Community - (rendered if not empty) {organization.community.name}
  - Market - (rendered if not empty) {organization.community.name}
  - Provider Group - {organziation.providerGroup.name}
  - Segment - {organziation.segment.name}
  - Address Line 1 - {organization.address.line1}
  - City - {organization.address.city}
  - State - {organization.address.state}
  - Zip Code - {organization.address.zipCode.postalCode}
  
  The form should provide 'input' elements for:
  - *Benefit Package Name
  - *Benefits - {benefit.description}
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new benefit package is submitted  
```

28. http://localhost:8983/processes/submit/new-benefit-package

```
Assuming the Organization is "Peachtree City Club C"
  - Select Benefit - {"code": "B0011", "description": "No Community Golf"}
  - Using {organization.id} - not rendered on form
  
Fill out form with following information and submit new benefit package - i.e. executed on Submit button
{
  "name": "Package C",
  "organizationId": 3,
  "benefitsIds": [
    "B0011"
  ]
}
```

29. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

30. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
No additional tasks to perform
```

### New Member Process

1. http://localhost:8983/processes/start/new-member

```
Starts the 'New Member Process'
```

2. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

3. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Member' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-member-form'
  
  The form should provide 'input' elements for:
  - *First Name
  - *Last Name
  - *Address Line 1
  - *City
  - *State - default element is 'Select State' - retrieved using http://localhost:8983/addresses/states
  - *Zip Code - initially disabled until a State is selected.  
               When a State is selected, this 'select' element is populated via
               http://localhost:8983/addresses/{state}/zip-codes
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new organization is submitted  
```

4. http://localhost:8983/processes/submit/new-member

```
Fill out form with following information and submit new member - i.e. executed on Submit button
{
  "firstName": "Daniel",
  "lastName": "Krieg",
  "email": "daniel.krieg@gmail.com,
  "address": {
    "line1": "123 Somewhere",
    "city": "Overland Park",
    "state": "KS",
    "zipCode": {
      "postalCode": "66221"
    } 
  }
}
```

5. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

6. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
No additional tasks to perform
```

### New Membership Process

1. http://localhost:8983/processes/start/new-membership

```
Starts the 'New Membership Process'
```

2. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
```

3. http://localhost:8983/processes/task-list/claim/{taskId}

```
Claim the 'Define New Membership' task using the Task ID.
Grab the 'form-key' from the result and display the 'define-new-membership-form'
  
  The form should provide 'input' elements for:
  - *Login ID
  - *Level - 'select' element default is 'Select Membership Level' - retrieved using http://localhost:8983/memberships/membership-levels
  - *Member - 'select' element default is 'Select Member' - retrieved using http://localhost:8983/members
  - *Organization - 'select' element default is 'Select Organization' - retrieved using http://localhost:8983/organizations
  - *Benefit Package - 'select' element initially disabled but enabled and populated when an organization is selected 
                        default is 'Select Benefit Package'
                        retrieved using http://localhost:8983/benefits//benefit-packages/with-organization/{organizationId}
  - Submit - Button disabled until required fields are set
  - Complete - Button preferably disabled until at least 1 new organization is submitted  
```

4. http://localhost:8983/processes/submit/new-membership

```
Fill out form with following information and submit new membership - i.e. executed on Submit button
{
  "loginId": "dkrieg",
  "level": "GOLD",
  "memberId": 1,
  "benefitPackageId": 1,
  "homeClubId": 1
}
```

5. http://localhost:8983/processes/task-list/complete/{taskId}

```
Complete the task using the Task ID
```

6. http://localhost:8983/processes/task-list/tasks

```
Retrieve Tasks created by the process 
No additional tasks to perform
```
