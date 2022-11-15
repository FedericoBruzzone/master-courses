print('''%%%%%%%%%%%%%%%%%%  1  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 1: A Few of Math with Lists.

# Write the solutions for the following quizzes by using functional programming:

# 1. Sum all the natural numbers below one thousand that are multiples of 3 or 5.
# 2. Calculate the smallest number divisible by each of the numbers 1 to 20.
# 3. Calculate the sum of the figures of 2^1000
# 4. Calculate the first term in the Fibonacci sequence to contain 1000 digits.

# 1
from functools import *

def sum_all_natural_number_divisile_by(f, t, *divisors):
    def divisible(number, *divisors):
        return 0 in (number % divisor for divisor in divisors)

    numbers = list(range(f, t))

    #list_numbers = [number for number in numbers if divisible(number, *divisors)]
    list_numbers = filter(lambda n: divisible(n, *divisors), numbers)

    return sum(list_numbers)

def sum_all(num):				
    def divisible(number, *divisors):
        return 0 in (number % divisor for divisor in divisors)

    numbers = list(range(num))
    f_sum = lambda x, y: x + y
    divisible_by_3_and_5 = lambda n: divisible(n, 3, 5)

    return reduce(f_sum, filter(divisible_by_3_and_5, numbers))

# 2
from functools import * 
import math

def smallest_divisible():
	l = list(range(2,21))
	p = l
	
	for i in range(len(l)):
			for j in range(i + 1, len(l)):
				if p[j]%l[i] == 0:
					p[j] = p[j] // l[i]
	return reduce(lambda x,y:x*y, l)

# 3
def sum_figures(num):
	return reduce(lambda x,y: x+y, [int(n) for n in str(num)])

# 

if __name__ == '__main__':
   print(sum_all_natural_number_divisile_by(0, 1000, 3, 5))
   print(sum_all(1000))
   print(sum([i for i in range(1000) if i%3 == 0 or i%5==0]))
   
   print(math.lcm(*range(2,21)))
   print(smallest_divisible())
   
   print(sum_figures(2**1000))
