from create_dict import *
from itertools import permutations

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
    #d_print(self.words_dict)

  def get_combination(self, *args):
    sentence = args[0]
    def process_input(sentence):
      s = sentence.split()
      def inner(s, res=[]):
        if (len(s) == 0): 
          return res
        p_words = self.words_dict[s[0]] * len(res)
        res = res*len(self.words_dict[s[0]]) 
        res = list(map(lambda x,y: x + " " + y, res, p_words))
        return inner(s[1:], res)
      return inner(s[1:], self.words_dict[s[0]])
    return [''.join(i) for i in process_input(sentence)]
   
if __name__ == "__main__":
  #i = "968 273 788743" # you are stupid
  i = "48 26624637 843 476877 63 5388377 66 3224 74663 539 9484 2 3278 222377 3428466279" # 63 967370"
  cc = CorrectCombination()
  cc.read_and_create("words.txt")
  [print(c) for c in cc.get_combination(i)] 

