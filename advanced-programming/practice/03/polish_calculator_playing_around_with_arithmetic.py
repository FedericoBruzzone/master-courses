

class PolishCalculator:
    def __init__(self): 
        add = lambda x,y: x+y
        sub = lambda x,y: x-y
        mul = lambda x,y: x*y
        div = lambda x,y: x/y

        self.operand = {"+" : add, "-" : sub, "*" : mul, "/" : div}
        self.stack = []
        
    def __str__(self): pass

    def eval(self, polish_str): 
        if polish_str == "": return False
        if polish_str[0].isdigit(): self.stack.insert(0, polish_str[0])
        if not polish_str[0].isdigit(): 
            self.operand(self.stack[0])(self.stack.pop(), self.stack.pop())
        return eval(self, polish_str[1:])

x = PolishCalculator()
x.eval("34+")