# Project Specification - Group 113

## Team Name:
Swiftify

## Domain:
Music Player web app.

## Software Specification:
The program is a social music player that allows users to listen to Taylor Swift's entire discography and interact with
other Swifties. Users will be able to make profiles, customize their profiles, make playlists of their favourite songs, 
share these playlists, and add comments to their favourite songs similar to the music websites SoundCLoud and Genius.

## User Stories:


- Bob wants to create an account on Swiftify, he enters his unique username (example: BobLovesSwift) and it’s checked for uniqueness. He proceeds to enter his personal name “Bob” and his optional last name in the respective text fields. He then adds his email and the program checks to be valid and a unique entry in the system. If all the previous entries are successful he can create an account on Swiftify.
- John (an already existing user) is on a current playlist, “Enchanted” and would like to switch to a song “Love story”, not in the “Enchanted” playlist. He decides to add this song to his playlist using the "add song to playlist option". He then listens to his song “Love story” within his “Enchanted” playlist sucessfully.
- Alice is a Taylor Swift superfan, and would like to share her knowledge with other Swifties. She uses the "annotate 
song" feature of Swiftify to add behind the scenes knowledge to every song.
- Larry is a depressed 19 year old male in college. But, last month he discovered an amazing new passion. Taylor Swift!
  Larry has always heard of Taylor Swift, but due to the patriarchy and societal pressures on masculinity, he never
  embraced Taylor's greatness. However, down on his luck, he finally decided to bite the bullet and listened to
  "Shake it Off." The lyrics spoke deeply to Larry, especially the part where she said "Baby, I'm just gonna shake, shake,
  shake, shake, shake," which is a commentary on late stage capitalism and its impact on the flexibility of modern man in
  life. Larry seeks a place where he can freely express his love for the goddess that is the Swift, without being shunned
  by society. Swiftify, as an anonymous music player and discussion board for her Swiftness, is just the place for Larry,
  as not only can he listen to the Swifter, but he can also discuss her with fellow Swifties. Larry's main use case would be
  the discussions on each song.
- Taylor wants to add her bestie Swiftie Swift on Swiftify, she searches up Swift’s alleged username “DieHardSwiftie123”
  on the Swiftify friend system, but to no avail. Turns out Swift doesn’t even have an account on the Swiftify app yet 
  (fake swiftie hint hint), so she has to go create an account through the registration page.

## Proposed Entities 
Songs:
- Id int
- Album_id int
- Artist_id int
- Title String
- Length float
- Track int?
- Disc int
- Lyrics String
- Path String ???
- Mtime int ??? huh?
- Created_at datetime
- Updated_at datetime

Albums:
- Id int
- Artist_id int
- Name String
- Cover String
- Songs_ids list(Song)
- Create_at datetime
- Updated_at datetime

Playlists:
- Id int
- User_id int
- Name String
- Rules text???
- Create_at datetime
- Updated_at datetime

Artists:
- Id int 
- Name String
- Image String
- Create_date datetime
- Update_date datetime

Users:
- Id int
- Name String 
- Email String 
- Password String 
- Is_admin boolean ?? (is_taylor)
- Preference text??? 
- Remember_token ??? 
- Created_at datetime 
- Updated_at datetime


