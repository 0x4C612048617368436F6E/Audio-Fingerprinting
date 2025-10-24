'''main purpose of this class is for development purpose. we will 
be using a library to ease things. But we will get the audio file, convert
that to a .wav file to ensure it is losless (having all the qualtieis we need). Initial goal
was to convert ourr mp3 files into wav files, but according to research this will have no improvement in quality
'''
import subprocess
import json
import os
from pathlib import Path

class audioPreprocess:
    audioMetadataPath = ["..","audio","metadata"]
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
    def extractMetadata(cls):
        '''if os.path.exists(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2])):
            #path does not exist
            Path(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],audioPreprocess.audioMetadataPath[2])).mkdir(parents=True)'''
        #let loop over the files within the audio path
        files = [file for file in os.listdir(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1])) if os.path.isfile(os.path.join(audioPreprocess.audioMetadataPath[0],audioPreprocess.audioMetadataPath[1],file)) and file[file.index('.')+1:] == "mp3"]
        files.sort(key=audioPreprocess.extractNumberFromTrackName)

        print(files)