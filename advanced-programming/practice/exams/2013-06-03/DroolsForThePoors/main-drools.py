from drools import *

# l = list(Golfer)
joe_is_second = lambda golfers: [True for golfer in golfers if golfer.name == "joe" and golfer.index == 2].count(True) == 1 
bob_blue_pants = lambda golfers: [True for golfer in golfers if golfer.name == "bob" and golfer.color == "plaid"].count(True) == 1
fred_right_is_blue = lambda golfers: [[True for golfer_2 in golfers if golfer_2.index==golfer.index+1 and golfer_2.color == "blue"] for golfer in golfers if golfer.name == "fred"].count([True]) == 1
one_is_red = lambda golfers: [True for golfer in golfers if golfer.color == "red"].count(True) == 1
tom_orange_not_four_and_one = lambda golfers: [True for golfer in golfers if golfer.name == "tom" and golfer.index not in [1,4] and golfer.color != "orange"].count(True) == 1

rules = [joe_is_second, \
         bob_blue_pants, \
         fred_right_is_blue, \
         one_is_red, \
         tom_orange_not_four_and_one]

if __name__ == "__main__":
  d = Drools(rules, \
             ['bob', 'joe', 'fred', 'tom'], \
             ['red', 'orange', 'blue', 'plaid'], \
             list(range(1,5)))
  d.eval()
