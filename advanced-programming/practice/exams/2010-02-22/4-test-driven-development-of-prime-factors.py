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

prime_numbers = lambda x: [i for i in range(x) if len([j for j in range(1, i+1) if i%j == 0]) == 2]

def prime_factors(number_to_factor):
    # I should divide the number for the greater prime number until if %2 == 0 
    # end if the find numbers isprime or there is not a prime number divisor
    prime_divisors = prime_numbers(number_to_factor)
    print(prime_divisors)

prime_factors(128)
    
