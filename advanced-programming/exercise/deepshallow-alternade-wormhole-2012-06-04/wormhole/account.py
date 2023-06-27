class Account:
  def __init__(self, number, owner):
    self.tot = 0
    self.number = number
    self.owner = owner
  def deposit(self, amount):
    self.tot += amount
  def withdraw(self, amount):
    self.tot -= amount
  def balance(self):
    return self.tot

