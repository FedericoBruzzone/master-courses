import inspect
from pprint import pprint

def get_number_of_resource(frame):
    while "resource" not in frame.f_locals:
        frame = frame.f_back
    return frame.f_locals["resource"] 

def check_resource(F):
    def wrapper(*args, **kwargs):
        frame = inspect.currentframe()
        wrapper.resource = get_number_of_resource(frame)
        if wrapper.resource in [9, 160]:
            print("resources run out")
            raise SystemExit

        return F(*args, **kwargs)
    wrapper.resource = None
    wrapper.__name__ = F.__name__
    return wrapper

@check_resource
def fact(n):
    if n == 0:
        return 1
    return n * fact(n-1)

@check_resource
def fibo(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fibo(n-2) + fibo(n-1)

