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

from abc import ABC, abstractmethod, override

class AbstractDatabase(ABC):
    user: str = ''
    password: str = ''
    port: str = ''
    host: str = ''
    database: str  = ''

    def __init__(self, user: str = '', password: str = '', port: str = '', host: str, database: str):
        self.user = user
        self.password = password
        self.port = port
        self.host = host
        self.database = database

    @abstractmethod
    def execute_query(self, sql, parameters={}): pass

class IDatabaseSQL(AbstractDatabase):
        connection = ''
        curr = ''

        def __init__():
            self.connection = _database_connection(user, password, port, host, database)
            self.curr = connection.cursor()
            super().__init__()

        @abstractmethod
        def _database_connection(self, ser: str, password: str, port: str, host: str, database: str): pass

        @override
        def execute_query(self, sql, parameters={}):

            string_sql = ''

            # make sql to string_sql

            return curr.execute(string_sql)

class Service(IDatabaseSQL): # IMPORANT: e.g., for MySQL
        import mysql.connector

        @override
        def _database_connection(self, ser: str, password: str, port:str, host: str, database: str):
            return connection = mysql.connector.connect( \
                host=host, #"localhost"
                user=user, #"yourusername"
                password=password, #"yourpassword"
                database=database #"mydatabase"
            )

class ClientInterface(ABC):
    @abstractmethod
    def execute(self, sql): pass

    @abstractmethod
    def make_readable(): pass

class ServiceAdapter(ClientInterface):
    service: Service = Service()

    def __init__(self, service: Service):
        self.service = service

    @override
    def execute(sql):
        return service.execute_query(sql)

    @override
    def make_readable(sql):
        pass # IMPLEMENTED

class Client(IDatabaseSQL): # IMPORANT: e.g., for MariaDB
        import mariadb

        clientInterface: ClientInterface = ClientInterface()

        def __init__(self, clientInterface: ClientInterface):
            self.clientInterface = clientInterface
            super().__init__()

        @override
        def _database_connection(self, ser: str, password: str, port:str, host: str, database: str):
            return connection = mariadb.connect(
                user=user, #"db_user"
                password=password, #"db_user_passwd"
                host=host, #"192.0.2.1"
                port=port, #3306
                database=database #"employees"
            )

        def writeUser(cloud_database_sql, local_database_query):
            users = make_readable(clientInterface.execute_query(cloud_database_sql))

            local_write = self.connection.execute_query(local_database_query, users)

if __name__ == '__main__':
    cloud_database: Service = Service('user', 'password', 'host', 'database')

    adapter_cloud_database = ServiceAdapter(cloud_database)

    local_database: Client = Client(adapter_cloud_database, 'user', 'password', 'port', 'host', 'database')

    local_database.writeUser({'SELECT * FROM user', 'INSERT user'})

          #ad1
        #/     \
#clouddb - ad2 - localdb
        #\     /
          #ad3
