'''
Exercise 1: Calculator.
Let us consider a small language for expression with the binary operators +, -, *, / (with the obvious meaning) and the operands 0, 1, ..., 9 (note that an expression can only have figures from 0 to 9 but the intermediate and final results can be greater than this). To simplify the expressions are written in polish notation, i.e., all the operators come before of their operands (e.g., (3+4)*5 is equal to * + 3 4 5).

An expression can be mathematical evaluated or converted in a stack-based assembler with the following statements: store n, add, sub, mul, div where store n will push on the stack the value of n and the other statements pop the two elements on the top and push the result on the top.

Write a calculator class with the methods:

__init__ that takes a string representing the expression in polish notion
eval with no args that mathematical evaluates the expression used in the constructor and returns the result
code with no args that generates the assembler corresponding to the expression and returns it as a string with a statement per row.
Of course the use of eval is forbidden and all the passed expressions are correct so no checks on inputs are necessary. The following is an example of usage


   from calculator import *
   calc = calculator('+2*-53/63')
   print(calc.eval())
   print(calc.code(),end='')

Note that, closures, recursion and dictionaries can help a lot.
'''

import functools

class calculator():
    polish_notation: str  = ""
    polish_list: list = []

    def __init__(self, polish_notation: str):
        self.polish_notation = polish_notation
        self.polish_list = self.polish_notation.split()
    
    # def eval(self):
    #     polish_list = self.polish_notation.split()
    #     operators = dict(zip(['+', '-', '*', '/'], [lambda x,y: x+y, lambda x,y: x-y, lambda x,y: x*y, lambda x,y: x/y]))
    #     sign = list(reversed([i for i in polish_list if not i.isnumeric()])) # use filter()
    #     num = [int(i) for i in polish_list if i.isnumeric()]

    #     memo_res = num[0]

    #     def inner_eval(sign, num, memo_res): 
    #         if len(num) == 0: return memo_res 
    #         memo_res = operators[sign[0]](memo_res, num[0])
    #         return inner_eval(sign[1:], num[1:], memo_res)

    #     return inner_eval(sign, num[1:], memo_res)

    def eval(self):
        operators = dict(zip(['+', '-', '*', '/'], [lambda x,y: x+y, lambda x,y: x-y, lambda x,y: x*y, lambda x,y: x/y]))
    
        def gen_sign():
            for i in list(reversed([i for i in self.polish_list if not i.isnumeric()])):
                yield i

        g = gen_sign()
        return functools.reduce(lambda x, y: operators[next(g)](x,y), [int(i) for i in self.polish_list if i.isnumeric()])

    def code(self):
        operators = dict(zip(['+', '-', '*', '/'], ['add', 'sub', 'mul', 'div']))

        sign = list(reversed([i for i in self.polish_list if not i.isnumeric()]))
        num = [i for i in self.polish_list if i.isnumeric()]

        return f'store {num[0]} \n' + ('store {}\n{}\n' * len(sign)) \
            .format(*[a for b in zip(num[1:], [operators[s] for s in sign]) for a in b])

c = calculator('* + 3 4 5')
print(c.eval())
print()
print(c.code())
