# Getting into the Flow with Business Flow Management

This repository contains all sources and slides to replay the Tutoring-Session *Getting into the Flow with Business Flow Management*.

## Run

* There is no main class, all **State Pattern** and **State Machine** examples are in the packages `naive`,`statepattern` and `enumstate`
* Persistent States with Spring State Machine can be tested by starting `PersistentStateApplication`
* For the Drools Example see https://springhow.com/spring-boot-drools/
* For the Camunda Workflow Example, use: `docker run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:run-latest`
  * Install the Modeller
  * Open and Deploy `src/main/resources/camunda`
