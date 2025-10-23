'''
our main driver
'''
import sys
import librosa

def main():
    #get an example multi-media file
    exampleFile = librosa.ex("nutcracker")
    print(exampleFile)
    sys.exit(0)

if __name__ == "__main__":
    main()