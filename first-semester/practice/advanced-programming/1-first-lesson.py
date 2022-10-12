# Exercise 1: The calendar module

# In the following exercises, you will work with Python's calendar module:
# 1. Visit the Python documentation website at http://docs.python.org/3.1/modindex.html, and look at the documentation on the calendar module.
# 2. Import the calendar module.
# 3. Read the description of the function isleap(). Use isleap() to determine the next leap year.
# 4. Find and use a function in the module calendar to determine how many leap years there will be between years 2000 and 2050, inclusive.
# 5.Find and use a function in module calendar to determine which day of the week July 29, 2016 will be.

# 2
import calendar

# 3
# help(calendar.isleap)

import datetime
now = datetime.datetime.now()

def next_year(x):
	return x if calendar.isleap(x) else next_year(x+1)

print('Next year: {0}'.format(next_year(now.year)))

# 4
print('How many leap years between 2000 and 2050: {0} '.format(calendar.leapdays(2000, 2051)))

# 5
print('Day name of July 29, 2016: {0}'.format(calendar.day_name[calendar.weekday(2016, 7, 29)]))

# Exercise 2: Alkaline Earth Metals.

# Assign a list that contains the atomic numbers and the names of the six alkaline
earth metals—barium (56), beryllium (4), calcium (20), magnesium (12), radium
(88), and strontium (38)—to a variable called alkaline_earth_metals.
1. Write a one-liner to return the highest atomic number in alkaline_earth_metals.
2. Using one of the list methods, sort alkaline_earth_metals in ascending
order (from the lightest to the heaviest).
3. Transform the alkaline_earth_metals into a dictionary using the name of
the metals as the dictionary’s key.
4. Create a second dictionary containing the noble gases—helium (2), neon
(10), argon (18), krypton (36), xenon (54), and radon (86)—and store it in
the variable noble_gases.
5. Merge the two dictionaries and print the result as couples (name, atomic
number) sorted in ascending order on the element names.
Note that Python’s dictionaries do not preserve the insertion order neither it is
sorted in some way.
