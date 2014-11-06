A very simple android app communicating with a very simple backend service via REST requests.

Based on spring.io REST tutorials:
* https://spring.io/guides/gs/actuator-service/
* https://spring.io/guides/gs/consuming-rest-android/

Comprises of three projects:
CommonRestDependencies - where shared POJOs/DTOs live (just the Greeting POJO in this example)
BackendRestService - spring boot service that uses embedded tomcat to make life easy
AndroidRestClient - simple android app that communicates with the backend service


Eclipse:

* File > Import > Existing Maven Projects > RestExperiment
* Import both projects (CommonRestDependencies and BackenRestService)
* Right click rest-common project, run as > maven install
* Expect 'build success' message in console, pom and jar installed in local m2 repository
* Right click rest-service project, run as > java application, select Application class
* Expect spring 'started application' message in console


Web Browser:

* http://localhost:8080/greeting or http://localhost:8080/greeting?name=TheInvader360
* Expect greeting to be displayed in browser page (json)


Android Studio:

* Import Project > RestExperiment/AndroidRestClient
* Connect android device via USB (ensure ADB enabled)
* Run 'app' > select connected device > ok
* Set Host IP value (i.e. the IP of the server) and optionally Your Name, then Submit
* Expect toast (greeting or error)
