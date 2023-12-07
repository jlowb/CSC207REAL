Project Specification  - Group 113

Team Name:
Swiftify

Domain:
Music Player web app.

Software Specification:
The program is a social music player that allows users to listen to Taylor Swift's entire discography and interact with other Swifties. Users will be able to enjoy a seamless interface with features such as playback, shuffle, add to queue, and effortless navigation through songs, similar to the music websites SoundCloud and Genius.

User Stories:
- Janice is a new Swiftify user and has no playlists. She should be able to: (i) listen to Taylor's entire discography as one playlist, (ii) listen to each album as one playlist and (iii) listen to other people's playlists.
- Bob wants to create an account on Swiftify. He enters a username (example: BobLovesSwift), which is checked for uniqueness. He proceeds to enter his personal name ÒBobÓ and his optional last name in the respective text fields. He then adds his email, which is checked to be valid and not already in use. If all the previous entries are successful, Swiftify creates an account for Bob.
- John (an already existing user) is on a current playlist, ÒEnchantedÓ and would like to switch to the song ÒLove storyÓ, not in the ÒEnchantedÓ playlist. He decides to add this song to his playlist using the "add song to playlistÓ option. He then listens to his song ÒLove storyÓ within his ÒEnchantedÓ playlist successfully.
- Alice is a Taylor Swift superfan, and would like to share her knowledge with other Swifties. She uses the "annotate song" feature of Swiftify to add behind the scenes knowledge to every song page.
- Taylor wants to add her bestie on Swiftify, she searches up her alleged username, ÒDieHardSwiftie123Ó on the Swiftify friend system, but to no avail. Turns out her friend doesnÕt even have an account on the Swiftify app yet (fake Swiftie), so she has to go create an account through the registration page.
- Larry is a depressed 19 year old male in college. But, last month he discovered an amazing new passion. Taylor Swift! Larry has always known of Taylor Swift, but was never able to embrace TaylorÕs greatness due to patriarchal and societal pressures on masculinity. However, down on his luck, he finally decided to bite the bullet and listened to "Shake it Off." The lyrics spoke deeply to Larry, especially the part where she said "Baby, I'm just gonna shake, shake, shake, shake, shake," which is a commentary on late stage capitalism and its impact on the flexibility of modern man in life. Larry seeks a place where he can freely express his love for the goddess that is the Swift, without being shunned by society. Swiftify, as an anonymous music player and discussion board for her Swiftness, is just the place for Larry, as not only can he listen to the Swifter, but he can also discuss her with fellow Swifties. Larry's main use case would be the discussions on each song.

Proposed Entities 
Songs:
- Id int
- Album_id int
- Artist_id int
- Title String
- Length float
- Track int
- Disc int
- Lyrics String
- Path String ???
- Mtime int 
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

Scheduled Meeting Times
Wednesdays 8.00-8.30pm, Zoom

