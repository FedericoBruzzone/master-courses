import threading

class asynchronous(object):
 
  def __init__(self, func):
    self.func = func
  
  class NotYetDoneException(Exception):
    def __init__(self, message: str):
      self.message = message

  class inner():
    
    def run(self, func, *args):
      self.res = func(*args)

    def __init__(self, func, *args):
      self.execution = threading.Thread(target=self.run, args=[func, *args])
      self.res = self.execution.start()
     
    def is_done(self):
      return not self.execution.is_alive() 

    def get_result(self):
      if not self.is_done():
        raise asynchronous.NotYetDoneException("NotYetDoneException")
      return self.res
   
  def start(self, *args):
    return asynchronous.inner(self.func, *args)
  

