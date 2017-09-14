# example project
A project for logging in console, file and database using Java

# What you need
- MySQL
- Maven

# Developer notes
- Create the DB with SQL script that can be found at "src/main/resources" folder (script.sql) - NOT MANDATORY
- Configure the DB connection parameters that can be found at "src/main/resources" folder (config.properties) - NOT MANDATORY

# About this test
- Code improvements were requested for review, please read the code comments in the "JobLogger.old" file located at ""src/main/resources" folder
- The new class containing the refactored code from the "JobLogger" class was named "LogUtil.java"
- The JUnit tests (just simple ones) can be found at "LogUtilTest.java"
