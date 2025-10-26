'''
our main driver
'''
import sys
import librosa
import audioPreprocess
import subprocess
import os

def main():
    #get an example multi-media file
    #audioPreprocess.audioPreprocess.extractMetadata()
    filePath = [file for file in os.listdir(os.path.join(audioPreprocess.audioPreprocess.audioMetadataPath[0],audioPreprocess.audioPreprocess.audioMetadataPath[1]))]

    filePath = list(filter(lambda a: a != "metadata",filePath))
    filePath = list(map(lambda a: os.path.join(audioPreprocess.audioPreprocess.audioMetadataPath[0],audioPreprocess.audioPreprocess.audioMetadataPath[1])+"\\"+a,filePath))
    #append the full path
    audioPreprocess.audioPreprocess.audioAnalysis(file=filePath[0])

    sys.exit(0)

if __name__ == "__main__":
    main()