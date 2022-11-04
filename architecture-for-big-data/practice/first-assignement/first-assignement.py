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
import json

'''
@dev AbstractDatabase is an abstract class that contains the information to connect with any database.
     In the inherit class, you will have to implement `execute_query(...)` method.
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
@dev IDatabaseSQL are an abstract class that define behavior of the SQL database.
     Since IDatabaseSQL inherits from AbstractDatabase it must implements `execute_query(...)` method.
     In the inherit class, you will have to implement `_database_connection()` method.

     Assuming that in python any database sql library implement `.connector.connect(...)` and `.cursor()` methods: 
        1. we use `_connection` field to store the connection to the specific database;
        2. we use `_cursor` field to store the cursor to the specific database.
        Note that `_cursor` contanis the `.execute()` method that is used to execute the query.
'''
class IDatabaseSQL(AbstractDatabase):
    _connection: str = ''
    _cursor: str = ''

    def __init__(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = self._database_connection(user, password, host, database, port)
        self._cursor = self._connection.cursor()
        super().__init__(user, password, host, database, port)

    @abstractmethod
    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''): pass
    
    def execute_query(self, sql): 
        self._cursor.execute(sql)
        res = [i for i in self._cursor]

        print(f'\nQuery has been executed: {sql}')
        for i in res:
            print(f'{i}')
       
        return res

'''
@dev ExternalDatabase is a concrete class that allow us to establish a connection.
     Since ExternalDatabase inherits from IDatabaseSQL it must implements `_database_connection(...)`.
'''
class ExternalDatabase(IDatabaseSQL): 
    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = mysql.connector.connect(user=user, password=password, host=host, database=database)
        print(f'Connection successfull to the: {host} {database} {user} {password} {port}')
        return self._connection

'''
@dev IHistoricalDatabse is an interface that contains the delcarations of methods that they must be implemented by each adapters.
     We expect the `read_from(...)` mothod returns a fitted content for the HistoricalDatabase. 
'''
class IHistoricalDatabse(ABC):
    @abstractmethod
    def _execute(self, sql): pass

    @abstractmethod
    def read_from(self, sql): pass

'''
@dev ExternalDatabaseAdapter is a concrete class that make the data from ExternalDatadase readable and writable for HistoricalDatabase. 
     Since ExternalDatabaseAdapter inherits from IHistoricalDatabse it must implements `_read_from(...)` and `_execute(...) methods`.
     Since ExternalDatabaseAdapter has a ExternalDatabase object and it can execute query or more specifically read the data from it.
     (e.g., If the our HistoricalDatabase would like to has a list of tuple we should implement this method in the way it returns it)
'''
class ExternalDatabaseAdapter(IHistoricalDatabse):
    external_databse: ExternalDatabase = ''

    def __init__(self, external_databse: ExternalDatabase):
        self.external_databse = external_databse
        print('ExternalDatabaseAdapter has been created')

    def _execute(self, sql):
        query_res = self.external_databse.execute_query(sql)
        return query_res
    
    def read_from(self, sql):
        query_res = self._execute(sql) 
        return query_res

'''
@dev HistoricalDatabse is a concrete class that contains the methods to write into the historical database,
     Since HistoricalDatabse inherits from IDatabaseSQL it must implements `_database_connection(...)`.
     Since HistoricalDatabse has a IHistoricalDatabase object it can get data from it.
     
     The `.write_to(...)` mothod use the `.read_from(...)` of the adapter to get a list of tuple 
     and then it will insert it to historical database. 
     After this, it will commit the changes.
'''
class HistoricalDatabse(IDatabaseSQL): 
    adapter_for_historical_database: IHistoricalDatabse = ''
    last_sync: int = 0 

    def __init__(self, adapter_for_historical_database: IHistoricalDatabse, user: str, password: str, host: str, database: str, port: str = ''):
        self.adapter_for_historical_database = adapter_for_historical_database
        try:
            #self.last_sync = self.adapter_for_historical_database.read_from('SELECT id FROM user ORDER BY id DESC LIMIT 1')[0][0]
            with open("sync.json", "r") as json_object:
                sync_data = json.load(json_object)
                self.last_sync = sync_data['sync_last']
        except:
            with open('sync.json', 'w'): pass
            self.last_sync = 0
        super().__init__(user, password, host, database, port)

    def _database_connection(self, user: str, password: str, host: str, database: str, port: str = ''):
        self._connection = mysql.connector.connect(user=user, password=password, host=host, database=database)
        print(f'Connection successfull to the: {host} {database} {user} {password} {port}')
        return self._connection

    def write_to(self, read_query, table, column=''):
        response = self.adapter_for_historical_database.read_from(read_query)
        new_column = f"({', '.join(column)})"
        for i in response:
            self.execute_query(f'INSERT INTO {table} {new_column} VALUES {(i)};') 
        self._connection.commit()

        dictionary = {'sync_last': \
            self.adapter_for_historical_database.read_from('SELECT id FROM user ORDER BY id DESC LIMIT 1')[0][0]}
        json_object = json.dumps(dictionary)
        with open("sync.json", "w") as sync_file:
            sync_file.write(json_object)    

        return True

if __name__ == '__main__':
    external_database: ExternalDatabase = ExternalDatabase(user='root', password='welcome123', host='127.0.0.1', database='test_database')
    adapter_external_database: ExternalDatabaseAdapter = ExternalDatabaseAdapter(external_database)
    historical_database: HistoricalDatabse = HistoricalDatabse(adapter_external_database, user='root', password='welcome123', host='127.0.0.1', database='test_database')
    historical_database.write_to(f'SELECT name, surname FROM user where id > {historical_database.last_sync}', 'user2', ('name', 'surname'))