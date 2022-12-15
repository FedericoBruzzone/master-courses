class SocialNetwork(object):
  
  edges = []

  class Edge():
    def __init__(self, f, t, v=""):
      self.f = f
      self.t = t
      self.v = v
    
    def __str__(self):
      return self.f + f" ...({self.v})... " + self.t

  def addEdge(self, f, t, v=""):
    E = self.Edge(f, t, v)
    self.edges.append(E)
  
  def __iter__(self):
    self.i = 0
    return self

  def __next__(self):
    if self.i == len(self.edges): raise StopIteration  
    res = self.edges[self.i]  
    self.i += 1  
    return res  

  def __str__(self):
    return ''.join([str(i)+"\n" for i in self.edges])

if __name__ == "__main__":
  SN = SocialNetwork()
  SN.addEdge("Jhon", "Kate", "friendship")
  SN.addEdge("George", "Susan", "kindship")
  print(SN)
