from functools import reduce
from itertools import combinations

def get_divisors(n):
    return [i for i in range(1, n+1) if n%i==0]
#print(get_divisors(7))

def is_prime(n):
    return len(get_divisors(n)) == 2
#print(is_prime(1), is_prime(7), is_prime(10))

#def sum_divisors_of(n, divisors):
    #tmp_list = list(filter(lambda x: x <= n, divisors))
    #while n > 0 and len(tmp_list) > 0:
        #tmp_list = list(filter(lambda x: x <= n, divisors))
        #m = max(tmp_list)
        #divisors.remove(m)
        #n -= m
    #return True if n == 0 else False
#print(sum_divisors_of(4, [1,2,3]))

def get_all_sum_of_divisors(n, divisors):
    comb = [list(combinations(divisors, i)) for i in range(1, len(divisors)+1)]

    return list(filter(lambda x: x <= n, {sum(j) for i in comb for j in i}))
#print(get_all_sum_of_divisors(6, [1,2,3,6]))

def is_practical(n):
    if n%2 != 0: return False

    divisors = get_divisors(n)

    return len(get_all_sum_of_divisors(n, divisors)) == n
#print(is_practical(10))

def paradise_generator():
    n = 10
    while True:
        print(n)
        if all([is_prime(n+i) for i in range(-9, +10, 6)]) and all([is_practical(n+i) for i in range(-8, +9, 4)]):
            yield n
        n += 1

paradise_generator = paradise_generator()
print(next(paradise_generator))
print(next(paradise_generator))




