def open_file(name):
  with open(name, mode="r", encoding="utf-8") as f:
    words = []
    current = ""
    for i in f.read():
      if (i >= "a" and i <= "z") or (i >= "A" and i <= "Z") or i == "-" or i == " ": 
        if i == " ":
          words.append(current)
          current = ""
        current += i
    words.append(current)
    words = [w for w in words if w != " "]
    return words
# words = open_file("wikipedia-excerpt.txt")
# print(words)

class UpDownFile(object):
  def __init__(self, name):
    self._name = name
    self._file = open_file(name)
    print(self._file)

  def __iter__(self):
    self.i = 0
    return self
 
  def __next__(self):
    if self.i == len(self._file):
      raise StopIteration
    n = self._file[self.i]
    self.i += 1
    return n

  def ungetw(self):
    self.i -= 1
