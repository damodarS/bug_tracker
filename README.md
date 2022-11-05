## Bug Tracker

### Purpose
Application aims to serve below functionalities
1. Create bugs.
2. Find bug by bug id.
3. Search bugs basis of dynamic search criteria.
4. Update bug details.
5. Delete bug.

### Build the application

Build application using
`mvn clean package`

### Run the application

After build done, Application can run using below command

`java -jar target/bug-tracker-0.1.jar`

Please note, pre-requisite for above, local machine should have below
1. Java8
2. Maven > 3.6.0

In case, prerequisite not met, alternative choice is to run using Docker. Below are steps.
1. create image `docker build -t bug-tracker:1.0 .`
2. run `docker run -d -p 8080:8080 -t bug-tracker:1.0`


### Database

Applicaton uses in-memory (h2) databases. use below url to launch h2 console. please note, 
1. No password set for this application yet, so click on connect should take you to landing page.
2. Ensure, JDBC URL entry is :  `jdbc:h2:mem:testdb`

http://localhost:8080/h2console/login.jsp

### API Documentation

Launch Open API (swagger) http://localhost:8080/swagger-ui/index.html#/

### Assumptions: 
1. 3 users(alice, bob, john) created by default during application boot time. so bug reporter, assignee can be any of three users.
2. Bug Status:  [here](src/main/java/com/ratepay/bugtracker/constants/BugStatus.java)
3. Bug Severity values: [here](src/main/java/com/ratepay/bugtracker/constants/BugSeverity.java)

### Test end points

1. create bug

`curl -X 'POST' \
   'http://localhost:8080/bug' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "title": "Users unable to verify address",
   "description": "Address verification not successful",
   "reporter": "alice",
   "severity": "MAJOR"
   }'`

2. Find bug by Id 

`curl -X 'GET' \
'http://localhost:8080/bug/BUG_0001' \
-H 'accept: */*'`

3. Update bug

_`curl -X 'PUT' \
'http://localhost:8080/bug/BUG_0001?severity=MAJOR' \
-H 'accept: */*'`_

4. Search bugs with dynamic filter criteria

`curl -X 'POST' \
'http://localhost:8080/bug/search?pageNum=0&pageSize=10' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"searchCriteriaList": [
{
"filterKey": "reporter",
"value": "alice",
"operation": "eq",
"dataOption": "all"
}
],
"dataOption": "all"
}'`

5. Delete bug

`curl -X 'DELETE' \
'http://localhost:8080/bug/BUG_0001' \
-H 'accept: */*'`

### To stop a container by container id

1. `docker ps` >> Lists all containers
2. find container Id whose name is _**bug-tracker**_, and Stop a container by container id.
   1. `docker stop <container_id>`
