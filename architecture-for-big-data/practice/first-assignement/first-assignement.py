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

class AbstractDatabase(ABC):
    user: str = ''
    password: str = ''
    port: str = ''
    host: str = ''
    database: str  = ''

    def __init__(self, host: str, database: str, user: str = '', password: str = '', port: str = ''):
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

    def __init__(self, host: str, database: str, user: str = '', password: str = '', port: str = ''):
        self._connection = self._database_connection(user, password, port, host, database)
        # self._curr = self._connection.cursor()
        super().__init__(host, database, user, password, port)

    @abstractmethod
    def _database_connection(self, host: str, database: str, user: str = '', password: str = '', port: str = ''): pass

    def execute_query(self, sql): 
        
        # 'INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...)' 
        
        # return self.curr.execute(sql)
        print('Query has been executed', sql)

class Service(IDatabaseSQL): 
    # import mysql.connector

    def _database_connection(self, host: str, database: str, user: str = '', password: str = '', port: str = ''):
        # self._connection = mysql.connector.connect( 
        #     host=host, 
        #     user=user, 
        #     password=password, 
        #     database=database 
        # )
        # return self.connection != ''
        print('Succesfull connection to the service (CLOUD)', host, database, user, password, port)

class ClientInterface(ABC):
    @abstractmethod
    def _execute(self, sql): pass

    def get_data(self, sql): pass

class ServiceAdapter(ClientInterface):
    service: Service = ''

    def __init__(self, service: Service):
        self.service = service

    def _execute(self, sql):
        # return self.service.execute_query(sql)
        print('Adapter: Query has been executed', sql)
        return True

    def get_data(self, sql):
        res = self._execute(sql) 
        # return e.g., JSON fromat from query sorted by column
        return [('value1.1', 'value2.1'), ('value1.2', 'value2.2'), ('value1.3', 'value2.3')]

class Client(IDatabaseSQL): 
    # import mariadb

    clientInterface: ClientInterface = ''

    def __init__(self, clientInterface: ClientInterface, host: str, database: str, user: str = '', password: str = '', port: str = ''):
        self.clientInterface = clientInterface
        super().__init__(host, database, user, password, port)

    def _database_connection(self, user: str, password: str, port:str, host: str, database: str):
        # self._connection = mariadb.connect(
        #     user=user,
        #     password=password, 
        #     host=host, 
        #     port=port, 
        #     database=database
        # )
        # return self.connection != ''
        print('Succesfull connection to the client (LOCAL)', host, database, user, password, port)

    def write(self, cloud_query, table, column=''):
        # local_table is in local_database

        resp = self.clientInterface.get_data(cloud_query) # Response is readable

        for i in resp:
            self.execute_query(f'INSERT INTO {table} {column} VALUES {i};')

        return True

if __name__ == '__main__':
    cloud_database: Service = Service('cloud_database.com', 'mysql_cloud_database')
    # cloud_database.execute_query('SELECT * FROM user')

    adapter_cloud_database: ServiceAdapter = ServiceAdapter(cloud_database)
    # adapter_cloud_database.execute('SELECT * FROM user')
    
    local_database: Client = Client(adapter_cloud_database, 'localhost.com', 'mariadb_local_database')
    local_database.write('SELECT * FROM user', 'user', ('value1', 'value2'))