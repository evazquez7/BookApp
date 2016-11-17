/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.eav.bookwebapp.controller;

import edu.wctc.eav.bookwebapp.entity.Author;
import edu.wctc.eav.bookwebapp.entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.eav.bookwebapp.service.AuthorFacade;
import edu.wctc.eav.bookwebapp.service.BookFacade;

/**
 *
 * @author Emilio
 */
public class BookController extends HttpServlet {

    private static final String LIST_PAGE = "/BooksTable.jsp";
    private static final String ADD_EDIT_PAGE = "/addEditBook.jsp";
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
    private BookFacade bookService;

    @Inject
    private AuthorFacade authService;

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
        Book book = null;
        try {

            switch (action) {

                case LIST_ACTION:
                    this.refreshList(request);
                    page = LIST_PAGE;
                    break;
                case ADD_EDIT_DELETE_ACTION:
                    String submitAction = request.getParameter(SUBMIT_ACTION);

                    if (submitAction.equals(ADD_EDIT_ACTION)) {
                        String[] bookIds = request.getParameterValues("bookId");

                        if (bookIds == null) {

                            this.refreshAuthorList(request);
                            
                            
                        } else {

                            String bookId = bookIds[0];
                            book = bookService.find(new Integer(bookId));
                            request.setAttribute("book", book);
                            this.refreshAuthorList(request);

                        }

                        page = ADD_EDIT_PAGE;

                    } else {
                        String[] bookIds = request.getParameterValues("bookId");
                        for (String bookId : bookIds) {
                            book = bookService.find(new Integer(bookId));
                            bookService.remove(book);

                        }

                        this.refreshList(request);
                        this.refreshAuthorList(request);
                        page = LIST_PAGE;
                    }

                    break;

                case ADD_ACTION:

                    String bookTitle = request.getParameter("bookTitle");
                    String isbn = request.getParameter("isbn");
                    String authorId = request.getParameter("authorId");
                    
                    Author author = new Author();
                    author.setAuthorId(Integer.parseInt(authorId));

                    Book b = new Book();
                    b.setTitle(bookTitle);
                    b.setAuthorId(author);
                    b.setIsbn(isbn);

                    bookService.create(b);
                    this.refreshList(request);

                    page = LIST_PAGE;

                    break;
                case UPDATE_ACTION:
                    String name = request.getParameter("bookTitle");
                    String bookId = request.getParameter("bookId");
                    isbn = request.getParameter("isbn");
                    authorId = request.getParameter("authorId");
                    
                    b = bookService.find(new Integer(bookId));
                    b.setTitle(name);
                    b.setIsbn(isbn);
                    author = null;
                    if (authorId != null){
                        author = authService.find(new Integer(authorId));
                        b.setAuthorId(author);
                    }
                    bookService.edit(b);
                    this.refreshList(request);
                    page = LIST_PAGE;
                    break;
                    
                case CANCEL_ACTION:
                    this.refreshList(request);
                    page = LIST_PAGE;
                    break;
                    
                    
            }

        } catch (Exception e) {
            request.setAttribute("books", "Invalid");
        }

        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
    }

    private void refreshList(HttpServletRequest request) throws Exception {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
    }

    private void refreshAuthorList(HttpServletRequest request) throws Exception {
        List<Author> authors = authService.findAll();
        request.setAttribute("authors", authors);
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
