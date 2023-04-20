/**
* Class: B.Sc. in Computing
* Description: A DBOperations interface - All classes implementing this interface must override its abstract methods
* Date: 11/05/2022
* @author Joseph Foy
* @version 1.0
*/

import java.sql.SQLException;

public interface DBOperations{ 
   public abstract void createConnection(String dbUrl);
   public abstract void createDatabase();
   public abstract void closeConnection();
   public abstract void insertIntoDatabase(String sqlString)throws SQLException;        
}