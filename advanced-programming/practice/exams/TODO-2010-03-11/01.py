from functools import *
from math import *
from operator import mul

def myfact(n):
	fact = 1
	for i in range(1, n + 1):
		fact *= i
		if i % 2 != 0:
			yield fact

def mysin(x, n):
	g = myfact(n)
	return sum([(x ** i) / next(g) for i in range(1, n // 2 + 1)])

def mysin_correct(x, n):
	g = myfact(n)
	s = [(x ** i) / next(g) for i in range(1, n, 2)]
	s = [v if j % 2 == 0 else -1 * v for j, v in enumerate(s)]
	return sum(s)

if __name__ == "__main__":
	for n in range(1000):
		print((sin(5) - mysin(5, n)))
		print((sin(5) - mysin_correct(5, n)))
