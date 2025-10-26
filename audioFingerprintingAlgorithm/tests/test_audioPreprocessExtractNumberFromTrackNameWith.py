import unittest
from audioPreprocess import audioPreprocess
class test_audioPreprocessExtractNumberFromTrackName(unittest.TestCase):
    #function should return the integer part of the trackFileName
        #If there is no number, should fail
    def test_audioPreprocessExtractNumberFromTrackNameWithNumber(self)->int:
        self.assertEqual(audioPreprocess.extractNumberFromTrackName("track0"),0)
        self.assertEqual(audioPreprocess.extractNumberFromTrackName("track50"),50)
        self.assertEqual(audioPreprocess.extractNumberFromTrackName("track100"),100)

    def test_audioPreprocessExtractNumberFromTrackNameWithNoNumber(self,trackFileName)->int:
        pass