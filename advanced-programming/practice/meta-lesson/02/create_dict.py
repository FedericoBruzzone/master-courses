T9 = {"2": ["a", "b", "c"], 
      "3": ["d", "e", "f"],  
      "4": ["g", "h", "i"],         
      "5": ["j", "k", "l"],        
      "6": ["m", "n", "o"], 
      "7": ["p", "q", "r", "s"],         
      "8": ["t", "u", "v"], 
      "9": ["w", "x", "y", "z"]}
i_T9 = {i:k for k,v in T9.items() for i in v}  
  
def d_print(d):
  for k,v in d.items():
     print(k,":", v)

if __name__ == "__main__":
  d_print(T9) 
  d_print(i_T9) 
 
