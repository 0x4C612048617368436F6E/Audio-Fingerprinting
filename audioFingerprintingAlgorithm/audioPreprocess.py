'''main purpose of this class is for development purpose. we will 
be using a library to ease things. But we will get the audio file, convert
that to a .wav file to ensure it is losless (having all the qualtieis we need). Initial goal
was to convert ourr mp3 files into wav files, but according to research this will have no improvement in quality
'''
import subprocess
import json
import os
from pathlib import Path

#reason for using os and Path is os does not support creating parent directory

class audioPreprocess:
    audioMetadataPath = ["..","audio","metadata"]
    jsonPath = "audioMetadata.json"
    #don't know why ffprobe not being recognised, probably something to do with conda environment and windows
    cmd = [
        'C:\\Users\\benja\Downloads\\ffmpeg-N-121480-gcb5e201f5c-win64-gpl-shared\\ffmpeg-N-121480-gcb5e201f5c-win64-gpl-shared\\bin\\ffprobe.exe', '-v', 'error',
        '-print_format', 'json',
        '-show_format',
        '-show_streams'
    ]
    metaData = []
    def __init__(self):
        pass

    @staticmethod
    def extractNumberFromTrackName(trackName:str) -> int:
        num = trackName[5:6]
        if(not(trackName[6:7] == '.')):
            num = trackName[5:7]
        if(not isinstance(num,int)):
            num = int(num)
        return num
    '''
    First thing we will do is some metadata exploration, that is getting metadata and all that sweet stuff and putting it inside of a JSON file. For this we can make use of the ffmpgee utitlity tool called ffprobe
    '''
    @classmethod
    def extractMetadata(cls,*kwags):
        if not os.path.exists(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2])):
            #path does not exist
            Path(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2])).mkdir(parents=True)
            #let loop over the files within the audio path
            files = [file for file in os.listdir(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1])) if os.path.isfile(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],file)) and file[file.index('.')+1:] == "mp3"]
            
            files.sort(key=audioPreprocess.extractNumberFromTrackName)
            print(files)
            #loop through files and get information of the audio
            #create json file
            with open(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2],audioPreprocess.jsonPath),'w') as jsonFile:
                print("Created file {0}".format(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2],audioPreprocess.jsonPath)))
            with open(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2],audioPreprocess.jsonPath),'a') as jsonFile:
                for i in range(len(files)):
                    if(len(audioPreprocess.cmd) > 7):
                        #pop of the last part
                        audioPreprocess.cmd.pop()
                        audioPreprocess.cmd.append(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],files[i]))
                    else:
                        audioPreprocess.cmd.append(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],files[i]))
                    try:
                        global result
                        result = subprocess.run(audioPreprocess.cmd,capture_output=True)
                        jsonFromStdout = json.loads(result.stdout)
                        audioPreprocess.metaData.append(jsonFromStdout)
                    except Exception as e:
                        print("An error occured: {0}".format(e))
                    print("Metadata of file {0} added to json file {1}".format(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],files[i]),os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2],audioPreprocess.jsonPath)))
                jsonFile.write(json.dumps(audioPreprocess.metaData,indent=4))
                #some got no tags
            

