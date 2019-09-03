import java.sql.*;

public class FirstJDBCExample {
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/emp?useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "fkfmzmfjqm84";

    // Method to create a new DB connection and display data from table
    public static void getDBConn(){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected to database..."+conn.toString());


            //STEP 4: Execute a query
            System.out.println ("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");

                //Display values
                System.out.print("ID: " + id);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }


    }//end try



    //main
    public static void main(String[] args) {
        getDBConn();
        System.out.println("First database connection!");
    }//end main

}//end FirstExample
