package project.Matricol;

import project.DB;
import project.Log.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatricolDAO {

    public Log log = Log.getInstance();
    private static final String UPDATE_MATRICOL = "update inmatriculare set an= ?, grupa =?, medie=?, bursa=?, formainv = ?, codst=?, cods=? where codm = ?";
    private static final String UPDATE_STUDENT = "update student set nume= ?, prenume =?, cetatenie=?, datan=? where codst = ?";
    private static final String DELETE_MATRICOL = "delete from inmatriculare where codm = ?";
    private static final String DELETE_STUDENT = "delete from student where codst = ?";
    private static final String SELECT_ALL_MATRICOL = "select * from inmatriculare";
    private static final String SELECT_FROM_STUDENT = "select * from student";
    private static final String INSERT_MATRICOL = "INSERT INTO inmatriculare (codm, an, grupa, medie, bursa, formainv, codst, cods) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_STUDENT = "INSERT INTO student (codst, nume, prenume, cetatenie, datan) VALUES (?, ?, ?, ?, ?)";
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

    public void insertMatricol(Matricol matricol) throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATRICOL);
             PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setInt(1,matricol.getCodm());
            preparedStatement.setInt(2,matricol.getAn());
            preparedStatement.setString(3, matricol.getGrupa());
            preparedStatement.setString(4, matricol.getMedie());
            preparedStatement.setInt(5,matricol.getBursa());
            preparedStatement.setString(6, matricol.getFormainv());
            preparedStatement.setInt(7,matricol.getCodst());
            preparedStatement.setInt(8,matricol.getCods());
            preparedStatement.executeUpdate();

            preparedStatement2.setInt(1,matricol.getCodst());
            preparedStatement2.setString(2, matricol.getNume());
            preparedStatement2.setString(3, matricol.getPrenume());
            preparedStatement2.setString(4, matricol.getCetatenie());
            preparedStatement2.setString(5, matricol.getDatan());
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        log.setCommand("insert");
        log.setTable_row(String.valueOf(matricol.getCodm()));
    }

    public List<Matricol> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Matricol> matricols = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_FROM_STUDENT);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MATRICOL)) {
            // Step 3: Execute the query or update query

            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rs2 = preparedStatement2.executeQuery();
            while (rs.next()){
                int codm = rs.getInt("codm");
                int an = rs.getInt("an");
                String grupa = rs.getString("grupa");
                String medie = rs.getString("medie");
                int bursa = rs.getInt("bursa");
                String formainv = rs.getString("formainv");
                int codst = rs.getInt("codst");
                int cods = rs.getInt("cods");

                if(rs2.next()) {
                  String nume = rs2.getString("nume");
                  String prenume = rs2.getString("prenume");
                  String cetatenie = rs2.getString("cetatenie");
                  String datan = rs2.getString("datan");

                  matricols.add(new Matricol(codm, an, grupa, medie, bursa,formainv, codst, cods, nume, prenume, cetatenie,datan));
                }

            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return matricols;
    }

    public void deleteMatricol(int codm,int codst) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MATRICOL);
             PreparedStatement statement2 = connection.prepareStatement(DELETE_STUDENT)) {
            statement.setInt(1, codm);
            statement2.setInt(1, codst);
            statement.executeUpdate();
            statement2.executeUpdate();

            log.setCommand("delete");
            log.setTable_row(String.valueOf(codm));
        }
    }



    public void updateMatricol(Matricol matricol) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MATRICOL);
             PreparedStatement statement2 = connection.prepareStatement(UPDATE_STUDENT)){
            statement.setInt(1, matricol.getAn());
            statement.setString(2, matricol.getGrupa());
            statement.setString(3, matricol.getMedie());
            statement.setInt(4, matricol.getBursa());
            statement.setString(5, matricol.getFormainv());
            statement.setInt(6, matricol.getCodst());
            statement.setInt(7, matricol.getCods());
            statement.setInt(8, matricol.getCodm());
            statement.executeUpdate();

            statement2.setString(1, matricol.getNume());
            statement2.setString(2, matricol.getPrenume());
            statement2.setString(3, matricol.getCetatenie());
            statement2.setString(4, matricol.getDatan());
            statement2.setInt(5, matricol.getCodst());
            statement2.executeUpdate();

            log.setCommand("update");
            log.setTable_row(String.valueOf(matricol.getCodm()));
        }
    }

    public void insertLog() throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG)) {
            log.setTable_name("inmatriculare");
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