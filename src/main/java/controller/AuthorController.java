/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Author;
import model.AuthorDao;
import model.AuthorDaoStrategy;
import model.AuthorService;
import model.MySqlDbStrategy;

/**
 *
 * @author Emilio
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    
    private static final String LIST_PAGE = "/AuthorsTable.jsp";
    private static final String ADD_EDIT_PAGE = "/addEdit.jsp";
    private static final String LIST_ACTION= "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ACTION_PARAM = "action";
    private static final String SAVE_ACTION = "Save";
    private static final String CANCEL_ACTION = "Cancel";
    private static final String ADD_EDIT_ACTION = "AddEdit";
    
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    @Inject
    private AuthorService authService;
        
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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        String page = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);
       try {
        configDbConnection();
        
        switch (action){
            
            case LIST_ACTION:
                this.refreshList(request, authService);
                page = LIST_PAGE;
                break;
            case ADD_EDIT_DELETE_ACTION:
                String submitAction = request.getParameter(SUBMIT_ACTION);

                if (submitAction.equals(ADD_EDIT_ACTION)){
                    String[] authorIds = request.getParameterValues("authorId");
                    
                    if (authorIds == null) {

                        
                    } else {

                        String authorId =  authorIds[0];
                        Author author = authService.getAuthorbyId(authorId);
                        request.setAttribute("author", author);
                    }
                    
                    page = ADD_EDIT_PAGE;
                    
                } else {
                    String[] authorIds = request.getParameterValues("authorId");
                    for (String authorId : authorIds) {
                        authService.deleteAuthor(authorId);
                    }
                    
                    this.refreshList(request, authService);
                    page = LIST_PAGE;
                }
                                
                break;
                
            case SAVE_ACTION:
                String authorName = request.getParameter("authorName");                
                String authorId = request.getParameter("authorId");
                authService.addOrEditAuthor(authorId, authorName);
                this.refreshList(request, authService);
                
                page = LIST_PAGE;
                
                break;
            
            case CANCEL_ACTION: 
                this.refreshList(request, authService);
                page = LIST_PAGE;
        }
        
        
       } catch(Exception e) {
        request.setAttribute("authors","Invalid");   
       } 
       
        RequestDispatcher view = request.getRequestDispatcher(page); 
        view.forward(request, response);
    }
    
    private void configDbConnection(){
      authService.getDao().initDao(driverClass, url, userName, password);
      }
    
    private void refreshList(HttpServletRequest request, AuthorService authorService) throws Exception{
        List<Author> authors = authorService.getAuthorsList();
        request.setAttribute("authors", authors);
    }
    
    @Override
    public void init() throws ServletException {
            driverClass = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/book?useSSL=false";
            userName = "root";
            password = "admin";
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
