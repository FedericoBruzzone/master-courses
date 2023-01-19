def even(G):
  y = next(G)
  while True:
    if y%2 == 0: 
      yield y
    y = next(G)

def stopAt(G, n):
  y = next(G)
  while y<=n:
    yield y
    y = next(G)

def buffer(G, n):
  buff = []
  try:
    while True:
      while len(buff) != n:
        buff.append(next(G)) 
      yield buff
      buff = []
  except Exception as e: 
    yield buff

def conditional(G, f):
  curr = next(G)
  while True:
    succ= next(G)
    if f(succ):
      yield curr
      curr = succ
    else:
      curr = succ  

