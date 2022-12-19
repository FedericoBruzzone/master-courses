

#@tail_recursion
def tfact(n, acc=1):
  if n == 0:
    return acc
  return tfact(n-1, n*acc)

#@tail_recursion
def tfib(i, current = 0, next = 1):
  if i == 0:
    return current
  else:
    return tfib(i - 1, next, current + next)

if __name__ == "__main__":
  print("10000! :-", tfact(10000))
  print("fib(10000) :-", tfib)
