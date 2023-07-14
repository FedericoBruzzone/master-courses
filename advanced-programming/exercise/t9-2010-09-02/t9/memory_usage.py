import os

def memory_usage(F):
    def wrapper(*args):
        print(os.getpid())
        for l in [i for i in open(f"/proc/{os.getpid()}/status").read().splitlines()]:
            if "VmRSS" in l or "VmSize" in l:
                print(l)
        return F(*args)

    wrapper.__name__ = F.__name__    
    return wrapper
