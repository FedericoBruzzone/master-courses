from Person import Person
from datetime import date

class Worker(Person):
 
 working_hours = 8

 def __init__(self, name: str, lastname: str, birthday: str, pay_per_hour: int):
  super().__init__(name, lastname, birthday)
  self.pay_per_hour = pay_per_hour

 def __update_pay_per_hour(self, amount):
  self.pay_per_hour = amount 

 def get_day_salary(self):
  return self.pay_per_hour * self.working_hours
 def set_day_salary(self, amount):
  self.__update_pay_per_hour(amount / self.working_hours)
 day_salary = property(get_day_salary, set_day_salary, None)

 
 
 def __repr__(self):
  return super().__repr__() + " " + str(self.working_hours)

if __name__ == "__main__":
 w: Worker = Worker("Federico", "Bruzzone", date(2000, 3, 7), 10)
 print(w)
 w.set_day_salary(160)
 print(w.day_salary)
