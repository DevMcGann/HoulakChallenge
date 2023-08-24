# HoulakChallenge
Challenge for Houlak with SpotifyAPI

#UI
Im using Jetpack compose,  because it is the new way of doing Android (and match iOS development with SwiftUI).
Declarative UI its in my opinion easier and faster to develop than XML,   and they re more eficient. 
And a big reason for me is to get rid of those complex adapters for Recycler Views, and complexity of the fragments/activities. 

#Architecture
Clean + Mvvm,  the usual idea of divide into layers such as Data, Domain and Presentation,  and inside presentation views and viewmodels.  

#Dependency Injections
Dagger Hilt of course,  the easiest one and the most used as well.  

#Unit Testing
i try to use Mock the most possible.  

#How this project works? 
As soon as the app starts,  SplashScreen is showed.   Behind the courtains,  this is getting the access token to use de Spotify API.  
When we got the token,   the isAuth state gets a "true" value,  and the app natigate to the next screen. 

SearchScreen...  In this screen,  we can type the query to search artist in the Textfield,  then tap the Search Icon and wait until the API bring the result to us, it will send the artists ordered by popularity. 
Meanwhile we can observe the Welcome message,  which also works as a visual cue when something got wrong and displays an Error message.   
Once the list of artists get rendered,  we can tap on any of them and navigate to next screen. 

Detail Screen... In this screen,  all the relevant info about the artist is showed.  Like an Image,  Name,  Genres and top 5 songs (ordered by popularity)

