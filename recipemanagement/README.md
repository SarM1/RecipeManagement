#Recipe Management System
Recipe Management system to create and maintain Recipes .

#About the application
User could provide  following details in input:
* Procedure to prepare the dish.
* Type of recipe ( Vegetarian  or Non Vegetarian)
* No of people for whom recipe is suitable
* All the ingredients used in the Recipe .Ingredients are pulled from a master list of ingredients .
* Quantity of each ingredient used  for the preparation.
* System would insert the time stamp of creation for the   recipe .Also this time stamp would be created for each ingredient
*In memory authentication is provided .


#Install & Running
##Pre-requisites
* Java 1.8
* Maven 3.3 + Build tool 
* Db H2

#Build and Run

* dev mode:
 	mvn spring-boot:run -Dspring-boot.run.profiles=dev

* prod mode:
	mvn spring-boot:run -Dspring-boot.run.profiles=prod

#DB:
 Following are the tables :
 Recipe
 Ingredient
 RecipeDetails(many  to one relationship with Recipe and Ingredient )
 


#Rest services 
/recipemanagement is the context root for the application 
CRUD operations for Recipe  and Ingredients are  available-

* /recipes/{id}(request type GET to fetch and DELETE to delete the recipe)
* /recipes/all (request type GET to fetch all the recipes )
* /recipes/ (request type POST or PUT)

* /ingredients/{id}(request type GET to fetch and DELETE to delete the ingredient)
* /ingredients/all (request type GET to fetch all the ingredients )
* /ingredients/ (request type POST or PUT)

#Built With 

* Spring  Boot version: 2.3.9
* Java Version: 1.8
* Maven version 3.3: Dependency Management

#Authors
* Sarika




