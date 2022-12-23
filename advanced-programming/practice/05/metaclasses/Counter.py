from datetime import date

class Counter(type):
 counter: int = 0

 def __new__(cls, classname, supers, classdict):
  print("Counter::__new__")
  return type.__new__(cls, classname, supers, classdict)
 
 def __init__(cls, classname, supers, classdict):
  print("Counter::__init__")
  type.__init__(cls, classname, supers, classdict)
 
 def __call__(cls, *args):
  print("Counter::__call__")
  Counter.counter += 1 
  return type.__call__(cls, *args)

class Person(object, metaclass=Counter):
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
 print(p1)
 print("Counter::counter :- ", Counter.counter)
 p2: Person = Person("A", "B", date(10, 10, 10))
 print(p2)
 print("Counter::counter :- ", Counter.counter)
