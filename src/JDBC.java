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

	static Connection conn = null;
	static Statement stmt = null;

	public static void createDB(List <String> index) {
		//Connection conn = null;
		//Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql = "DROP DATABASE IF EXISTS MyIndex";
			stmt.executeUpdate(sql);
			sql = "CREATE DATABASE MyIndex";
			stmt.executeUpdate(sql);
			sql = "USE MyIndex";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE WordCount" +
					"(word VARCHAR(50) NOT NULL)";
			stmt.executeUpdate(sql);

			System.out.println("Database created successfully. Adding values...");

			for(int i = 0; i < index.size(); i++){
				sql = "INSERT INTO WordCount VALUES ('" + index.get(i) + "')";
				stmt.executeUpdate(sql);
			}

			System.out.println("Inserted values into index!");

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public static void selectIndex(){
		// display index
		String sql = null;
		sql = "SELECT * FROM WordCount";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				String word = rs.getString("word");

				System.out.print(word + "\n");
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void selectGroup(){
		// display index
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void selectGroupAsc(){
		// display index
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY word ASC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void selectGroupDesc(){
		// display index
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY word DESC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeDB(){
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

		System.out.println("Goodbye!");
	}//end main
}//end JDBCExample