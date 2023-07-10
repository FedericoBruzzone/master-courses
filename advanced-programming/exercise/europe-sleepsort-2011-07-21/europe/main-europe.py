
# to simple

def crossing(name, steps):
    pass

if __name__ == '__main__':
  print("*** From Italy in ")
  for steps in range(0,8):
    print("[{0}] = {1}".format(steps, crossing("italy", steps)))

  print("*** From Sweden in [5] steps, you get in", crossing('sweden', 5))
  print("*** From Germany in [2] steps, you get in", crossing('germany', 2))
  print("*** From Iceland in [3] steps, you get in", crossing('iceland', 3))

