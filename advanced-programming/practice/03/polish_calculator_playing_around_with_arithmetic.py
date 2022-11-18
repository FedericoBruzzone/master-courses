'''
Write a PolishCalculator class that implements a stack-based calculator that adopts polish notation for the expressions to be evaluated.

Polish Notation is a prefix notation wherein every operator follows all of its operands; this notation has the big advantage of being 
unambiguous and permits to avoid the use of parenthesis. E.g., (3+4)*5 is equal to 3 4 + 5 *.

An instance of the PolishCalculator class will understand the following API:

__init(self)__ with the obvious meaning
__str(self)__ which will format the expression in the corresponding infix notation
eval(str) which will evaluate the expression contained in the string str and written in the polish notation
The recognized operators are +, - (both unary and binary), *, /, ** over integers and floats, or, and and not over booleans. 
At least a space ends each operands and operators, T and F respectively represent True and False.

Hint. The evaluation/translation can be realized by pushing the recognized elements on a stack.
'''

import operator, functools

class PolishCalculator:
    def __init__(self, expr): 
        self.expr = expr.split()
        self.op = {
            "+"   : operator.add, 
            "-"   : operator.sub, 
            "*"   : operator.mul, 
            "/"   : operator.truediv,
            "**"  : operator.pow,
            "and" : operator.and_,
            "or"  : operator.or_,
            "not" : operator.not_}
        self.stack = []
        
    def __str__(self): 
        # brackets = {"*", "/", "**"}
        # def inner_str(expr):
        #     if len(expr) == 0: return False
        #     if len(expr) == 1: return expr[0] + " "
        #     if expr[-1] in brackets: return "(" +  inner_str(expr[:-2]) + ") " + str(expr[-1]) + " " + str(expr[-2])
        #     if expr[-1] not in brackets: return inner_str(expr[:-2]) + str(expr[-1]) + " " + str(expr[-2]) + " "
        # return inner_str(self.expr)
        return functools.reduce(lambda stack, c : 
            (c == "not") and (stack[:-1]+["{0} {1}".format(c, self._convert(stack[-1]))]) or
            (c in self.op.keys()) and (stack[:-2]+["({0} {1} {2})".format(stack[-2],c, stack[-1])]) or
            stack+[c], 
        self.expr[1:], 
        [self.expr[0]])[0]

    def _convert(self, char):
        return (char == 'T' and True) or (char == 'F' and False)

    def eeval(self): 
        swap = lambda x, y: (y, x)
        def inner_eval(expr):
            print(expr, "stack: ", self.stack)
            if len(expr) == 0: return self.stack[0]
            if expr[0].isdigit() or expr[0] in {'T', 'F'}: 
                self.stack.append(self._convert(expr[0]) if expr[0] in {'T', 'F'} else int(expr[0]))
            if not expr[0].isdigit() and not expr[0] in {'T', 'F'}:
                self.stack.append(self.op[expr[0]](self.stack.pop())) if expr[0] == 'not' else \
                self.stack.append(self.op[expr[0]](*swap(self.stack.pop(), self.stack.pop())))   
            return inner_eval(expr[1:])
        return inner_eval(self.expr)

x = PolishCalculator("3 5 + 2 / 5 **") # (((3 + 5 / 2) ** 5) => str[1:-1]
y = PolishCalculator("T F or T not and")
print(x.eeval())
print(y.eeval())
print(x)
print(y)

s = "((3 + 5 ) / 2) ** 5"