# Question service application

This is a Spring Boot web application project for demonstrates Q & A

# Pre-requisites
	1. Java 1.8 
	2. Maven version >=3.3.3
	3. Spring  
	5. Spring boot
	4. STS IDE  

# Requirement 
	Client: Hey Service, can you provide me a question with numbers to add?
	Service: Here you go, solve the question: “Please sum the numbers 9,5,3”.
	Client: Great. The original question was “Please sum the numbers 9,5,3” and the answer is 15.
	Service: That’s wrong. Please try again.
	Client: Sorry, the original question was “Please sum the numbers 9,5,3” and the answer is 17.
	Service: That’s great!

# Analysis
  	1. Client will send request asking for a question to add numbers
   	2. Server will response with a question of random numbers to solve
   	3. Client will read the question and sends back again with total sum of numbers
  	4. Server will read the question and verify the answer receives from request 
      		if correct then sends response as "That's" Great!" along with response code - 200
      		if not correct then sends response as  "That’s wrong. Please try again" and along with response code - 400

# Implementation :
  
    1. Spring boot with java 1.8 for developing application
	2. Maven for dependency management
	3. Swagger for Api Documentation
	4. Spring cache for cache implementation
	5. Junit for unit test case
	6. Logger for logging information
	7. server port cane be change assigning value to server.port 
   
#Authentication :  
Authentication can enable and disable by using the property value jwt.authentication-enabled
jwt.authentication-enabled=true means enable and false means disable

Generating JWT Token Using registered User details( Username/Password )
url     :  localhost:8080/authenticate
headers :  Content-Type : application/json
body	:  {
	"username": "apurba",
	"password": "password"
} or {
	"username": "admin",
	"password": "password"
}

Response : 
 {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcHVyYmEiLCJleHAiOjE2NTk0NDcyMjcsImlhdCI6MTY1OTQyOTIyN30.xiw8iMHu7WHeSkIDnw6AMCNGVGffv42zbPI3tQEYvlD_tGsNK-OMp8GxX6GRZiJtJSimB5npvx86tgqW-HsYEw"
}


# Service Application  Use Cases : 

When ever you send request to service, you need to include following header(headername: headervalue) for authorization
authorization : Bearer tokenvalue
 
 Valid Use Case :
 Client  : localhost:8080/question?question=Hey Service, can you provide me a question with numbers to add?
 Service : Here you go, solve the question: "Please sum the numbers 4,4,4" 
 Clinet  : localhost:8080/question/answer?input=Great. The original question was "Please sum the numbers 4,4,4" and the answer is 12.
 Service : That's Right!

 Invalid Valid Use Case :
 Client  : http://localhost:8080/question?text=Hey Service, can you provide me a question with numbers to add?
 Service : Here You  Go solve the question: "Please sum the numbers 4,4,4" 
 Clinet  : localhost:8080/question/answer?input=Great. The original question was "Please sum the numbers 3,4,4" and the answer is 17..
 Service : That’s wrong. Please try again.
   
    
# Swagger API 

http://localhost:8080/swagger-ui/

# Reference Documentation

Spring: https://start.spring.io/ )
Spring Boot: https://docs.spring.io/spring-boot/docs/2.5.6/reference/html/
jwt: https://www.javainuse.com/spring/boot-jwt



