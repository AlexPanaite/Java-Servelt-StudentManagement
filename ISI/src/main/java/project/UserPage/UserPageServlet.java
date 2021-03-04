package project.UserPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/userpage","/userpage/insert","/userpage/delete"})
public class UserPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserPageDAO userpageDAO;

    public void init() {
        userpageDAO = new UserPageDAO();
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
                case "/userpage/delete":
                    deleteUserPage(request, response);
                    insertLog();
                    break;
                case "/userpage/insert":
                    insertUserPage(request, response);
                    insertLog();
                    break;
                default:
                    listUserPage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertUserPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String confpass = request.getParameter("confirm_password");
        String role = request.getParameter("role");
        UserPage newUserPage = new UserPage(id,username,pass,confpass,null,role);
        userpageDAO.insertUserPage(newUserPage);
        response.sendRedirect("/userpage");
    }

    private void listUserPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<UserPage> listUserPage = userpageDAO.selectAllUsers();
        request.setAttribute("listUserPage", listUserPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUserPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userpageDAO.deleteUserPage(id);
        response.sendRedirect("/userpage");

    }

    private void insertLog() throws SQLException {
        userpageDAO.insertLog();

    }

}