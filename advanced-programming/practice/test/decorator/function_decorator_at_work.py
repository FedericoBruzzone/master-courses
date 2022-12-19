import time

class timer(object):
  def __init__(self, func):
    self.func = func
    self.alltime = 0
  def __call__(self, *args, **kargs):
    start = time.time()
    result = self.func(*args, **kargs)
    elapsed = time.time() - start
    self.alltime += elapsed
    print("{0}: {1:.5f}, {2:.5f}".format(self.func.__name__, elapsed, self.alltime))
    return result

@timer
def listcomp(N):
  return [x * 2 for x in range(N)]

@timer
def mapcall(N):
  return list(map((lambda x: x * 2), range(N)))

if __name__ == "__main__":
  result = listcomp(5)
  listcomp(50000)
  listcomp(500000)
  listcomp(1000000)
  print(result)
  print("allTime = {0}".format(listcomp.alltime))
  print("")
  result = mapcall(5)
  mapcall(50000)
  mapcall(500000)
  mapcall(1000000)
  print(result)
  print("allTime = {0}".format(mapcall.alltime))
  print("map/comp = {0}".format(round(mapcall.alltime / listcomp.alltime, 3))) 
