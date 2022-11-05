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