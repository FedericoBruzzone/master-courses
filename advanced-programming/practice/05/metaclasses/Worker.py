from Person import Person
from datetime import date

class Worker(Person):
 working_hours_a_day: int = 8
 working_day_a_week: int = 5
 weeks_a_month: int = 4
 months_a_year: int = 12

 def __init__(self, name: str, lastname: str, birthday: str, pay_per_hour: int):
  super().__init__(name, lastname, birthday)
  self.pay_per_hour = pay_per_hour

 def __update_pay_per_hour(self, amount):
  self.pay_per_hour = amount 

 def get_day_salary(self):
  return self.pay_per_hour * self.working_hours_a_day
 def set_day_salary(self, amount: int):
  self.__update_pay_per_hour(amount / self.working_hours_a_day)
 day_salary = property(get_day_salary, set_day_salary, None)
 
 def get_week_salary(self):
  return self.get_day_salary() * self.working_day_a_week
 def set_week_salary(self, amount: int):
  self.__update_pay_per_hour(amount / self.working_day_a_week / self.working_hours_a_day)
 week_salary = property(get_week_salary, set_week_salary, None)

 def get_month_salary(self):
  return self.get_week_salary() * self.weeks_a_month
 def set_month_salary(self, amount):
  return self.__update_pay_per_hour(amount / self.weeks_a_month / self.working_day_a_week / self.working_hours_a_day)
 month_salary = property(get_month_salary, set_month_salary, None)

 def get_year_salary(self):
  return self.get_month_salary * self.months_a_year
 def set_year_salary(self, amount):
  return self.__update_pay_per_hour(amount / self.months_a_year /self.weeks_a_month / self.working_day_a_week / self.working_hours_a_day)
 year_salary = property(get_year_salary, set_year_salary, None)

 def __repr__(self):
  return self.__class__.__name__ + ": " + self.name + " " + self.lastname + " " + self.birthday.isoformat() + " " + str(self.working_hours_a_day)

if __name__ == "__main__":
 w: Worker = Worker("Federico", "Bruzzone", date(2000, 3, 7), 10)
 print(Worker.__bases__)
 w.set_day_salary(160)
 print(w.pay_per_hour)
 w.set_week_salary(400)
 print(w.pay_per_hour)
