from Person import Person
from functools import reduce

class Student(Person):
 lectures: dict = dict()
 
 def __init__(self, lectures):
  self.lectures = lectures
  
 def set_lectures(self, lectures):
  self.lectures = lectures
  
 def grade_average(self):
  return reduce(lambda x,y: x + y, self.lectures.values()) / len(self.lectures)

 lectures = property(grade_average, set_lectures, None)

if __name__ == "__main__":
 s = Student({"math": 30, "cs": 25})
