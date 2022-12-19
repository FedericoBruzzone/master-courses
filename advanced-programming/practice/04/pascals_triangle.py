class PascalTriangle():
  
  def __init__(self, depth):
    self.current = [[1], [1, 1]]
    self.depth = depth
  
  def __calculate(self):
    def inner(res):
      if len(self.current) == self.depth: return res    
      res.append([1] + [res[-1][i]+res[-1][i+1] for i in range(0, len(res[-1])-1)] + [1])
      return inner(res)
    return inner(self.current)        

  def __iter__(self):
    self.__calculate()  
    self.i = -1
    return self    
  
  def __next__(self):
    if self.i == self.depth: raise StopIteration   
    self.i += 1
    res = self.current[self.i]
    return res

  def prev(self):
    if self.i == -1: raise StopIteration
    res = self.current[self.i-1]
    self.i -= 1  
    return res

if __name__ == "__main__":
  pt = PascalTriangle(10)
  #[print(i) for i in pt]
  iter(pt)
  print(next(pt))
  print(next(pt))
  print(next(pt))
  print(next(pt))
  print(pt.prev())
  print(pt.prev())
