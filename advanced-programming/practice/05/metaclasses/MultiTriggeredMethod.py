from datetime import date
from types import FunctionType
import traceback

def MultiTriggeredMethod_dec(f):
 def wrapper(*args):
  if f in wrapper.cache.keys() and wrapper.cache[f] == 1:
   print("It's the second time")
   wrapper.cache[f] = 0    
   return f(*args)
  else:
   wrapper.cache[f] = 1   
   #return f(*args)
   print("[ERROR] It's the first time")
   pass
 wrapper.cache: dict = dict()
 return wrapper

class MultiTriggeredMethod(type):
 def __new__(cls, classname, supers, classdict):
  print("MultiTriggeredMethod::__new__", cls)
  for attr, attrval in classdict.items():
   if type(attrval) is FunctionType and attr != "__init__":
    classdict[attr] = MultiTriggeredMethod_dec(attrval)
  return type.__new__(cls, classname, supers, classdict)

class Person(object, metaclass=MultiTriggeredMethod):
 name: str = ""
 lastname: str = ""
 birthday: date = ""
 
 def __init__(self, name: str, lastname: str, birthday: date):
  self.name = name
  self.lastname = lastname
  self.birthday = birthday

 def get_name(self): return self.name
 def get_lastname(self): return self.lastname 
 def get_birthday(self): return self.birthday
 def set_name(self, name: str): self.name = name
 def set_lastname(self, lastname: str): self.lastname = lastname
 def set_birthday(self, birthday: date): self.birthday = birthday
 
 def __repr__(self):
  return self.__class__.__name__ + ": " + self.name + " " + self.lastname + " " + self.birthday.isoformat() 

if __name__ == "__main__":
 print("__main__")
 p1: Person = Person("Federico", "Bruzzone", date(2000, 3, 7))
 print("### p1.get_name()", p1.get_name())
 print("### p1.get_name()", p1.get_name())
