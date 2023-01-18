def gray(n):
  def inner(n):
    if n == 0: 
      return [""]
    else:
      comp = inner(n-1)
      w_zero = ["0" + c for c in comp]
      w_one = ["1" + c for c in reversed(comp)]
      return w_zero + w_one
  for res in inner(n):
    yield res

def memoization(f):
  cache = {}
  def wrapper(n):
    if n in cache:
      return cache[n]
    print(f"### cached value for ({n},) {cache[n]}")
    cache[n] = f(n)
    return f(n)
  
  return wrapper

mgray = memoization(gray) 
