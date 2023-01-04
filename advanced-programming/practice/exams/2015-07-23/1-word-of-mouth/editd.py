import re
import itertools

def open_file():
  with open("dict.txt", mode="r", encoding="utf-8") as f:
    words = [l.strip() for l in f.readlines()]
    return words

global words
words = open_file()

def update_res(res, all_words):
  if len(all_words) <= 1: return res 
  tmp = [res for i in range(len(all_words))] 
  
  for w in all_words: 
    for i in range(len(res)-1):
      if res[i][-1] != w:
        tmp[i].append(w)
  return tmp 

def chain(f: str, t: str): 
  global res
  res = [f]
  def inner(f, t, l):
    if l == len(f)-1:
      return 
    if f[l] == t[l]: 
      return inner(f, t, l+1)
    if f[l] != t[l]:
        pattern = t[:l] + "\S" + f[l+1:]
        # second pattern inverse
        all_words = [word for word in words if re.match(pattern, word) and word != f]
        print("all: ", all_words)
        global res 
        res = update_res(res, all_words)
        print(res)
        return inner(f, t, l+1)
  return inner(f, t, l = 0)


