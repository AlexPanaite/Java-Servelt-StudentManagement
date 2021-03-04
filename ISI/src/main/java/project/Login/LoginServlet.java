package project.Login;
import project.Log.Log;
import project.Log.LogDAO;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/login","/login/change"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;
    private Log log;

    public void init() {

        loginDAO = new LoginDAO();
        log = Log.getInstance();
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
                case "/login/change":
                    changePass(request, response);
                    break;
                default:
                    checklogin(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void checklogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Login login = loginDAO.checkLogin(username,password);
        String destPage = "login.jsp";
        if (login != null) {
            destPage = "index.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);

        log.setUsername(username);

    }

    private void changePass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {

        HttpSession session = request.getSession();
        Login ulogin = (Login)session.getAttribute("login");

        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");

        loginDAO.changePass(ulogin.getUsername(),new_password,confirm_password);
        session.invalidate();
        response.sendRedirect("/login");

    }
}