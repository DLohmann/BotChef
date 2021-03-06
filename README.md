# BotChef
This app will use machine learning to suggest recipes to a user based on the ingredients, preferences, and dietary restrictions that the user has

## The Plan:

### Basic Use Case:
##### 1) Type in the ingredients you have
	- We will create the app in Android
	
##### 2) Press enter. The app sends the list of ingredients to the BotChef server, where they are stored along with the user preferences.
	- The Android app sends a HTTP request to the web server along with a serialized list of ingredients. 
	- The web server returns a JSON response containing a list of recipes ranked. 
	
##### 3) Server finds a list of recipes that can be made with those ingredients
	- We will use the ____ API to look up recipes based on ingredients
	- We will the recipe data at this link: https://eightportions.com/datasets/Recipes/
	- We will add all this data to a database on the server
	
##### 4) Server sorts recipes based on the user's preferences, and what most popular.
	- We will use __Tensorflow???___ API to do machine learning of best recipe??
	- We will use ___ API to perform suggestions
	- We will use Pandas to sort the recipes???
	
##### 5) Server sends top 5 recipes to the app
	- Server sends data over HTTP POST request
	- recipes sent to app in a JSON file
	
##### 6) User chooses a recipe in the app. The chosen recipe is sent to the server.
	- Chosen recipe data is sent via HTTP POST request
	
##### 7) Server receives the user's chosen recipe, and stores this data for later in the database(along with other user's preferences). Update recipe popularity data, and machine learning model.
	- The database API should allow for updating data
	
##### 8) Server sends user more recipe suggestions to be displayed next time the app is opened
	- Server sends suggestion data in an HTTP POST request
	- Suggestion data sent in JSON file
