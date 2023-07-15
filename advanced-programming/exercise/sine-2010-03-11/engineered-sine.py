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

def mysin2(x, n):
    g = myfact(n)
    tmp = [(x ** i) / next(g) for i in range(1, n // 2 + 1)]
    tmp_x = [-tmp[i] if i % 2 == 0 else tmp[i] for i in range(len(tmp))] 
    return sum(tmp_x)

if __name__ == "__main__":
    for n in range(10):
        print((sin(5) - mysin(5, n)))
        print((sin(5) - mysin2(5, n)))