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
        self.user = user
        self.password = password
        self.port = port
        self.host = host
        self.database = database

    @abstractmethod
    def execute_query(self, sql, parameters={}): pass

class IDatabaseSQL(AbstractDatabase):
    connection: str = ''
    curr: str = ''

    def __init__(self, host: str, database: str, user: str = '', password: str = '', port: str = ''):
        self.connection = self._database_connection(user, password, port, host, database)
        # self.curr = self.connection.cursor()
        super().__init__(host, database, user, password, port)

    @abstractmethod
    def _database_connection(self, user: str, password: str, port: str, host: str, database: str): pass

    def execute_query(self, sql, parameters={}):
        # parameters issue
        
        #return self.curr.execute(sql)
        print('Query has been executed', sql)

class Service(IDatabaseSQL): # IMPORANT: e.g., for MySQL
    # import mysql.connector

    def _database_connection(self, host: str, database: str, user: str, password: str, port: str):
        # connection = mysql.connector.connect( 
        #     host=host, 
        #     user=user, 
        #     password=password, 
        #     database=database 
        # )
        # return connection
        print('Succesfull connection', host, database, user, password, port)

# class ClientInterface(ABC):
#     @abstractmethod
#     def execute(self, sql): pass

#     @abstractmethod
#     def make_readable(): pass

# class ServiceAdapter(ClientInterface):
#     service: Service = Service()

#     def __init__(self, service: Service):
#         self.service = service

#     @override
#     def execute(sql):
#         return service.execute_query(sql)

#     @override
#     def make_readable(sql):
#         pass # IMPLEMENTED

# class Client(IDatabaseSQL): # IMPORANT: e.g., for MariaDB
#     import mariadb

#     clientInterface: ClientInterface = ClientInterface()

#     def __init__(self, clientInterface: ClientInterface):
#         self.clientInterface = clientInterface
#         super().__init__()

#     @override
#     def _database_connection(self, ser: str, password: str, port:str, host: str, database: str):
#         return connection = mariadb.connect(
#             user=user, #"db_user"
#             password=password, #"db_user_passwd"
#             host=host, #"192.0.2.1"
#             port=port, #3306
#             database=database #"employees"
#         )

#     def writeUser(cloud_database_sql, local_database_query):
#         users = make_readable(clientInterface.execute_query(cloud_database_sql))

#         local_write = self.connection.execute_query(local_database_query, users)

if __name__ == '__main__':
    cloud_database: Service = Service('cloud_database.com', 'mysql_cloud_database')
    cloud_database.execute_query('SELECT * FROM user')

    # adapter_cloud_database = ServiceAdapter(cloud_database)

    # local_database: Client = Client(adapter_cloud_database, 'user', 'password', 'port', 'host', 'database')

    # local_database.writeUser({'SELECT * FROM user', 'INSERT user'})
