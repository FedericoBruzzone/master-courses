from editor import *
import types

def u(self):
    self.undoredo = self.check 

def ctrlr(self):
    self.undoredo = self.check

def SaveStatus(F):
    def wrapper(*args):
        res = F(*args)
        args[0].undoredo += 1
        args[0].status.insert(args[0].undoredo, (args[0].c, args[0].text))
        return res
    return wrapper

def __str__(self):
    res = "".join(self.status[self.undoredo][1]) 
    self.check = self.undoredo - 1
    return res

class UndoRedo(type):
    def __new__(cls, classname, supers, classdict):
        classdict["undoredo"] = 0 
        classdict["status"] = [(-1, "")]

        for k, v in classdict.items():
            if k in ["i", "x", "dw", "iw", "h", "l"]:
                classdict[k] = SaveStatus(v)

        classdict["u"] = u
        classdict["ctrlr"] = ctrlr
        classdict["__str__"] = __str__
        return type.__new__(cls, classname, supers, classdict)

editor = UndoRedo("editor", tuple(), dict(editor.__dict__))


