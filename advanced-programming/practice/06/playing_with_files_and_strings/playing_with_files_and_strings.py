import re

def process_input():
 with open("./input.txt", mode="r", encoding="utf-8") as f:
  pattern = r"\s{2,}"
  res = [re.sub(pattern, " ", l.strip()) for l in f.readlines()] # " ".join(s.split())
 return res

def words_without_useless_words(w):
 not_w = ('and', 'the', "And", "The")   
 return [i for i in w.split(" ") if len(i) > 2 and i not in not_w]

def duplicate_sorted_words(words):
 res = []
 for w in words:   
  u_words = words_without_useless_words(w) 
  res.append((u_words[0],w))
  for i in range(1, len(u_words)):
   res.append((u_words[i],w))
 return sorted(res, key=lambda k: k[0])

def create_key_word_in_context(ds_words):
 template = """{:>5}{:>40.33}{:^}{:<0.40}\n"""
 output = "" 

 for w in ds_words:
  index = w[1].index(w[0])   
  if index != 0:
   output += template.format("0", w[1][:index], w[1][index], w[1][index+1:])
  else:
   output += template.format("1", "", w[1][index:], "")    
 return output

def main():
 words = process_input()
 ds_words = duplicate_sorted_words(words)
 res = create_key_word_in_context(ds_words)
 print(res) 

if __name__ == "__main__":
 main() 
