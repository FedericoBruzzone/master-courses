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





print('''%%%%%%%%%%%%%%%%%%  2  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 2: Alkaline Earth Metals.

# Assign a list that contains the atomic numbers and the names of the six alkaline earth metals—barium (56), beryllium (4), calcium (20), magnesium (12), radium (88), and strontium (38)—to a variable called alkaline_earth_metals.
# 1. Write a one-liner to return the highest atomic number in alkaline_earth_metals.
# 2. Using one of the list methods, sort alkaline_earth_metals in ascending order (from the lightest to the heaviest).
# 3. Transform the alkaline_earth_metals into a dictionary using the name of the metals as the dictionary’s key.
# 4. Create a second dictionary containing the noble gases—helium (2), neon (10), argon (18), krypton (36), xenon (54), and radon (86)—and store it in the variable noble_gases.
# 5. Merge the two dictionaries and print the result as couples (name, atomic number) sorted in ascending order on the element names. Note that Python’s dictionaries do not preserve the insertion order neither it is sorted in some way.

# 1
alkaline_earth_metals = [('barium',56), ('beryllium', 4), ('calcium', 20), ('magnesium', 12), ('radium', 88), ('strontium', 38)]

print(sorted(alkaline_earth_metals, key = lambda x:x[1])[-1][1])

# 2
print(sorted(alkaline_earth_metals, key = lambda x:x[1]))


#3
alkaline_earth_metals = dict(alkaline_earth_metals)
print(alkaline_earth_metals)
type(alkaline_earth_metals)

# 4
noble_gases = {'helium': 2, 'neon': 10, 'argon': 18, 'krypton': 36, 'xenon': 54, 'radon': 86}  

# 5
#merge_dict = alkaline_earth_metals | noble_gases
merge_dict = {**alkaline_earth_metals, **noble_gases}
print(merge_dict)






print('''%%%%%%%%%%%%%%%%%%  3  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 3: Temperature Conversion System

# Beyond the well-known Celsius and Fahrenheit, there are other six temperature scales: Kelvin, Rankine, Delisle, Newton, Réaumur, and Rømer (Look at http://en.wikipedia.org/wiki/Comparison_of_temperature_scales to read about them).
# 1. Write a function (table) that given a pure number returns a conversion table for it (as a string) among any of the 8 temperature scales (remember that functions are objects as well).
# 2. Write a function (toAll) that given a temperature in a specified scale returns a string for all the corresponding temperatures in the other scales, the result must be sorted on the temperatures and the scale must be specified.

def fromC(x): return x
def toC(x): return x
def fromDe(x): return 100-x*2/3
def toDe(x): return (100-x)*3/2
def fromF(x): return (x-32)*5/9
def toF(x): return x*9/5+32
def fromK(x): return x-273.15
def toK(x): return x+273.15
def fromN(x): return x*100/33
def toN(x): return x*33/100
def fromR(x): return (x-491.67)*5/9
def toR(x): return x*9/5+491.67
def fromRe(x): return x*5/4
def toRe(x): return x*4/5
def fromRo(x): return (x-7.5)*40/21
def toRo(x): return x*21/40+7.5

toT = {’F’: toF, ’K’: toK, ’R’: toR, ’De’: toDe, ’N’: toN, ’Ré’: toRé, ’Rø’: toRø, ’C’: toC}
fromT = {’F’: fromF, ’K’: fromK, ’R’: fromR, ’De’: fromDe, ’N’: fromN, ’Re’: fromRe, ’Ro’: fromRo, ’C’: fromC}
 
def fromTtoAll(n, T):
	celsius = fromT[T](n)
	return {scale: convert(celsius) for scale, convert in toT.item()}
