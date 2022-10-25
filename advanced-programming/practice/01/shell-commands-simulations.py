 
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
