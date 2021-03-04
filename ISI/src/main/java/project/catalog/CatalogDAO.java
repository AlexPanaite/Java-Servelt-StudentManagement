package project.catalog;

import project.DB;
import project.Log.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogDAO {

    public Log log = Log.getInstance();
    private static final String UPDATE_CATALOG = "update catalog set codst= ?, sesiune =?, ip=?, bd=?, paw = ?, plf=?, poo=?, am=?, lft=? where id = ?";
    private static final String DELETE_CATALOG = "delete from catalog where id = ?";
    private static final String SELECT_ALL_CATALOG = "select * from catalog";
    private static final String SELECT_FROM_STUDENT = "select * from student where codst=?";
    private static final String INSERT_CATALOG = "INSERT INTO catalog (id,codst, sesiune, ip, bd, paw, plf, poo, am, lft) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

    public void insertCatalog(Catalog catalog) throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATALOG)) {
            preparedStatement.setInt(1,catalog.getId());
            preparedStatement.setInt(2,catalog.getCodst());
            preparedStatement.setString(3, catalog.getSesiune());

            preparedStatement.setString(4,catalog.getIp());
            preparedStatement.setString(5,catalog.getBd());
            preparedStatement.setString(6,catalog.getPaw());
            preparedStatement.setString(7,catalog.getPlf());
            preparedStatement.setString(8,catalog.getPoo());
            preparedStatement.setString(9,catalog.getAm());
            preparedStatement.setString(10,catalog.getLft());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        log.setCommand("insert");
        log.setTable_row(String.valueOf(catalog.getId()));
    }

    public List<Catalog> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Catalog> catalog = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_FROM_STUDENT);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATALOG)) {
            // Step 3: Execute the query or update query

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int codst = rs.getInt("codst");
                String sesiune = rs.getString("sesiune");
                String ip = rs.getString("ip");
                String bd = rs.getString("bd");
                String paw = rs.getString("paw");
                String plf = rs.getString("plf");
                String poo = rs.getString("poo");
                String am = rs.getString("am");
                String lft = rs.getString("lft");

                preparedStatement2.setInt(1, codst);
                ResultSet rs2 = preparedStatement2.executeQuery();

                if(rs2.next()) {
                    String nume = rs2.getString("nume");
                    String prenume = rs2.getString("prenume");

                    catalog.add(new Catalog(id, codst, nume, prenume, sesiune,ip, bd, paw, plf, poo, am, lft));
                }

            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return catalog;
    }

    public void deleteCatalog(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CATALOG)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }

        log.setCommand("delete");
        log.setTable_row(String.valueOf(id));
    }

    public void updateCatalog(Catalog catalog) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATALOG)){
            statement.setInt(1, catalog.getCodst());
            statement.setString(2, catalog.getSesiune());
            statement.setString(3, catalog.getIp());
            statement.setString(4, catalog.getBd());
            statement.setString(5, catalog.getPaw());
            statement.setString(6, catalog.getPlf());
            statement.setString(7, catalog.getPoo());
            statement.setString(8, catalog.getAm());
            statement.setString(9, catalog.getLft());
            statement.setInt(10, catalog.getId());
            statement.executeUpdate();

            log.setCommand("update");
            log.setTable_row(String.valueOf(catalog.getId()));

        }
    }

    public void insertLog() throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG)) {
            log.setTable_name("catalog");
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