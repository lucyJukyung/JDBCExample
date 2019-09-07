import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class FirstJDBCExtract {

    static final String DB_URL = "jdbc:mysql://localhost:3306/emp?useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "mysql123";

    public static void getDBConn(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connecting to DB...." + conn.toString());

            //execute query
            stmt = conn.createStatement();
            String sql;
            sql = "select * from Students";
            //store result in a resultSet
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //display result
                System.out.println("ID: " + id + "\tName: " + first + "." + last);
            }
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            //close environment
            try{
                if(conn != null){
                    conn.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }
            catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //calling getDBConn method
        getDBConn();
    }
}
