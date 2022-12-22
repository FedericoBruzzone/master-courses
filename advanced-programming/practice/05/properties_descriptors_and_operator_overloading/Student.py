from Person import Person
from datetime import date

class Student(Person):
 lectures: dict = dict()
 
 def __init__(self, name: str, lastname: str, birthday: str, lectures: dict):
  super().__init__(name, lastname, birthday)
  self.lectures = lectures
  
 def add_lecture(self, key: str, value: int):
  self.lectures.update({key, value})
     
 def __grade_average(self):
  return sum(self.lectures.values()) / len(self.lectures)

 grade_average = property(__grade_average, None, None, "There is no lecture")

 def __repr__(self):
  return super().__repr__() + " " + str(self.lectures)
 
if __name__ == "__main__":
 s = Student("Federico", "Bruzzone", date(2000, 3, 7), {"math": 30, "cs": 25})
 print(s)
 print(s.grade_average)
