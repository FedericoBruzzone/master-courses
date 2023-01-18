class calculator(object):
    
  def __init__(self, expr):
    self._expr = expr
    self._parse_expr = self.__convert(expr)

  def __convert(self, expr):
    stack = []
    self.c = 0
    self.open = -1 
    def inner(expr, res):    
      if expr == "":
        if self.open == 0: self.open = 1
        [res.append(")") for i in range(self.open)]
        return res
      if expr[0].isnumeric():
        self.c += 1
        res.append(expr[0])
        if self.c == 2:
          self.c = 0
          res.append(")") 
          self.open -= 1
          if self.open >= 1 and len(stack) <= 1:
            print(expr, stack)
            res.append(")")
        if len(stack) > 0:  
          res.append(stack.pop())
        return inner(expr[1:], res)
      else:
        stack.append(expr[0])
        if self.c < 2:
          self.c = 0
          self.open += 1
          res.append("(")
        return inner(expr[1:], res)
    return inner(expr, [])

  def __str__(self): 
    return "".join(self._parse_expr)

def is_internal(expr, i):
  while i < len(expr):
    if expr[i] == "(":
      return False 
    elif expr[i] == ")":
      return True
    i += 1

def print_reduction(calculator_t):
  expr = calculator_t.__str__()
  def inner(expr):
    print("".join(expr))
    if len(expr) < 3: return
    close_index = [(i, list(expr).index(")", i, len(expr))) \
                    for i in range(len(expr)) \
                       if expr[i] == "(" and \
                          is_internal(expr, i+1)] 
    res = []
    current = 0 
    i = 0
    while i < len(expr)-len(close_index):      
      f,t = close_index[current]
      if i < f:  
        res.append(expr[i])  
        i += 1
      else:
        current += 1 
        res.append(str(eval(expr[f:t+1])))
        i = t+1
    if len(res) > 2: res.append(expr[-1])    
    inner("".join(res))
  return inner(expr)


if __name__ == "__main__":
  expressions = ["+34", "+3-15", "*+34-23", "+7++34+23", "*+*34-34/6-35", "/+-81*45*/93/52", "*+/12/14-2/32", "+2*-53/63"]
  c_expressions = []
  for expr in expressions: c_expressions.append(calculator(expr).__str__())
  for i in c_expressions: print(i)
  # print_reduction(c_expressions[2])
