def palin(s: str):
  def inner(s: str, i: int):
    for j in range(j, len(s)):
      print("hello")
  
  for i in range(s):
    left_to_right += inner(s, i)
    right_to_left += inner(s[::-1], i)
  return ""
