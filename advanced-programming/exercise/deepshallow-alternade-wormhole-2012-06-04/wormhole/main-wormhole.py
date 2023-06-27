from ATM import *

if __name__ == "__main__":
  atm1 = ATM(1)
  atm2 = ATM(2)
  atm3 = ATM(3)

  atm1.deposit(11, 2500)
  atm2.withdraw(12, 1000)
  atm3.deposit(12, 7000)
  atm3.deposit(13, 1500)
  atm1.withdraw(11, 500)
  atm2.withdraw(13, 1500)
  atm2.deposit(11, 7000)
  atm1.deposit(13, 3500)

  for number in accounts.keys():
    print("the account #{0} contains {1}€".format(number,atm1.balance(number)))

