import java.sql.SQLException;

// public class FacebookDBTester{
//    public static void main(String[] args) {
//       FacebookDB fbDb = new FacebookDB();
//       String sql = "INSERT INTO user VALUES ('bart.simpsons@lyit.ie', 'LetMeIn','Bart','Simpson') " + "ON DUPLICATE KEY UPDATE password='LetMeIn', firstname='Bart', lastname='Simpson'";
//       try{
//          fbDb.insertIntoDatabase(sql);
//       }
//       catch (SQLException e){
// 			System.out.println("error\n" + e.getMessage());
// 		}
//    }
// }

public class FacebookDBTester{
   public static void main(String[] args) {
      FacebookDB database = new FacebookDB();
      String password=database.getUserPasswordFromDatabase("bart.simpson@lyit.ie");
      // Should display “LetMeIn”
      System.out.println("Password is " + password);
   }
}
