def diff(filename, filename2):
  mylist = []
  c = 0
  with open(filename, encoding="utf-8") as f:
    for s in f.readlines():
      mylist.append(s)
    mylist.sort()
  with open(filename, encoding="utf-8") as f2:
    for s in f2.readlines():
      if s not in mylist:
        return False
    return True 

print(diff("prof.txt", "my.txt"))
