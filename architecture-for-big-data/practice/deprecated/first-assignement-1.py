# 1. Satisfying requirements
# - Functional Requirement
# - Technical Requirement
# - Security requirement

# 2. Design
# - Modularization and detailed interface to satisfy the requirements

# 3. Process management (and Cost estimation)

# 4. Enabling component Reuse

# 5. Allowing a tidy scalability

# 6. (Avoiding handover and people lock-in)

from abc import ABC, abstractmethod

class AbstractDatabase(): pass
    # DB
    # server
    # user = ''
    # pwd

    # @abstractmethod
    # database_conn()

# Input Database (SquarePeg)
class Service(AbstractDatabase): pass
    # def __init__(DB, server): pass

    # @overrides
    # database_conn(): pass

    # remove_security(): pass

# Get from their Database (SquarePegAdapter)
class Adapter(ClientInterface):
    # def __init__(self, Service):
    #    connection()

    # connection(Service):
    #   Service.database_conn()

    # readFromService(TABLE, <parameters>):
    #   select *
    #   from database.TABLE

    # queryToReadable() # from db to readable
    #   generic method given a query returns dict of index_row: list[row_value]
    #   table: List[] = [name, ...]

    # @override
    # getData():
    #   return queryToReadable()

# Abstraction for our "local" Database
class AbstractClient(ABC):
    # @abstractmethod
    # writeData(): pass

# Write on our "local" Database (RoundHole)
# AbstractDatabase: if I would like to read from sql_db and write on excel file, i don't need this implementation (AbstractDatabase)
class Client(AbstractClient, AbstractDatabase):
    # def __init__(DB, server, ClientInterface): pass

    # @override
    # writeData():
    #   ClientInterface.getData()

    # @override
    # database_conn(): pass

    # lookAt():
    #   ClientInterface.getData()

# Data representation (RoundPeg)
class ClientInterface(ABC): pass
    # @abstractmethod
    # getData(): pass



if __name__ == '__main__':
    # mongo_service = Service2('Mongo', 'localhost')
    sql_service = Service('SQL', 'www.cloud_server.com::6969')

    adapter_sql_service = Adapter(sql_service)

    local_db = Client('Mongo', 'www.my_server.com::2424', adapter_sql_service)

    local_db.writeData()

    local_db.lookAt()

    # python3 nome_programma('Mongo', 'user', 'pwd')
    # switch kargs[1]:
    #   case 'mongo':
    #   mongo_service = Service2(args)
    #   start(mongo)


          #ad1
        #/     \
#clouddb - ad2 - localdb
        #\     /
          #ad3
