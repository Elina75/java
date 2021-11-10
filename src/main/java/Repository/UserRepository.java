package Repository;
import javax.ejb.Stateful;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Repository.newUser;

@Stateful
public class UserRepository {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static List<Integer> getAgeUser() {
        Connection conn = null;
        Statement stmt = null;
        List<Integer> agelist = new ArrayList<>();
        try {
            //List<Integer> agelist = new ArrayList<>();
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("repository is ");

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT AGE FROM Registration";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int age = rs.getInt("age");
                agelist.add(age);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        }// end try
        System.out.println("Goodbye!");
        return agelist;
    }


    public static String addUser(newUser newUser) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        String phrase="";
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("repository is ");

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            String sql = "INSERT INTO Registration (ID,FIRST,LAST,AGE) VALUES (?,?,?,?)";
            PreparedStatement PreparedStatement = conn.prepareStatement(sql);
            PreparedStatement.setInt(1, newUser.getId()); // 1 - порядковый номер параметра ("?") внутри запроса
            PreparedStatement.setString(2, newUser.getFirst());
            PreparedStatement.setString(3, newUser.getLast());
            PreparedStatement.setInt(4, newUser.getAge());
            PreparedStatement.executeUpdate();
            PreparedStatement.close();

            phrase = "Inserted records into the table...";

            conn.close();

        System.out.println("Goodbye!");
        return phrase;
    }

    public static List<newUser> getAllUser() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        List<newUser> allUsers = new ArrayList<>();
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("repository is ");

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Registration";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");
                newUser user1= new newUser(id,first,last,age);
                allUsers.add(user1);
            }
            // STEP 5: Clean-up environment
            rs.close();

        return allUsers;
    }

    public static String updateUserNameById(newUser UpdUser) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);
        System.out.println("repository is ");

        // STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 3: Execute a query
        System.out.println("Connected database successfully...");
        String sql = "UPDATE Registration set first=?, last=?, age=? where id =?";
        PreparedStatement PreparedStatement1 = conn.prepareStatement(sql);
        PreparedStatement1.setString(1, UpdUser.getFirst());
        PreparedStatement1.setString(2, UpdUser.getLast());
        PreparedStatement1.setInt(3, UpdUser.getAge());
        PreparedStatement1.setInt(4, UpdUser.getId());
        PreparedStatement1.executeUpdate();
        PreparedStatement1.close();

        conn.close();
        return "User name is updated";
    }

    public static String deleteUserNameById(newUser delUser) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);
        System.out.println("repository is ");

        // STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 3: Execute a query
        System.out.println("Connected database successfully...");
        String sql = "delete from Registration where id =?";
        PreparedStatement PreparedStatement1 = conn.prepareStatement(sql);
        PreparedStatement1.setInt(1, delUser.getId());
        PreparedStatement1.executeUpdate();
        PreparedStatement1.close();

        conn.close();
        return "User is deleted";
    }

    public static newUser getUserById(newUser user12) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stmt = null;
        newUser user1=new newUser();
        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);
        System.out.println("repository is ");

        // STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 3: Execute a query
        System.out.println("Connected database successfully...");
        stmt = conn.createStatement();
        String sql = "SELECT * FROM Registration where ID=?";

        PreparedStatement PreparedStatement1 = conn.prepareStatement(sql);
        PreparedStatement1.setInt(1, user12.getId());
        ResultSet rs = PreparedStatement1.executeQuery();

        // STEP 4: Extract data from result set
        while (rs.next()) {
            // Retrieve by column name

            user1.setId(rs.getInt("ID"));
            user1.setAge(rs.getInt("AGE"));
            user1.setFirst(rs.getString("FIRST"));
            user1.setLast(rs.getString("LAST"));
           // userbyid.add(user1);
        }
        // STEP 5: Clean-up environment
        rs.close();
        PreparedStatement1.close();


        return user1;


    }
}