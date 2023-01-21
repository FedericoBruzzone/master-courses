from itertools import *

class Golfer(object):
  def __init__(self, name, color, index):
    self.name  = name 
    self.color = color
    self.index = index
  def __str__(self):
    return f"Golfer {self.name} is in position {self.index} and wears some {self.color} pants"

class Drools(object):
  def __init__(self, rules, names, colors, indexes):
    self.rules   = rules
    self.names   = names
    self.colors  = colors
    self.indexes = indexes
  
  def eval(self):
    print(self.names, self.colors, self.indexes)
    all_premutations = [[Golfer(name,color,index) for name,color,index in zip(self.names, c, i)] for i in permutations(self.indexes, len(self.indexes)) for c in [c for c in permutations(self.colors, len(self.indexes))]]
    for i in filter(lambda golfers: all([rule(golfers) for rule in self.rules]), all_premutations).__next__():
      print(i)
