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

toT = {'F': toF, 'K': toK, 'R': toR, 'De': toDe, 'N': toN, 'Re': toRe, 'Ro': toRo, 'C': toC}
fromT = {'F': fromF, 'K': fromK, 'R': fromR, 'De': fromDe, 'N': fromN, 'Re': fromRe, 'Ro': fromRo, 'C': fromC}
 
def fromTtoAll(n, t):
	celsius = fromT[t](n)
	return {scale: convert(celsius) for scale, convert in toT.items()}
	
# 1
def table(n):
	all_values = [[convert \
		       for scale, convert in sorted(fromTtoAll(n, t).items(), key=lambda x: x[0])] \
		       for t in sorted(toT.keys())]
	res = "    "+(8*"{: ^6} ")+"\n"+(8*("{:<3}"+(8*"{: 6.1f} ")+"\n"))
	output = temp = sorted(toT.keys())
	for i in range(8): output += [temp[i]] + all_values[i]
	
	return res.format(*output)	
	
print(table(25))
	
# 2
def toAll(n, t):
	all_values = fromTtoAll(n, t)
	del all_values[t]
	
	res=(7*"{{{}[1]:.1f}}◦{{{}[0]}} ").format(*[e for l in zip(range(7),range(7)) for e in l])
	output = sorted(all_values.items(),key=lambda x:x[1])
	return res.format(*output)

print(toAll(25, 'F'))





print('''%%%%%%%%%%%%%%%%%%  4  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 4: Matrix Calculi

# A matrix can be represented as a list of lists (rows and columns). 
# 1. Use the comprehensions to define a function (identity) that returns the identity matrix (the one with all 0s but the 1s on the diagonal) of given size.
# 2. Use the comprehensions to define a function (square) that returns a square matrix filled with the first n*n integers with n given as an argument. 
# 3. Write the function transpose to transpose a generic matrix independently of the size and content. 
# 4. Write a function to multiply two matrices non necessarily square matrices.

rows = lambda l, n, m: [[l[col + row * m] for col in range(0, m)] for row in range(0,n)]

# 1 
indentity = lambda n: rows([1 if row==col else 0 for col in range(n) for row in range(n)], n, n)
print(indentity(4))

# 2
square= lambda n: rows([row + col * n for row in range(1,n+1) for col in range(0,n)], n, n)
print(square(4))

# 3
transpose = lambda matrix: rows([matrix[row][col] for row in range(len(matrix)) for col in range(len(matrix[0]))], len(matrix[0]), len(matrix))
print(transpose(square(4)))

# 4 
element = lambda matrix1, matrix2, m1, m2: sum(matrix1[m1][i]+matrix2[i][m2] for i in range(len(matrix1[0])))
multiply = lambda matrix1, matrix2: rows([element(matrix1, matrix2, m1, m2) \
	for m1 in range(len(matrix1)) for m2 in range(len(matrix2[0]))], len(matrix1), len(matrix2[0]))

print(multiply(square(4), square(5)))





print('''%%%%%%%%%%%%%%%%%%  5  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 4: Shell Commends Simulation

# Similarly to the ls-l example please implement:
# 1. The cat command, i.e., a command that given a list of files prints their content on the terminal (man cat to get more info).
# 2. The chmod command, i.e., a command that permits to change the access mode of a given group of files (man chmod to get more info).
# 3. The more command, i.e., a command that given a file prints its content 30 rows at a time and wait a keystroke every 30 rows before printing the next 30.

import sys

# 1
def cat(fn):
	print(open(fn).read()[:-1])

cat(sys.argv[0])

# 2
#from stat import *
import stat
import os

def chmod(fn, mode):
	os.chmod(fn, mode) 

chmod(sys.argv[0], stat.S_IRWXU)

# 3
def do_print(fn, start, end):
	[print(i) for i in fn[start:end]]

def inc(n_lines, start):
	return 30 if n_lines - start > 30 else n_lines - start 

def naive_more(fn):
	content = open(fn).read().split('\n')[:-1]
	n_lines = len(content)
	start = 0
	
	while n_lines - start > 0:
		do_print(content, start, start + inc(n_lines, start))
		input('----------PRESS ENTER----------')
		start += inc(n_lines, start)

naive_more(sys.argv[0])
