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

# ...

# 3

# ...

# 4

# First implementation (It works in Python)
def fibonacci(n):
    memoization = {}

    def fibonacci_helper(n):
        if n in memoization:
            return memoization[n]
        if n == 1: return 1
        elif n == 2: return 1
        else:
            value = fibonacci_helper(n-1) + fibonacci_helper(n-2)
        memoization[n] = value
        return value

    return fibonacci_helper(n)

# Second implementation (It works in Python)
def memoize(f):
    cache = {}
    def inner(*args):
        key = tuple(args)
        if key in cache:
            return cache[key]
        value = f(*args)
        cache[key] = value
        return value
    return inner

@memoize
def fibonacci_m(n):
    if n == 1: return 1
    elif n == 2: return 1
    else: return fibonacci_m(n-1) + fibonacci_m(n-2)

# Third implementation (It works in Python)
from functools import *

@lru_cache(maxsize = 1000) # Least Recently Used Cache
def fibonacci_lru(n):
    if n == 1: return 1
    elif n == 2: return 1
    else: return fibonacci_lru(n-1) + fibonacci_lru(n-2)

print(fibonacci(500))
print(fibonacci_m(500))
print(fibonacci_lru(500))


