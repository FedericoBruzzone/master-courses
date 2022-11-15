class Monoide:
    def __init__(self, S, o, i):
        self.S = list(S)
        self.o = o
        self.i = i

    def check_indentify(self):
        return all([self.o(self.i, self.S[x]) == self.o(self.S[x], self.i) == self.S[x] for x in range(len(self.S))])    

class Group:
    def __init__(self, S, o, i):
        self.S = list(S)
        self.o = o
        self.i = i

m = Monoide({True, False}, lambda x, y: x + y, False)
print(m.check_operation())
