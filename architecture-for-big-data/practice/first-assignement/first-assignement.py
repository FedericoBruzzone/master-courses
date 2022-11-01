# MIRO FILE: https://miro.com/app/board/uXjVPL-PaYM=/

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
import mysql.connector

'''
@dev AbstractDatabase is an abstract class that contains the information to connect with any database
'''
class AbstractDatabase(ABC):
    _user: str = ''
    _password: str = ''
    _port: str = ''
    _host: str = ''
    _database: str  = ''

    def __init__(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._host = host
        self._database = database
        self._user = user
        self._password = password
        self._port = port

    @abstractmethod
    def execute_query(self, sql): pass

'''
@dev IDatabaseSQL are an abstract classes that define behavior of the specified database
'''
class IDatabaseSQL(AbstractDatabase):
    _connection: str = ''
    _curr: str = ''

    def __init__(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = self._database_connection(user, password, host, database, port)
        self._curr = self._connection.cursor()
        super().__init__(user, password, host, database, port)

    @abstractmethod
    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''): pass
    
    def execute_query(self, sql): 
        self._curr.execute(sql)
        res = [i for i in self._curr]

        print(f'\nQuery has been executed: {sql}')
        for i in res:
            print(f'{i}')
       
        return res

'''
@dev Service is a concrete class that contains the methods to read from the external database
'''
class Service(IDatabaseSQL): 
    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = mysql.connector.connect(user=user, password=password, host=host, database=database)
        print(f'Connection successfull to the: {host} {database} {user} {password} {port}')
        return self._connection

'''
@dev ClientInterface is an interface that contains the delcarations of methods defined for each adapters
'''
class ClientInterface(ABC):
    @abstractmethod
    def _execute(self, sql): pass

    @abstractmethod
    def read_from(self, sql): pass

'''
@dev ServiceAdapter is a concrete class that inherits from ClientInterface and contains a Service object. 
     Its purpose is to get data from service and make it readable for the client 
     through the implemented ClientInterface methods.
'''
class ServiceAdapter(ClientInterface):
    service: Service = ''

    def __init__(self, service: Service):
        self.service = service
        print('ServiceAdapter has been created')

    def _execute(self, sql):
        query_res = self.service.execute_query(sql)
        return query_res
    
    def read_from(self, sql):
        query_res = self._execute(sql) 
        return query_res

'''
@dev Client is a concrete class that contains the methods to write in the historical database,
     to make it, it use the ClientInterface methods to read adapted data from the external database.
'''
class Client(IDatabaseSQL): 
    clientInterface: ClientInterface = ''

    def __init__(self, clientInterface: ClientInterface, user: str, password: str, host: str, database: str, port: str = ''):
        self.clientInterface = clientInterface
        super().__init__(user, password, host, database, port)

    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = mysql.connector.connect(user=user, password=password, host=host, database=database)
        print(f'Connection successfull to the: {host} {database} {user} {password} {port}')
        return self._connection

    def write_to(self, read_query, table, column='') -> Bool:
        response = self.clientInterface.read_from(read_query)
        new_column = f"({', '.join(column)})"
        for i in response:
            self.execute_query(f'INSERT INTO {table} {new_column} VALUES {(i)};') 

        self._connection.commit()
        return True

if __name__ == '__main__':
    cloud_database: Service = Service(user='root', password='welcome123', host='127.0.0.1', database='test_database')
    adapter_cloud_database: ServiceAdapter = ServiceAdapter(cloud_database)
    local_database: Client = Client(adapter_cloud_database, user='root', password='welcome123', host='127.0.0.1', database='test_database')
    local_database.write_to('SELECT name, surname FROM user', 'user2', ('name', 'surname'))

    
