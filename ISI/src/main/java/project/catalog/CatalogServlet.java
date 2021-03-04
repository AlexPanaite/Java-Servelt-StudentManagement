package project.catalog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/catalog","/catalog/insert","/catalog/delete","/catalog/update"})
public class CatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CatalogDAO catalogDAO;

    public void init() {
        catalogDAO = new CatalogDAO();
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
                case "/catalog/insert":
                    insertCatalog(request, response);
                    insertLog();
                    break;
                case "/catalog/delete":
                    deleteMatricol(request, response);
                    insertLog();
                    break;
                case "/catalog/update":
                    updateCatalog(request, response);
                    insertLog();
                    break;
                default:
                    listCatalog(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Catalog> listCatalog = catalogDAO.selectAllUsers();
        request.setAttribute("listCatalog", listCatalog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalog.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int codst = Integer.parseInt(request.getParameter("codst"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String sesiune = request.getParameter("sesiune");

        String ip=null,bd=null,lft=null,poo=null,paw=null,plf=null,am=null;
        if(!request.getParameter("ip").isEmpty())
        {
            ip = request.getParameter("ip");
        }
        if(!request.getParameter("bd").isEmpty())
        {
            bd = request.getParameter("bd");
        }
        if(!request.getParameter("lft").isEmpty())
        {
            lft = request.getParameter("lft");
        }
        if(!request.getParameter("poo").isEmpty())
        {
            poo = request.getParameter("poo");
        }
        if(!request.getParameter("paw").isEmpty())
        {
            paw = request.getParameter("paw");
        }
        if(!request.getParameter("plf").isEmpty())
        {
            plf = request.getParameter("plf");
        }
        if(!request.getParameter("am").isEmpty())
        {
            am = request.getParameter("am");
        }

        Catalog newCatalog = new Catalog(id,codst,nume,prenume,sesiune,ip,bd,paw,plf,poo,am,lft);
        catalogDAO.insertCatalog(newCatalog);
        response.sendRedirect("/catalog");
    }

    private void updateCatalog(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int codst = Integer.parseInt(request.getParameter("codst"));
        String sesiune = request.getParameter("sesiune");
        String ip = request.getParameter("ip");
        String bd = request.getParameter("bd");
        String paw = request.getParameter("paw");
        String plf = request.getParameter("plf");
        String poo = request.getParameter("poo");
        String am = request.getParameter("am");
        String lft = request.getParameter("lft");

        Catalog catalog = new Catalog(id, codst, null, null, sesiune,ip, bd, paw, plf, poo, am, lft);
        catalogDAO.updateCatalog(catalog);
        response.sendRedirect("/catalog");
    }

    private void deleteMatricol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        catalogDAO.deleteCatalog(id);
        response.sendRedirect("/catalog");

    }

    private void insertLog() throws SQLException {
        catalogDAO.insertLog();

    }

}