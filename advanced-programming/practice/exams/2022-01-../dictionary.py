class Dictionary(object):
    class Node:
        f = None
        nxt = None

        def __init__(self, f=lambda k: None):
            self.f = f

        def my_set(self, n2):
            self = n2
            return True

    def __init__(self): self.n = self.Node()

    def get(self, k):
        def inner(n, k): return (n.nxt == None and None) or n.f(k) if n.f(k) != False else inner(n.nxt, k)
        return inner(self.n, k)

    def add(self, k, v):
        if self.get(k) == None:
            new_node = self.Node(lambda key: (key == k and v) or False)
            new_node.nxt = self.n; self.n = new_node

    def remove(self, k):
        def inner(n, k):
            if n.nxt == None: return None
            if n.nxt.f(k) != False: n.nxt = n.nxt.nxt
            return inner(n.nxt, k) 
        if self.n.f(k) != False: self.n = self.n.nxt
        else: return inner(self.n, k)
        
if __name__ == "__main__":
    d = Dictionary()
    d.add(1,2)
    d.add(2,3)
    d.add(3,4)
    d.add("key", "value")
    d.add("key", "x")

    print(d.get("key"))
    print(d.get(1))
    print(d.get(3))

    d.remove(1)
    print(d.get(1))
    print(d.get("key"))
