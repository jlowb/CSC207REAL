# CSC207 Group 113

## 0. Problem Domain

After the brainstorming session in our tutorial, our team has tentatively decided to create a **music player**. 
This decision was motivated by the abundance of features that could be implemented,
ensuring that the project would be interesting for every single group member.

## 1. Project Description

We hypothesize that the key features of a music player (that would need to be implemented) are:
1. Login System
   1. User creation/management (probably via an API like Oauth)
   2. Login screen (needs a frontend)
   3. Routing/auth flow (probably via an API like Oauth)
2. User Customization System
   1. Friends list (optional)
   2. Profile customization (optional)
   3. Personal music collection, i.e. playlists etc. (essential)
3. Music Database
4. Music Player
   1.  Music Player Component
   2.  Music Queue/Recommender/Shuffle/History
5. Front End



## 2. Link to API documentation:
https://developer.musixmatch.com/documentation

## 3. A screenshot of using a tool to try out the API:
![aasd](https://github.com/jlowb/CSC207REAL/assets/46061076/f1313616-8b97-47e2-b13b-6e8913838e1c)

## 4. Example output of running your Java code:
![Java MusixMatch thing](https://github.com/jlowb/CSC207REAL/assets/46061076/c65611db-ee8e-416e-a078-8eac2aa287b7)

## 6. A list of any technical problems blocking progress:

Technical issues: We initially encountered issues with a 401 error, indicating that the URL was invalid (in our case, the API key was incorrectly entered). I found it quite useful to examine other examples of API calls. One that particularly aided me was studying JokeAPI and how the URL was constructed, along with how the various parameters were combined. After reviewing these examples, our team successfully obtained an output from the Musixmatch API, as shown in the image.
