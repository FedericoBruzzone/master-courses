class CreateDict():
  T9 = dict()
  i_T9 = dict()

  def __init__(self):
    self.T9 = {"2": ["a", "b", "c"], 
          "3": ["d", "e", "f"],  
          "4": ["g", "h", "i"],         
          "5": ["j", "k", "l"],        
          "6": ["m", "n", "o"], 
          "7": ["p", "q", "r", "s"],         
          "8": ["t", "u", "v"], 
          "9": ["w", "x", "y", "z"]}
  
  def print(self, d):
    for k,v in d.items():
       print(k,":", v)
  
  def invert_T9(self):
      self.i_T9 = {v:k for k,v in self.T9.items()}

if __name__ == "__main__":
  cd = CreateDict()
  cd.invert_T9()

  cd.print(cd.T9) 
  cd.print(cd.i_T9) 
 
