package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;


@Stateful
public class ProductRepository {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static List<Product> getAllProductAmount() {
        Connection conn = null;
        Statement stmt = null;
        List<Product> prodAmount = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("repository is ");

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT id,name,amout FROM Product";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int amount = rs.getInt("amout");
                Product product1=new Product(id,name,amount);
                prodAmount.add(product1);
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
        return prodAmount;
    }


    public static String addProduct(Product product) throws ClassNotFoundException, SQLException {
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
        String sql = "INSERT INTO Product (ID,NAME,DESCRIPTION,PRICE,AMOUT) VALUES (?,?,?,?,?)";
        PreparedStatement PreparedStatement = conn.prepareStatement(sql);
        PreparedStatement.setInt(1, product.getId());
        PreparedStatement.setString(2, product.getName());
        PreparedStatement.setString(3, product.getDescription());
        PreparedStatement.setDouble(4, product.getPrice());
        PreparedStatement.setInt(5, product.getAmount());

        PreparedStatement.executeUpdate();
        PreparedStatement.close();

        phrase = "Inserted records into the table...";

        conn.close();

        return phrase;
    }

    public static List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        List<Product> allProducts = new ArrayList<>();
        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);
        System.out.println("repository is ");

        // STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 3: Execute a query
        System.out.println("Connected database successfully...");
        stmt = conn.createStatement();
        String sql = "SELECT * FROM Product";
        ResultSet rs = stmt.executeQuery(sql);

        // STEP 4: Extract data from result set
        while (rs.next()) {
            // Retrieve by column name
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Double price=rs.getDouble("price");
            int amount  = rs.getInt("amout");
            Product product1= new Product(id,name,description,price,amount);
            allProducts.add(product1);
        }
        // STEP 5: Clean-up environment
        rs.close();

        return allProducts;
    }

    public static String updateProductById(Product product) throws SQLException, ClassNotFoundException {
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
        String sql = "UPDATE Product set name=?, description=?, price=?, amout=? where id =?";
        PreparedStatement PreparedStatement1 = conn.prepareStatement(sql);
        PreparedStatement1.setString(1, product.getName());
        PreparedStatement1.setString(2, product.getDescription());
        PreparedStatement1.setDouble(3, product.getPrice());
        PreparedStatement1.setInt(4, product.getAmount());
        PreparedStatement1.setInt(5, product.getId());
        PreparedStatement1.executeUpdate();
        PreparedStatement1.close();

        conn.close();
        return "Product info is updated";
    }

    public static String deleteProductById(Product product) throws SQLException, ClassNotFoundException {
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
        String sql = "delete from Product where id =?";
        PreparedStatement PreparedStatement1 = conn.prepareStatement(sql);
        PreparedStatement1.setInt(1, product.getId());
        PreparedStatement1.executeUpdate();
        PreparedStatement1.close();

        conn.close();
        return "Product is deleted";
    }

//    public static newUser getUserById(newUser user12) throws ClassNotFoundException, SQLException {
//        Connection conn = null;
//        Statement stmt = null;
//        newUser user1=new newUser();
//        // STEP 1: Register JDBC driver
//        Class.forName(JDBC_DRIVER);
//        System.out.println("repository is ");
//
//        // STEP 2: Open a connection
//        System.out.println("Connecting to database...");
//        conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//        // STEP 3: Execute a query
//        System.out.println("Connected database successfully...");
//        stmt = conn.createStatement();
//        String sql = "SELECT * FROM Registration where ID=?";
//
//        PreparedStatement PreparedStatement1 = conn.prepareStatement(sql);
//        PreparedStatement1.setInt(1, user12.getId());
//        ResultSet rs = PreparedStatement1.executeQuery();
//
//        // STEP 4: Extract data from result set
//        while (rs.next()) {
//            // Retrieve by column name
//
//            user1.setId(rs.getInt("ID"));
//            user1.setAge(rs.getInt("AGE"));
//            user1.setFirst(rs.getString("FIRST"));
//            user1.setLast(rs.getString("LAST"));
//            // userbyid.add(user1);
//        }
//        // STEP 5: Clean-up environment
//        rs.close();
//        PreparedStatement1.close();
//
//
//        return user1;
//
//
//    }
}
