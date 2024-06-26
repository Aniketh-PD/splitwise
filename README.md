# Backend api endpoints for Splitwise application

### About
- replicated key features found in the Splitwise application, including the ability to create new expenses, retrieve user information, and facilitate group settlement functionality.
- Tech Stack - Springboot,MySql DB

### Api Endpoints :-

  
| api url      | api request type |  description
| ----------- | ----------- |   ------------
| /expense/add      | POST      |  add a new expense
| /user/id   | GET        | gets the requested user based on id
| /settleUp//group/{groupId} | GET | gets the list expenses to be paid or owed by users in a group to settle up expenses
| /settleUp/{groupId}/user/{userId} | GET | gets the list of expenses that needs to be paid by or owed by a individual user within a group

### Dependecies used :-
- Spring Boot Maven
- Spring Web
- Spring Configuration Processor
- Spring Data JPA
- Spring Boot DevTools
