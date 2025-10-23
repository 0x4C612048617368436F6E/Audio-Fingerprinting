'''main purpose of this class is for development purpose. we will 
be using a library to ease things. But we will get the audio file, convert
that to a .wav file to ensure it is losless (having all the qualtieis we need). Initial goal
was to convert ourr mp3 files into wav files, but according to research this will have no improvement in quality
'''
import subprocess
import json

class audioPreprocess:
    audioPath = "../audio"
    def __init__(self):
        pass
    '''
    First thing we will do is some metadata exploration, that is getting metadata and all that sweet stuff and putting it inside of a JSON file. For this we can make use of the ffmpgee utitlity tool called ffprobe
    '''
    @classmethod
    def convertToWavIfNotWav(cls,file):
        pass