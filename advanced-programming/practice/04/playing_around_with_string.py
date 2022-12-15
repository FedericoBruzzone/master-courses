"""
To extend the class for the strings to support the following operations:

1. to check if the string is palindrome, a string is palindrome when the represented sentence can be read the same way in either directions in spite of spaces, punctual and letter cases, e.g., detartrated, "Do geese see God?", "Rise to vote, sir.", ...

2. to subtract the letters in a string from the letters in another string, e.g., "Walter Cazzola"-"abcwxyz" will give "Wlter Col" note that the operator - is case sensitive and that the target should be a name containing an instance of the child class

3. given a dictionary of strings, to check if the string is an anagram of one or more of the strings in the dictionary
"""

import re
import itertools

class MyString(str):
  
  def __init__(self, d):
      self.d = d

  def isPalindrome(self):
    t = re.sub(r"[^\w\s]", "",self.lower().strip().replace(" ", ""))
    return t == t[::-1]    
 

  def __sub__(self, other):
      return MyString(self.translate({ord(i):None for i in other})) 

  
  def __anagram(self, y):
    return [''.join(p) == y for p in itertools.permutations(self)]   


  def isAnagram(self, d):
    return any([self.__anagram(i) for i in d])

if __name__ == "__main__":
  t1 = "detartrated"
  t2 = "Do geese see God?"
  t3 = "Rise to vote, sir."
  s1 = MyString(t1)
  s2 = MyString("de")
  print(s1.isPalindrome())
  print(s1 - s2)
  st = MyString("abc")
  print(st.isAnagram(["abc"]))
