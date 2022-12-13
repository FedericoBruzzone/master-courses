import inspect

class antialiasing(type):
    def __new__(cls, classname, supers, classdict):
        # print("__new__")

        classdict["__del__"] = lambda *args: print(inspect.currentframe().f_back)
        # print(inspect.currentframe().f_code)
        # print(f"""[__new__] cls: - {cls}\n
        # [__new__] classname: - {classname}\n
        # [__new__] supers: - {supers}\n
        # [__new__] classdict: - {classdict}""")
        # print(f"[__new__] dict :- {cls.__dict__.keys}")
        return type.__new__(cls, classname, supers, classdict)

    def __init__(cls, classname, supers, classdict):
        # print("__init__")
        # print(f"""[__init__] cls: - {cls}\n
        # [__init__] classname: - {classname}\n
        # [__init__] supers: - {supers}\n
        # [__init__] classdict: - {classdict}""")
        # print(f"[__init__] dict :- {cls.__dict__}")
        type.__init__(cls, classname, supers, classdict)
    
    def __call__(cls):
        print(f"""[__call__] cls: - {cls}\n
        [__call__] arg: - {"arg"}""")
        return type.__call__(cls)

tmp = list
list = antialiasing("list", (tmp, ), dict(list.__dict__))