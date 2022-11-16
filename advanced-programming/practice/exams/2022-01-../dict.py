def _get(k): return None
def show(): return ""
def get(k): return None if _get(k) == -1 else _get(k)
    
def add(key, value):
    old_get = globals()["_get"]
    globals()["_get"] = lambda k: key==k and value or old_get(k)
    old_show = globals()["show"]
    globals()["show"] = lambda : f"{key}:{value} "+ old_show() 

def remove(key, value=-1):
    return add(key, value)

add('1', '1')
add('2', '2')
add('3', '3')
add('4', '4')
print(show())
print(get('2'))
remove('2')
print(get('2'))



# def find(k):
# 	return None

# def show():
# 	return ""

# def add(k,v):
# 	old_find = globals()["find"]
# 	old_show = globals()["show"]
# 	globals()["find"] = lambda x:x==k and v or old_find(x)
# 	globals()["show"] = lambda : f"{k}:{v} "+ old_show() 

# add('1', '1')
# add('2', '2')
# add('3', '3')
# add('4', '4')
# print(show())
# print(find(1))

