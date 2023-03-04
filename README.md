# Created a Rest API to fetch the details of the specific timezone.

API will take the timezone as input parameter.
API will validate that the timezone parameter is within the US timezone.
 1. API will throw a custom exception "NotUSTimeZone" if timezone is not US timezone.
 2. API will return error if the parameter(timezone) is not a valid timezone.
 
 
 Endpoint: 
 
 eg:
 http://localhost:8080/time/?timezone=America/Chicago
