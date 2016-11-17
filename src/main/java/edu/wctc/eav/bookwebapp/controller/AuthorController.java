/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.eav.bookwebapp.controller;

import edu.wctc.eav.bookwebapp.service.AuthorFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import edu.wctc.eav.bookwebapp.entity.Author;
import edu.wctc.eav.bookwebapp.entity.Book;
import edu.wctc.eav.bookwebapp.service.BookFacade;
import java.util.Date;

/**
 *
 * @author Emilio
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String LIST_PAGE = "/AuthorsTable.jsp";
    private static final String ADD_EDIT_PAGE = "/addEditAuthor.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ACTION_PARAM = "action";
    private static final String ADD_ACTION = "Add";
    private static final String UPDATE_ACTION = "Update";
    private static final String CANCEL_ACTION = "Cancel";
    private static final String ADD_EDIT_ACTION = "AddEdit";

    private String driverClass;
    private String url;
    private String userName;
    private String password;

    private String dbJndiName;

    //@Inject
    //private AuthorService authService;
    @Inject
    private AuthorFacade authService;
    
    @Inject 
    private BookFacade bookService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String page = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);
        Author a = null;
        try {

            switch (action) {

                case LIST_ACTION:
                    this.refreshAuthorList(request);
                    page = LIST_PAGE;
                    break;
                case ADD_EDIT_DELETE_ACTION:
                    String submitAction = request.getParameter(SUBMIT_ACTION);

                    if (submitAction.equals(ADD_EDIT_ACTION)) {
                        String[] authorIds = request.getParameterValues("authorId");

                        if (authorIds == null) {

                        } else {

                            String authorId = authorIds[0];
                             a = authService.find(new Integer(authorId));
                            request.setAttribute("author", a);
                        }

                        page = ADD_EDIT_PAGE;

                    } else {
                        String[] authorIds = request.getParameterValues("authorId");
                        for (String authorId : authorIds) {
                            a = authService.find(new Integer(authorId));
                            authService.remove(a);
                        }

                        this.refreshAuthorList(request);
                        page = LIST_PAGE;
                    }

                    break;

                case ADD_ACTION:
                    String authorName = request.getParameter("authorName");

                    Author author = new Author();
                    author.setAuthorName(authorName);
                    author.setDateAdded(new Date());
                    authService.create(author);
                    
                    
                    this.refreshAuthorList(request);

                    page = LIST_PAGE;

                    break;
                case UPDATE_ACTION:
                    String name = request.getParameter("authorName");
                    String authorId = request.getParameter("authorId");

                    a = authService.find(new Integer(authorId));
                    a.setAuthorName(name);
                    authService.edit(a);

                case CANCEL_ACTION:
                    this.refreshAuthorList(request);
                    page = LIST_PAGE;
            }

        } catch (Exception e) {
            request.setAttribute("authors", "Invalid");
        }

        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
    }

    private void refreshAuthorList(HttpServletRequest request) throws Exception {
        List<Author> authors = authService.findAll();
        request.setAttribute("authors", authors);
    }

    

    @Override
    public void init() throws ServletException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
