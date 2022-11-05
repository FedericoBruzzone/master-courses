 '''
 Exercise 2: Multi-Triggered Methods.
Sometimes is necessary to delay the execution of a method until a condition is verified, e.g., in cryptography when two key are necessary to decrypt a text we have to wait that the decrypt method is called with both keys.

A similar behavior can be achieved by really calling the method only after a given number of calls on a rearrangement of the whole set of passed arguments.

Implements such a behavior through a parametric decorator multi_triggered. Such a decorator should have a couple of parameters: the first expresses how many times the method should be call before being really activated, the second is a function which applies on the values used in each call for each parameter. Of course we are speaking about decorators applicable on method not class definitions.

The following is an example of use:


  class ToBeMultiTriggered:
    def m1(self): print("### m1 has been called!")
    @multi_triggered(2, lambda x,y: x*y)
    def m2(self, i): print("### m2({0}) has been called!".format(i))
    @multi_triggered(3, lambda x,y: x+y)
    def m3(self, x, y): print("### m3({0},{1}) has been called!".format(x,y))

  if __name__ == "__main__":
    to_be = ToBeMultiTriggered()
    to_be.m1()
    to_be.m2(5)
    to_be.m3('a',3)
    to_be.m2(7)
    to_be.m3('b', 5)
    to_be.m2(3)
    to_be.m3('c', 7)

whose execution gives:


   [11:59]cazzola@ulik:~>python3 multi-triggered.py
   ### m1 has been called!
   ### m2(35) has been called!
   ### m3(abc,15) has been called!
'''
