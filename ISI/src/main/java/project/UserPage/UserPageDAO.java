package project.UserPage;

import project.DB;
import project.Log.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPageDAO {

    public Log log = Log.getInstance();
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERPAGE = "DELETE from users where id = ?";
    private static final String INSERT_USERPAGE = "INSERT INTO users (id, username, password, role) VALUES (?, ?, ?, ?)";
    private static final String INSERT_LOG = "INSERT INTO logs (username, table_name, table_row, command) VALUES (? ,? ,? ,?)";


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

    public void insertUserPage(UserPage userpage) throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERPAGE)) {
            preparedStatement.setInt(1,userpage.getId());
            preparedStatement.setString(2, userpage.getUsername());
            preparedStatement.setString(3, userpage.getPass());
            preparedStatement.setString(4, userpage.getRole());

            if(userpage.getPass().equals(userpage.getConfpass()))
            {
                preparedStatement.executeUpdate();
            }
            else
            {
                System.out.println("Wrong pass!");
            }


        } catch (SQLException e) {
            printSQLException(e);
        }

        log.setCommand("insert");
        log.setTable_row(String.valueOf(userpage.getId()));
    }

    public List<UserPage> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<UserPage> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String date = rs.getString("created_at");
                String role = rs.getString("role");
                users.add(new UserPage(id,username,null,null,date, role));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public void deleteUserPage(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERPAGE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }

        log.setCommand("delete");
        log.setTable_row(String.valueOf(id));
    }

    public void insertLog() throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG)) {
            log.setTable_name("users");
            preparedStatement.setString(1, log.getUsername());
            preparedStatement.setString(2, log.getTable_name());
            preparedStatement.setString(3, log.getTable_row());
            preparedStatement.setString(4, log.getCommand());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
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