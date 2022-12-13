from create_dict import *

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

  def process_input(sentence):
      pass

if __name__ == "__main__":
  cc = CorrectCombination()
  cc.read_and_create("words_test.txt")

