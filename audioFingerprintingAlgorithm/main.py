'''
our main driver
'''
import sys
import librosa
import audioPreprocess
import subprocess

def main():
    #get an example multi-media file
    audioPreprocess.audioPreprocess.extractMetadata()

    sys.exit(0)

if __name__ == "__main__":
    main()