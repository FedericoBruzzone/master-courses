import inspect
import re

from pprint import pprint

def deepcopy(l):
  lcopy = list()
  for elem in l:
    if type(elem) is list:
      lcopy.append(deepcopy(elem))
    else: lcopy.append(elem)
  return lcopy

def delete(self):
  current_frame = inspect.currentframe()
  outer_frame = current_frame.f_back
  try:
    assignment = inspect.getframeinfo(outer_frame).code_context[0]
  except AttributeError: return
  m = re.search("([a-zA-Z][a-zA-Z0-9_]*)[ ]*=[ ]*(.*)$", assignment)
  outer_frame.f_locals[m.group(1)] = deepcopy(eval(m.group(2), outer_frame.f_locals))

class DeepCopyList(type):
  def __new__(meta, classname, supers, classdict):
    classdict["__del__"] = delete
    return type.__new__(meta, classname, supers, classdict)
  def __init__(clazz, classname, supers, classdict):
    supers.__init__(clazz, classname, supers, classdict)

original_list = list

list = DeepCopyList("list", (original_list,), dict(list.__dict__))

