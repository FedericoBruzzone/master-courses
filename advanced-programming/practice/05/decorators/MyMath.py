import math
import sys

def memoization(f):
 def wrapper(*args): 
  if args in wrapper.cache.keys():
   print("memoization::__call__ :- ", args[1:]) 
   return wrapper.cache[args]
  wrapper.cache[args] = f(*args)
  return wrapper.cache[args]
 wrapper.cache: dict = dict() 
 return wrapper

def logging(f): 
 def wrapper(*args):
  with open("logging.txt", mode="a", encoding="utf-8") as file:
   file.write(f"{f}::{args[1]}\n")
  return f(*args)
 return wrapper

def stacktrace(f):
 stacktrace.estack: list = list()
 def wrapper(*args):
  stacktrace.estack = [(f.__name__, args[1])]+ stacktrace.estack
  print([s for s in stacktrace.estack] , sep="\n")
  res = f(*args)
  stacktrace.estack = stacktrace.estack[1:]
  return res
 return wrapper

class MyMath(object):
 
 def __init__(self): pass
 
 @stacktrace
 @logging
 @memoization
 def fib(self, n: int): 
  print(n)
  if n <= 1:
   return n
  return self.fib(n-1) + self.fib(n-2)
 
 @memoization
 def fact(self, n: int):
  if n == 1:
   return n
  return n*self.fact(n-1)
 
 @stacktrace
 @memoization
 def taylor(self, x: int, apx: int):
  if apx == 0:
    return 1
  return x**apx/math.factorial(apx) + self.taylor(x, apx-1)
  
if __name__ == "__main__":
 m: MyMath = MyMath()
 print(m.fib(10))      
 print(m.fact(10))      
 print(m.taylor(2, 3))     
     
