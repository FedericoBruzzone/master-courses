def _get(k): return None
def _show(): return ""
def get(k): return None if _get(k) == 'DoesNotExist' else _get(k)
def show():
    memo = []
    return ' '.join([((element[0] not in memo and (memo.append(element[0]) or True) and element) or '') for element in _show().split()])

def add(key, value):
    old_get = globals()["_get"]
    globals()["_get"] = lambda k: key==k and value or old_get(k)
    old_show = globals()["_show"]
    globals()["_show"] = lambda : f"{key}:{value} " + old_show() 

def remove(key, value='DoesNotExist'):
    return add(key, value)

add('1', '1')
add('2', '2')
add('3', '3')
add('4', '4')
print(show())
print(get('2'))
remove('2')
print(show())
print(get('2'))
add('2', '2')
print(show())
print(get('2'))
print(show())