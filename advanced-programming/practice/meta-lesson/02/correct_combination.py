from create_dict import *
import time

class CorrectCombination():
  words_dict = dict()
  
  @staticmethod
  def word_to_number(word):
      return ''.join(list(map(lambda c: i_T9[c], word)))

  def read_and_create(self, name):  
    with open(name, encoding="utf-8") as f:
      for word in f.readlines():
        k = self.word_to_number(word.strip()) 
        if (self.words_dict.get(k) == None):
          self.words_dict[k] = [word.strip()]
        else:
          self.words_dict[k].append(word.strip())

  def process_input(self, sentence):
    s = sentence.split()
    def inner(s, res=[]):
      if (len(s) == 0): 
        return res
      res = list(map(lambda x,y: x + " " + y, res*len(self.words_dict[s[0]]), self.words_dict[s[0]] * len(res)))
      return inner(s[1:], res)
    print('\n'.join(inner(s[1:], self.words_dict[s[0]])))
   
if __name__ == "__main__":
  i = "48 26624637 843 476877 63 5388377 66 3224 74663 539 9484 2 3278 222377 3428466279"
  cc = CorrectCombination()
  cc.read_and_create("words.txt")
  start = time.time()
  cc.process_input(i)
  end = time.time()
  total_time = end - start
  print("\n"+ "time: " + str(total_time))

