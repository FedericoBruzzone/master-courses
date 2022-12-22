import math
import sys

#def memoization(f):
# def wrapper(*args):
#  print("memoization::__call__") 
#  return f(*args)
# return wrapper

class memoization(object):
 def __init__(self, f):
  #self.cls = eval(str(*[f.__qualname__.split(".")[0]]) + "()")
  self.f = f
 def __call__(self=None, *args):
  return self.f(*args)

class MyMath(object):
 
 def __init__(self): pass
 
 @memoization
 def fib(self, n: int): 
  print(n)
  if n <= 1:
   return n
  return self.fib(n-1) + self.fib(n-2)

 def fact(self, n: int):
  if n == 1:
   return n
  return n*self.fact(n-1)

 def taylor(self, x: int, apx: int):
  if apx == 0:
    return 1
  return x**apx/math.factorial(apx) + self.taylor(x, apx-1)
  
if __name__ == "__main__":
 m: MyMath = MyMath()
 print(m.fib(10))      
 print(m.fact(10))      
 print(m.taylor(2, 3))     
     
