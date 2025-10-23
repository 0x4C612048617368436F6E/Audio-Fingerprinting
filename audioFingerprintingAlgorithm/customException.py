'''
custom exception class
'''
class customException(Exception):
    def __init__(self,message="An Error occured"):
        super().__init__(message)