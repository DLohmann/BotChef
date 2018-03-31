# BotChef
This app will use machine learning to suggest recipes to a user based on the ingredients, preferences, and dietary restrictions that the user has

## The Plan:

### Basic Use Case:
##### 1) Type in the ingredients you have
	- We will create the app in Android
	
##### 2) Press enter. The app sends the list of ingredients to the BotChef server, where they are stored along with the user preferences.
	- We will use Python __<b>requests???</b>__ library to send an HTTP POST request to send the data
	- The ingredients will be sent in a JSON file
	
##### 3) Server finds a list of recipes that can be made with those ingredients
	- We will use the ____ API to look up recipes based on ingredients
	
##### 4) Server sorts recipes based on the user's preferences, and what most popular.
	- We will use __Tensorflow???___ API to do machine learning of best recipe??
	- We will use ___ API to perform suggestions
	- We will use Pandas to sort the recipes???
	
##### 5) Server sends top 5 recipes to the app
	- Server sends data over HTTP POST request
	- recipes sent to app in a JSON file
	
##### 6) User chooses a recipe in the app. The chosen recipe is sent to the server.

##### 7) Server receives the user's chosen recipe, and stores this data for later in the database(along with other user's preferences). Update recipe popularity data, and machine learning model.

##### 8) Server sends user more recipe suggestions to be displayed next time the app is opened
	- Server sends suggestion data in an HTTP POST request
	- Suggestion data sent in JSON file