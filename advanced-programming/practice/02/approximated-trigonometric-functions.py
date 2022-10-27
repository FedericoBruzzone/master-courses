print('''%%%%%%%%%%%%%%%%%%  3  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 3: Approximated Trigonometric Functions.
# Let's write a library to implement sin(x, n) by using the Taylor's series 
# (where n is the level of approximation, i.e., 1 only one item, 2 two items, 3 three items and so on).

# Let's compare your function with the one implemented in the math module at the growing of the approximation level.

# Hint. Use a generator for the factorial and a comprehension for the series.

from functools import reduce

def generator_factorial():
    i = 1
    current = 1

    while True:
        yield current
        current *= i
        i += 1
        
# for f in generator_factorial(10):
#     print(f)

def taylor_series(n, apx):
    gen = generator_factorial()
    return reduce(lambda a,b: a+b, [(n**i / next(gen)) for i in range(apx)])

print(taylor_series(2,5))