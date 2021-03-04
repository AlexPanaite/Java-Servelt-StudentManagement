package project.Log;

import project.Matricol.Matricol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/log")
public class LogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LogDAO logDAO;

    public void init() {
        logDAO = new LogDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {

                default:
                    listLogs(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listLogs(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Log> listLogs = logDAO.selectAllUsers();
        request.setAttribute("listLogs", listLogs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("logs.jsp");
        dispatcher.forward(request, response);
    }

}