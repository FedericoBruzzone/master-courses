from datetime import date
from Worker import Worker

class Spell(type):
 def __new__(cls, classname, supers, classdict):
  print("Spell::__new__ ", cls)
  def init(self, name, lastname, birthday, pay_per_hour = 10):
   self.name = name
   self.lastname = lastname
   self.birthday = birthday
   self.pay_per_hour = pay_per_hour
 
  new_dict = Worker.__dict__.copy()
  new_dict["__init__"] = init

  return type.__new__(cls, Worker.__name__, Worker.__bases__, new_dict)
 
class Person(object, metaclass=Spell):
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
 print(p1.__class__.__bases__)
 print(p1)
 print(p1.get_month_salary())
 print(p1.get_day_salary())
 p1.set_day_salary(160)
 print(p1.get_day_salary())
 print(p1.pay_per_hour)
