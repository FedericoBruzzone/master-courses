def decorator(cls):
  class wrapper(object):
    def __init__(self, *args):  
      print(f"I'm creating {cls.__name__}{args}")
      self.wrapped = cls(*args)
    def __getattr__(self, name):
      print(f"I'm fetching {self.wrapped}.{name}")
      return getattr(self.wrapped, name)
    def __setattr__(self, attribute, value):
      print(f"I'm setting {attribute} to {value}")
      if attribute == "wrapped":
        self.__dict__[attribute] = value
      else:
        return setattr(self.wrapped, attribute, value)
  return wrapper

@decorator
class C(object):
  def __init__(self, x, y):
    self.attr = "spam"
  def f(self, a, b):
    print(f"{a} {b}")

x = C(6,7)
print(x.attr)
x.attr = "tmp"
print(x.attr)

