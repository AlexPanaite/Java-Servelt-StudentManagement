package project.Log;

import project.DB;
import project.Matricol.Matricol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    private static final String SELECT_LOG = "SELECT * FROM LOGS";

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

    public List<Log> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Log> logs = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOG)) {
            // Step 3: Execute the query or update query

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String table_name = rs.getString("table_name");
                String table_row = rs.getString("table_row");
                String command = rs.getString("command");
                String data = rs.getString("created_at");
                logs.add(new Log(id, username, table_name, table_row, command,data));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return logs;
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