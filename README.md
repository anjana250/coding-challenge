
## What you need to get started
- A favorite text editor or IDE (I used IntelliJ)
- JDK 8 or later
- Gradle Installed (I used version 7.1)
- Postman

## Instructions on how to import gradle project in IntelliJ
1. If no project is currently opened in IntelliJ IDEA, click Open on the welcome screen. Otherwise, select File | Open from the main menu.
If you have some custom plugins that require you to import your project from the IntelliJ IDEA model, press Ctrl+Shift+A and search for the Project from Existing Sources action.

2. In the dialog that opens, select a directory containing a Gradle project and click OK.
IntelliJ IDEA opens and syncs the project in the IDE.

3. Install and Enable the Lombok plugin (Already enabled in newer versions of IntelliJ)

## Instructions on how to run and test project in IntelliJ
1. After the sync of all gradle dependencies is complete
2. Go to Gradle Tasks -> application -> Run  bootRun
3. After application starts, navigate to: http://localhost:8080/h2-console/login.jsp
4. Put in the password that is in the application.properties file (located: src/main/resources/application.properties). 
5. Make sure you use the jdbc driver listed in the application.properties 
- (spring.datasource.url=jdbc:h2:mem:testdb)
- Please note that the database gets recreated with each deployment and data will get deleted

## Instructions on how to use the API

Default bootRun script in Spring uses localhost and port 8080. In case you wish to change the port, modify application.properties as below
server.port=8081

Use Postman or a similar tool to test the API. Here are the links for reference:
| HTTP   |  Method Route | Description | 
| ------------- | ------------- | ------------- |
| GET | http://localhost:8080/contacts |  List all contacts|
| POST | http://localhost:8080/contacts | Create a new contact|
| PUT | http://localhost:8080/contacts/{id} | Update a contact |
|GET  | http://localhost:8080/contacts/{id} | Get a specific contact |
| DELETE | http://localhost:8080/contacts/{id} | Delete a contact |
|GET  | http://localhost:8080/contacts/call-list | Get a call list |


3. First Name and Last Name are mandatory fields. If a create request does not provide any of these required values, an invalid request error message is returned. You will also get this error if there are other issues with the JSON request such as the phone number type is invalid. This is only applicable for POST and PUT
 
4. For GET by ID, DELETE by ID and PUT by ID, you will get an error if you attempt to send an ID value that is not an integer OR not an integer that already exists in the database.


## Instructions on how to import gradle project in Eclipse
http://makble.com/how-to-import-gradle-project-into-eclipse



