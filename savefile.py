#this file will be used to save game record.
from datetime import datetime 
import sys
import re
#to save files with changing names, using time is better.
#Get local time using datetime.now
current_time = datetime.now()

#Convert current_time to string
file_name = str(current_time)

#To use string as name for txt file, we fix by getting 11th to n-1th chars

file_name = file_name[11:]

#Replace . and : to _ to prevent errors
file_name = file_name.replace('.','_')
file_name = file_name.replace(':','_')
file_name = file_name.replace(':','_')

#Fetch parameter from java using sys.argv[1] because argv[0] is file path.
record_received = str(sys.argv[1])

#Break string
GameStatusIndex = record_received.find("Status: ")
GameTimeIndex = (record_received.find("Time") -1)

#Find parts
Gameboard = record_received[0:11]
GameWinStatement = record_received[GameStatusIndex : GameTimeIndex]
GameTime = record_received[GameTimeIndex+1 :]

#Break gameboard
UpdatedGameBoard = Gameboard.replace(" ","\n")
UpdatedGameBoard = Gameboard.replace(" ","\n")
UpdatedGameBoard = Gameboard.replace(" ","\n")

print(UpdatedGameBoard)
print(GameWinStatement)
print(GameTime)

with open(f"{file_name}.txt","w") as file:
    file.write(UpdatedGameBoard)
    file.write("\n"+GameWinStatement)
    file.write("\n"+GameTime)

