print('''%%%%%%%%%%%%%%%%%%  1  %%%%%%%%%%%%%%%%%%%%''')

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




