# What you need to get started
- A favorite text editor or IDE (I used IntelliJ)
- JDK 8 or later
- Gradle Installed (I used version 7.1)
- Postman

# Instructions on how to import gradle project in IntelliJ
1. If no project is currently opened in IntelliJ IDEA, click Open on the welcome screen. Otherwise, select File | Open from the main menu.
If you have some custom plugins that require you to import your project from the IntelliJ IDEA model, press Ctrl+Shift+A and search for the Project from Existing Sources action.

2. In the dialog that opens, select a directory containing a Gradle project and click OK.
IntelliJ IDEA opens and syncs the project in the IDE.

3. Install and Enable the Lombok plugin (Already enabled in newer versions of IntelliJ)

# Instructions on how to run and test project in IntelliJ
1. After the sync of all gradle dependencies is complete
2. Go to Gradle Tasks -> application -> Run  bootRun
3. After application starts, navigate to: http://localhost:8080/h2-console/login.jsp
4. Put in the password that is in the application.properties file (located: src/main/resources/application.properties). 
5. Make sure you use the jdbc driver listed in the application.properties 
spring.datasource.url=jdbc:h2:mem:testdb

6. Now you can test all of the scenarions in the Code Challenge Document in Postman

# Instructions on how to import gradle project in Eclipse
http://makble.com/how-to-import-gradle-project-into-eclipse



