from datetime import date

class Person(object):
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
 p: Person = Person("Federico", "Bruzzone", date(2000, 3, 7))
 print(p)
