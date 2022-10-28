print('''%%%%%%%%%%%%%%%%%%  3  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 3: Approximated Trigonometric Functions.
# Let's write a library to implement sin(x, n) by using the Taylor's series 
# (where n is the level of approximation, i.e., 1 only one item, 2 two items, 3 three items and so on).

# Let's compare your function with the one implemented in the math module at the growing of the approximation level.

# Hint. Use a generator for the factorial and a comprehension for the series.

from math import sin
from functools import reduce

def generator_factorial():
    i = 1
    current = 1

    while True:
        yield current
        current *= i
        i += 1

def taylor_series(x, apx):
    gen = generator_factorial()
    gen_plus_2 = lambda gen: (next(gen) and next(gen))

    return reduce(lambda a,b: a+b, \
        list(map(lambda x: (x[0]%2==0 and x[1]*-1) or x[1], \
        list(zip(range(1, apx), [((x**i) / gen_plus_2(gen)) \
        for i in range(1, apx*2-1, 2)])))))

print(f'math.sin(3): {sin(3)}')
print(f'My taylor_series(3, 10): {taylor_series(3, 10)}')