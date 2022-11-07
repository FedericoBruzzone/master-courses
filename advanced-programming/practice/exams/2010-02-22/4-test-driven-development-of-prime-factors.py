'''
Exercise 4: Test Driven Development of Prime Factors.
Prime Factors. There are two kinds of positive numbers: prime numbers and composite numbers. 
A composite number is the product of a sequence of prime numbers. 
You can write a simple generator, named prime_factors to factor numbers and yield each prime factor of the number; 
the generators take as an input the number to factor and returns the prime factors from the smallest to the largest. 
Note that, a simple-looking for -loop shall not work; the prime factor of 128 is 2, repeated 7 times. 
Note also that 1 is not a factor or better is always a factor so can be excluded by the list.

The generator have to be developed by using the test-driven techniques; 
a commitment without test cases will be considered completely wrong.
'''

is_prime = lambda x: len([i for i in range(1, x+1) if x%i == 0]) == 2

def generator_prime():
    yield 2
    i = 1
    while True:
        i += 2
        (is_prime(i) and (yield i))


def prime_factors(number_to_factor):
    G = generator_prime()
    current = next(G)
    while True:
        if number_to_factor <= 1: 
            raise StopIteration
        (((number_to_factor % current == 0 and (number_to_factor:=number_to_factor // current) and (yield current))) or \
        ((current <= number_to_factor and number_to_factor % current != 0) and (current:=next(G))))

G = prime_factors(7) 
print(f'7  : {[next(G) for i in range(1)]}')
G = prime_factors(55) 
print(f'55 : {[next(G) for i in range(2)]}')
G = prime_factors(128) 
print(f'128: {[next(G) for i in range(7)]}')

# prime_numbers = lambda x: [i for i in range(x+1) if len([j for j in range(1, i+1) if i%j == 0]) == 2]

# def prime_factors(number_to_factor):
#     # I should divide the number for the greater prime number until if %2 == 0 
#     # end if the find numbers isprime or there is not a prime number divisor
#     prime_divisors = prime_numbers(number_to_factor)
#     print(prime_divisors)

#     current_divisor = 0

#     while number_to_factor > 1:
#         #print(number_to_factor % prime_divisors[current_divisor] == 0, number_to_factor, prime_divisors[current_divisor], current_divisor < len(prime_divisors))
#         (((number_to_factor % prime_divisors[current_divisor] == 0 and (number_to_factor:=number_to_factor // prime_divisors[current_divisor]) and (yield prime_divisors[current_divisor]))) or \
#          ((prime_divisors[current_divisor] <= number_to_factor and number_to_factor % prime_divisors[current_divisor] != 0) and (current_divisor:=current_divisor+1)))


# G = prime_factors(55) 
# [print(next(G)) for i in range(7)]

