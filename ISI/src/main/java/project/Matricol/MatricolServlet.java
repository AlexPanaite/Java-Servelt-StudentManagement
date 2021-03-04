package project.Matricol;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/matricol","/matricol/insert","/matricol/delete","/matricol/update"})
public class MatricolServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatricolDAO matricolDAO;

    public void init() {
        matricolDAO = new MatricolDAO();
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
                case "/matricol/insert":
                    insertMatricol(request, response);
                    insertLog();
                    break;
                case "/matricol/delete":
                    deleteMatricol(request, response);
                    insertLog();
                    break;
                case "/matricol/update":
                    updateMatricol(request, response);
                    insertLog();
                    break;
                default:
                    listMatricol(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMatricol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Matricol> listMatricols = matricolDAO.selectAllUsers();
        request.setAttribute("listMatricol", listMatricols);
        RequestDispatcher dispatcher = request.getRequestDispatcher("inmatriculare.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMatricol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int an = Integer.parseInt(request.getParameter("an"));
        int codm = Integer.parseInt(request.getParameter("codm"));
        int cods = Integer.parseInt(request.getParameter("cods"));
        int codst = Integer.parseInt(request.getParameter("codst"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String grupa = request.getParameter("grupa");
        String datan = request.getParameter("datan");
        String medie = request.getParameter("medie");
        String formainv = request.getParameter("formainv");
        String cetatenie = request.getParameter("cetatenie");
        int bursa = Integer.parseInt(request.getParameter("bursa"));
        Matricol newMatricol = new Matricol(codm, an, grupa, medie, bursa,formainv, codst, cods, nume, prenume, cetatenie, datan);
        matricolDAO.insertMatricol(newMatricol);
        response.sendRedirect("/matricol");
    }

    private void updateMatricol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int an = Integer.parseInt(request.getParameter("an"));
        int codm = Integer.parseInt(request.getParameter("codm"));
        int cods = Integer.parseInt(request.getParameter("cods"));
        int codst = Integer.parseInt(request.getParameter("codst"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String grupa = request.getParameter("grupa");
        String datan = request.getParameter("datan");
        String medie = request.getParameter("medie");
        String formainv = request.getParameter("formainv");
        String cetatenie = request.getParameter("cetatenie");
        int bursa = Integer.parseInt(request.getParameter("bursa"));

        Matricol matricol = new Matricol(codm,an,grupa,medie,bursa,formainv,codst,cods,nume,prenume,cetatenie,datan);
        matricolDAO.updateMatricol(matricol);
        response.sendRedirect("/matricol");
    }

    private void deleteMatricol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int codm = Integer.parseInt(request.getParameter("codm"));
        int codst = Integer.parseInt(request.getParameter("codst"));
        matricolDAO.deleteMatricol(codm,codst);
        response.sendRedirect("/matricol");

    }

    private void insertLog() throws SQLException {
           matricolDAO.insertLog();

    }

}