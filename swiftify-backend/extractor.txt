import os
from mutagen.mp3 import MP3
from mutagen.id3 import ID3, TIT2, TALB, TDRC
import csv

def extract_metadata(file_path):
    audio = MP3(file_path, ID3=ID3)
    try:
        title = audio['TIT2'].text[0]
    except KeyError:
        title = "Unknown"
    try:
        album = audio['TALB'].text[0]
    except KeyError:
        album = "Unknown"
    try:
        release_year = str(audio['TDRC'].text[0])[:4]  # Convert to string and take the first 4 characters
    except KeyError:
        release_year = "Unknown"
    duration = int(audio.info.length)
    return title, album, release_year, duration

album_order = [
    'Speak Now', '1989 (Deluxe)', 'Reputation', 'Lover',
    'Folklore', 'Evermore', 'Fearless', 'Red', 'Midnights (Deluxe)'
]

with open('MusicMetadata2.csv', mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["SongID", "title", "album", "duration", "release_year", "s3_key"])

    song_id = 1
    for album in album_order:
        album_path = os.path.join('discography', album)
        if os.path.exists(album_path):
            for song_file in sorted(os.listdir(album_path)):
                if song_file.endswith('.mp3'):
                    file_path = os.path.join(album_path, song_file)
                    title, album_name, release_year, duration = extract_metadata(file_path)
                    s3_key = f"{song_id}.mp3"  # s3_key is song_id + ".mp3"
                    writer.writerow([song_id, title, album_name, duration, release_year, s3_key])
                    song_id += 1
        else:
            print(f"Album folder not found: {album}")