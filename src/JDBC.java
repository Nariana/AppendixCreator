import java.sql.*;
import java.util.List;

public class JDBC {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/";

	//  Database credentials. NOTE: Must change to match MySQL DB credentials.
	static final String USER = "root";
	static final String PASS = "root";

	static Connection conn = null;
	static Statement stmt = null;

	public void createDB(List <String> index) {
		try{
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Create SQL statement to execute
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			// Drop DB. Create DB. Create table for words and count.
			String sql = "DROP DATABASE IF EXISTS MyIndex";
			stmt.executeUpdate(sql);
			sql = "CREATE DATABASE MyIndex";
			stmt.executeUpdate(sql);
			sql = "USE MyIndex";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE WordCount" +
					"(ID int NOT NULL AUTO_INCREMENT,"
					+ "word VARCHAR(1000) NOT NULL,"
					+ "PRIMARY KEY (ID))";
			stmt.executeUpdate(sql);

			System.out.println("Database created successfully. Adding values...");

			// Populate table with data
			for(int i = 0; i < index.size(); i++){
				// Final check to remove any non-words or filler words
				if(index.get(i).length() < 2)
					continue;
				
				sql = "INSERT INTO WordCount (word) VALUES ('" + index.get(i) + "')";
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

	public void selectIndex(){
		// display index with words as-is
		String sql = null;
		sql = "SELECT * FROM WordCount";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// print results to console
		try {
			System.out.println("----------");
			while(rs.next()){
				String word = rs.getString("word");

				System.out.print(word + "\n");
			}
			System.out.println("----------");

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void selectGroup(){
		// display index with words and count
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY ID ASC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// print results to console
		try {
			System.out.println("----------");
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}
			System.out.println("----------");

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectGroupAlphaAsc(){
		// display index with words in ascending alphabetical order and count
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY word ASC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// print results to console
		try {
			System.out.println("----------");
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}
			System.out.println("----------");

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectGroupNumAsc(){
		// display index with words in ascending count order and count
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY COUNT(word) ASC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// print results to console
		try {
			System.out.println("----------");
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}
			System.out.println("----------");

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectGroupAlphaDesc(){
		// display index with words in descending alphabetical order and count
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY word DESC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// print results to console
		try {
			System.out.println("----------");
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}
			System.out.println("----------");

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectGroupNumDesc(){
		// display index with words in descending count order and count
		String sql = null;
		sql = "SELECT word, COUNT(word) FROM WordCount GROUP BY word ORDER BY COUNT(word) DESC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// print results to console
		try {
			System.out.println("----------");
			while(rs.next()){
				String word = rs.getString("word");
				int count = rs.getInt("COUNT(word)");

				System.out.print(word + " " + count + "\n");
			}
			System.out.println("----------");

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void closeDB(){
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

		System.out.println("Closed DB");
	}
}