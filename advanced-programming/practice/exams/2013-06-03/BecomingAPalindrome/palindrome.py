def palin(s: str):
  def inner(s: str, i: int, reverse: bool = False):
    res = []
    stack = [s[j] for j in range(i+1)]
    first = True
    for j in range(i, len(s)):
      stack = stack[:-1] if first and len(stack) > 1 and stack[-1] == stack[-2] else stack
      if len(stack) == 0: 
        res += list(s[j:]) if not reverse else list(reversed(s[j:]))
        break
      while len(stack) > 0 and stack[-1] != s[j] and not first:
        res += list(stack[-1])         
        stack = stack[:-1]
      first = False
      stack = stack[:-1]
    return res + stack if not reverse else res + list(reversed(stack))  
 
  tmp = []
  for i in range(1, len(s)):
    tmp.append(inner(s, i))
    tmp.append(inner(s[::-1], i, True))
  res = min(tmp, key=len) 
  return f"The word \"{s}\" needs {len(res)} insertions to become palindrome: {res}"
