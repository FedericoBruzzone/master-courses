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

class AbstractDatabase(ABC):
    user: str = ''
    password: str = ''
    port: str = ''
    host: str = ''
    database: str  = ''

    def __init__(self, user: str, password: str, host: str, database: str, port: str = ''):
        self.host = host
        self.database = database
        self.user = user
        self.password = password
        self.port = port

    @abstractmethod
    def execute_query(self, sql): pass

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
            print(f"{i}")
       
        return res
        
class Service(IDatabaseSQL): 
    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = mysql.connector.connect(user=user, password=password, host=host, database=database)
        print(f'Connection successfull to the: {host} {database} {user} {password} {port}')
        return self._connection
        
class ClientInterface(ABC):
    @abstractmethod
    def _execute(self, sql): pass

    @abstractmethod
    def read_from(self, sql): pass

class ServiceAdapter(ClientInterface):
    service: Service = ''

    def __init__(self, service: Service):
        self.service = service
        print('ServiceAdapter has been created')

    def _execute(self, sql):
        res = self.service.execute_query(sql)
        return res
    
    def read_from(self, sql):
        query_res = self._execute(sql) 
        return query_res

class Client(IDatabaseSQL): 
    clientInterface: ClientInterface = ''

    def __init__(self, clientInterface: ClientInterface, user: str, password: str, host: str, database: str, port: str = ''):
        self.clientInterface = clientInterface
        super().__init__(user, password, host, database, port)

    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = mysql.connector.connect(user=user, password=password, host=host, database=database)
        print(f'Connection successfull to the: {host} {database} {user} {password} {port}')
        return self._connection

    def write_to(self, cloud_query, table, column=''):
        response = self.clientInterface.read_from(cloud_query) 
        new_column = f"({', '.join(column)})"
        for i in response:
            #print(f'INSERT INTO {table} {(new_column)} VALUES {(name, surname)};')
            self.execute_query(f'INSERT INTO {table} {new_column} VALUES {(i)};')  
        self._connection.commit()
        return True

if __name__ == '__main__':
    cloud_database: Service = Service(user='root', password='welcome123', host='127.0.0.1', database='test_database')
    # cloud_database.execute_query('SELECT * FROM user')

    adapter_cloud_database: ServiceAdapter = ServiceAdapter(cloud_database)
    #adapter_cloud_database._execute('SELECT * FROM user')
    #adapter_cloud_database.read_from('SELECT * FROM user')

    local_database: Client = Client(adapter_cloud_database, user='root', password='welcome123', host='127.0.0.1', database='test_database')
    #local_database.execute_query('SELECT * FROM user')
    local_database.write_to('SELECT name, surname FROM user', 'user2', ('name', 'surname'))

    