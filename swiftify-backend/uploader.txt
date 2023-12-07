import boto3
import csv

def upload_csv_to_dynamodb(csv_file_path, table_name):
    """
    Uploads data from a CSV file to a DynamoDB table.

    Parameters:
    csv_file_path (str): The file path of the CSV file.
    table_name (str): The name of the DynamoDB table.

    Returns:
    None
    """

    # Create a DynamoDB client using Boto3
    dynamodb = boto3.resource('dynamodb')
    
    # Access the DynamoDB table
    table = dynamodb.Table(table_name)

    # Open the CSV file and read data
    with open(csv_file_path, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            # Convert numeric fields to integers
            row['SongID'] = int(row['SongID'])
            row['duration'] = int(row['duration'])
            row['release_year'] = int(row['release_year'])

            # Put item in DynamoDB table
            table.put_item(Item=row)

    print("Data upload complete.")

upload_csv_to_dynamodb('MusicMetadata2.csv', 'MusicMetadata')
