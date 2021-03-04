package project.Login;

import project.DB;

import java.sql.*;

public class LoginDAO {

    private static final String SELECT_LOGIN = "SELECT * FROM users WHERE username = ? and password = ?";
    private static final String UPDATE_LOGIN = "UPDATE users set password = ? WHERE username = ?";

    protected Connection getConnection() {
        DB db = new DB();
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(db.URL,db.User, db.Password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public Login checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN)) {

            // Step 3: Execute the query or update query
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            Login login = null;

            if (rs.next()) {
                login = new Login();
                login.setUsername(rs.getString("username"));
                login.setRole(rs.getString("role"));
                login.setPass(rs.getString("password"));
            }

            return login;
        }
    }

    public Login changePass(String username, String password,String confpass) throws SQLException,
            ClassNotFoundException {

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOGIN)) {

            // Step 3: Execute the query or update query
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);


            if(password.equals(confpass))
            {
                preparedStatement.executeUpdate();
            }
            else
            {
                System.out.println("Wrong pass!");
            }

            Login login = new Login();

            return login;

        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}