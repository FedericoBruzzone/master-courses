'''A social network is a social structure made of individuals (or organizations) called nodes, which are tied (connected) by one or more specific types of interdependency, such as friendship, kinship, financial exchange, dislike, sexual relationships, or relationships of beliefs, knowledge or prestige.

A graph is an abstract representation of a set of objects where some pairs of the objects are connected by links. The interconnected objects are represented by mathematical abstractions called vertices, and the links that connect some pairs of vertices are called edges.

The exercise consists of:

1. to implement the social network as a graph, i.e., to define the graph data structure with the operations you consider necessary to implement a social network
2. to implement an operation that visits in a convenient way all the elements of the graph, such an operation should be associated to the __str__ operation of the graph implementation
3. to test it against a dummy social network.'''

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
