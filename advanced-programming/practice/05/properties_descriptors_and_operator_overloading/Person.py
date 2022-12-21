'''
Let us consider a class Person with the following attributes: name, lastname, birthday with the obvious meaning and the corresponding setter and getters and the __repr__ to print it.

1. Extend the class Person in the class Student by adding a dictionary lectures with the lecture name as a key and the mark as a value, and the property grade_average to calculate the marks average
2. Extend the class Person in the class Worker by adding an attribute pay_per_hour and the properties day_salary, week_salary, month_salary, and year_salary considering 8 working hours a day, 5 working days a week, 4 weeks a month, 12 months a year; note that to set one of the properties implies to recalculate the pay_per_hour value
3. Extend the class Person in the class Wizard by adding a property age that when used as a getter calculates the correct age in term of passed days from the birthday to the current day and when used as a setter it will change the birthday accordingly rejuvenating or getting old magically.
Repeat the exercise by using the descriptors instead of the properties.
'''

class Person(object):
 name: str = ""
 lastname: str = ""
 birthday: str = ""
 
 def __init__(self): pass

 def get_name(self): return self.name
 def get_lastname(self): return self.lastname 
 def get_birthday(self): return self.birthday
 def set_name(self, name: str): self.name = name
 def set_lastname(self, lastname: str): self.lastname = lastname
 def set_birthday(self, birthday: str): self.birthday = birthday

