# CSC207 Group 113

## 0. Problem Domain

After the brainstorming session in our tutorial, our team has tentatively decided to create a **music player**. 
This decision was motivated by the abundance of features that could be implemented,
ensuring that the project would be interesting for every single group member.

The program is a music player that allows users to listen to Taylor Swift's entire discography. Users will be able to enjoy a seamless interface with features such as playback, shuffle, add to queue, and effortless navigation through songs, similar to the music websites SoundCloud and Genius. Crucially, our code is optimized for an internet-based interface. This means users can always enjoy the most up-to-date Taylor Swift songs and albums directly from the web. 

## 1. Project Description

We hypothesize that the key features of a music player (that would need to be implemented) are:
1. Artist Selection (Open for extension, we will just have Taylor Swift for now) 
   1. Choice of artist list (needs a frontend)
   2. Once artist is selected, then user will be met with page of their albums, with the respective songs in each album
2. Music Database
3. Music Player
   1. Music Player Component (essential)
   2. Music Lyrics (optional) 
   3. Music Queue/Playback/Shuffle/History (essential)
4. Front End
   1. GUI of the Artist Selection Page, Load Albums Page, Load Songs Page (all essential) 

As this is quite ambitious, we may remove features depending on the rate of progress, but this is more or less the big picture.

## 2. Link to API documentation

The following API can be used to implement feature 4.ii above: 
https://developer.musixmatch.com/documentation

AWS (API Gateway + Lambda + DynamoDB) - Music Metadata

AWS (API Gateway + Lambda + S3) - Music Static Files

JLayer - Audio Player

## 3. A screenshot of using a tool to try out the API

![aasd](https://github.com/jlowb/CSC207REAL/assets/46061076/f1313616-8b97-47e2-b13b-6e8913838e1c)

## 4. Example output of running your Java code

![Java MusixMatch thing](https://github.com/jlowb/CSC207REAL/assets/46061076/c65611db-ee8e-416e-a078-8eac2aa287b7)

## 6. A list of any technical problems blocking progress

1. Since our project is a complex system that depends on many features working cohesively, we will need to use a myriad of APIs, which will be difficult to implement (Oauth in particular). In addition, we will almost certainly run into incompatibility issues or other esoteric bugs that will test our web development abilities.
2. For example, in trying to implement the above API, we initially encountered issues with a 401 error, indicating that the URL was invalid (in our case, the API key was incorrectly entered). I found it quite useful to examine other examples of API calls. One that particularly aided me was studying JokeAPI and how the URL was constructed, along with how the various parameters were combined. After reviewing these examples, our team successfully obtained an output from the Musixmatch API, as shown in the image.
