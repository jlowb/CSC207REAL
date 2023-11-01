import os
import boto3
from mutagen.mp3 import MP3
from mutagen.id3 import ID3, TIT2, TALB, TDRC

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

def insert_into_dynamodb(id, title, album, duration, release_year, s3_key):
    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('MusicMetadata')
    table.put_item(
        Item={
            'SongID': id,
            'title': title,
            'album': album,
            'duration': duration,
            'release_year': release_year,
            's3_key': s3_key
        }
    )

# Initialize a unique identifier for each song
song_id = 1

# List of albums in the order you want to process them
album_order = [
    'Speak Now', '1989 (Deluxe)', 'Reputation', 'Lover',
    'Folklore', 'Evermore', 'Fearless', 'Red', 'Midnights (Deluxe)'
]

# Loop through each album folder in the specific order
for album_folder in album_order:
    if os.path.isdir(album_folder):
        # Loop through each song in the album folder
        for song_file in os.listdir(album_folder):
            if song_file.endswith('.mp3'):
                # Generate the file path for metadata extraction
                file_path = os.path.join(album_folder, song_file)
                
                # Extract metadata
                title, album, release_year, duration = extract_metadata(file_path)
                
                # Generate the S3 key
                s3_key = f"s3://csc207swiftify/discography/{album_folder}/{song_file}"
                
                # Insert metadata into DynamoDB
                insert_into_dynamodb(song_id, title, album, duration, release_year, s3_key)
                
                # Increment the song_id for the next song
                song_id += 1

# Print completion message
print("Metadata extraction and DynamoDB population completed.")