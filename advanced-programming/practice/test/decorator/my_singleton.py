def singleton(cls):
  class wrapper(object):
    instance = None
    def __new__(self, *args):
      if self.instance == None:
        self.instance = cls(*args)
      return self.instance
  return wrapper

@singleton
class Person(object):
  def __init__(self, name):
    self.name = name
  def get_name(self):
    return self.name

if __name__ == "__main__":
  p1 = Person("Federico")
  p2 = Person("Greta")
  print(p1.name, p2.name)
