//STEP 1. Import required packages
import java.sql.*;
import java.util.List;

public class JDBC {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void createDB(List <String> a) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();
      
      String sql = "DROP DATABASE IF EXISTS APPENDIX";
      stmt.executeUpdate(sql);
      sql = "CREATE DATABASE Appendix";
      stmt.executeUpdate(sql);
      sql = "USE Appendix";
      stmt.executeUpdate(sql);
      sql = "CREATE TABLE WordCount" +
    		  "(word VARCHAR(50), " +
    		  "count INTEGER DEFAULT 1)";
      stmt.executeUpdate(sql);
      
      System.out.println("Database created successfully. Adding values...");
      
      for(int i = 0; i < a.size(); i++){
    	  sql = "INSERT INTO WordCount VALUES ('" + a.get(i) + "',1)";
    	  stmt.executeUpdate(sql);
      }
      
      sql = "SELECT * FROM WordCount";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
    	  String word = rs.getString("word");
    	  int count = rs.getInt("count");
    	  
    	  System.out.print(word + " " + count + "\n");
      }
      
      rs.close();
      
      System.out.println("Inserted values into appendix!");
    		  
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample