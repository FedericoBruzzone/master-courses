from healting import *

if __name__ == "__main__":
    resource = 10
    print("{0}! :- {1}".format(10,fact(10)))
    resource = 9
    try:
        print("{0}! :- {1}".format(10,fact(10)))
    except SystemExit: pass
    resource = 160
    try:
        print("fibo({0}) :- {1}".format(10,fibo(10)))
    except SystemExit: pass
    resource = 180
    print("fibo({0}) :- {1}".format(10,fibo(10)))
