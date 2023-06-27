from wormhole import *

accounts = { 
     11: Account(11, "Walter"),
     12: Account(12, "Cazzola"),
     13: Account(13, "WCazzola")
   }

class ATM:
  def __init__(self, idn):
    self.idn = idn
  def deposit(self, accnumber, amount):
    accounts[accnumber].deposit(amount)
  def withdraw(self, accnumber, amount):
    accounts[accnumber].withdraw(amount)
  def balance(self, accnumber):
    return accounts[accnumber].balance()

