from editor import *

def debug(C):
    class wrapper:
        def __init__(self, *args):
            self.wrapped = C(*args)
            self.last_op = "F"
        def __getattr__(self, name):             
            self.last_op = name
            return getattr(self.wrapped, name)
        def __setattr__(self, attribute, value): 
            if attribute == "wrapped" or attribute == "last_op":
                self.__dict__[attribute] = value
            else:
                setattr(self.wrapped, attribute, value)
        def __str__(self):
            return self.last_op + " " + self.wrapped.c.__str__() + " :- " + self.wrapped.__str__()

    return wrapper 

editor = debug(editor)
