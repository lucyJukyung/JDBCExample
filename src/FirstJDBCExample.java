import java.sql.*;

public class FirstJDBCExample {

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/emp?useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "mysql123";

    // Method to create a new DB connection and display data from table
    public static void getDBConn(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt, cstmt;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database..." + conn.toString());

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //insert new rows
            /*pstmt = conn.prepareStatement("Insert into Employees(id, age, FIRST, LAST) values (?,?,?,?)");

            //place the values into the position
            pstmt.setInt(1, 110);
            pstmt.setInt(2, 18);
            pstmt.setString(3, "Brown");
            pstmt.setString(4, "S");

            pstmt.executeUpdate();// This executes the query
            Tested, working okay */

            /*
            //create new table
            String creatTable = "CREATE TABLE Students("
                    + "id INT NOT NULL,"
                    + "first VARCHAR(255),"
                    + "last VARCHAR(255),"
                    + "PRIMARY KEY (id)"
                    + ");";
            cstmt = conn.prepareStatement(creatTable);
            cstmt.executeUpdate();
            Tested, working okay */

            String updateValue = "INSERT INTO Students VALUES (10001, 'Adam', 'Smith')";
            pstmt = conn.prepareStatement(updateValue);
            pstmt.executeUpdate();

            updateValue = "INSERT INTO Students VALUES (10002, 'Jones', 'David')";
            pstmt = conn.prepareStatement(updateValue);
            pstmt.executeUpdate();

            updateValue = "INSERT INTO Students VALUES (10003, 'Jake', 'Blake')";
            pstmt = conn.prepareStatement(updateValue);
            pstmt.executeUpdate();

            //retrieve all employees
            String sql;
            sql = "SELECT * FROM Employees";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.println("ID: " + id + "\tAge: " + age + "\t" + first + "." + last);
            }
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        finally {

            //STEP 6: Clean-up environment
            try{
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }

            catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }

        }

    }//end try

    //main
    public static void main(String[] args) {
        getDBConn();
        System.out.println("First database connection!");
    }//end main

}//end FirstExample
