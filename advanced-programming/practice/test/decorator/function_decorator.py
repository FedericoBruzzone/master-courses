print("....................")

def decorator(F):
  def wrapper(*args):
    print(f"I'm executing the call {F.__name__}{args}")
    return F(*args)
  return wrapper

@decorator
def f(x,y):
  print(f"{x} {y}")

def f_t(x,y):
  print(f"{x} {y}")

decorator(f_t)(42,7)
f(42, 7)

print("....................")

class wrapper(object):
  def __init__(self, func):
    self.func = func
  def __call__(self, *args):
    print(f"I'm executing the call {self.func.__name__}{args}")
    return self.func(*args)

@wrapper
def f2(x,y,z):
  print(f"{x} {y} {z}")

f2("abc", 7, "b")

print("....................")

