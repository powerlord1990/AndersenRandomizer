# Andersen spring random project

This application should return random pair of students, where 1st is 
questioner,2nd is respondent;
* If question is good, user can receive point
* If answer is good, user can receive point, else user gets nothing or subtract a point
* User's from same team, can't ask a question to each other, 
but if last 2 user's in queue from same team - they must ask question to each other.

## How to use Excel import:

1. Make this format of excel import data:

![Example of excel format](src/main/resources/static/imgs/excel.png)

2. Set path of your's excel file to that input block

![Input place](src/main/resources/static/imgs/excel2.png)

3. Press "Send path" button

![Button to import](src/main/resources/static/imgs/excel3.png)

4. ... Done

## How to import data with textArea

1. Make text like this format:

![Example of text](src/main/resources/static/imgs/text.jpg)

2. Set path of your's excel file to that input block

![Input place](src/main/resources/static/imgs/text2.png)

3. Press "send" button

![Send btn](src/main/resources/static/imgs/text3.png)

4. ... Done

TODO:
* [x] Add text validator, which consume some String and receive List of Students to Repository
* [x] Add function to import .xlsx to database
* [ ] Add new frontend realization
* [x] Code refactoring

Used stack:
* JDK 11
* Spring boot
* AngularJs

Dependencies:
* Spring web
* Spring data JPA
* Spring test
* Thymeleaf (AngularJs - frontend)
* H2 (PostgreSQL)
* Lombok
