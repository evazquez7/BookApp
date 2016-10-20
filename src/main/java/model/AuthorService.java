/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Emilio
 */
@SessionScoped
public class AuthorService implements Serializable {

    @Inject
    private AuthorDaoStrategy dao;

    public AuthorService() {

    }


    public List<Author> getAuthorsList()
            throws ClassNotFoundException, SQLException {

        return dao.getAuthorList();
    }

    public void addOrEditAuthor(String authorId, String authorName) throws Exception {
        Integer id = null;
        
        if (authorId == null || authorId.equals(0)) {
            id = null;
            List<String> colNames = new ArrayList<>();
            colNames.add("author_Name");
            colNames.add("date_Added");
            
            List <Object> colValues = new ArrayList<>();
            colValues.add(authorName);
            colValues.add(new Date());
            
            dao.createAuthor(colNames, colValues);
            
        } else {
            id = Integer.parseInt(authorId);
            
            
            List<String> colNames = new ArrayList<>();
            colNames.add("author_Name");

            
            List <Object> colValues = new ArrayList<>();
            colValues.add(authorName);
            
            dao.updateAuthor(colNames, colValues,id);
        }
         
        

    }


    public Author getAuthorbyId(String authorId) throws SQLException, ClassNotFoundException {
         Author author = dao.getSpecificAuthor(Integer.parseInt(authorId));
        return author;
    }

    public void deleteAuthor(String id)
            throws Exception {

        dao.deleteAuthorById(id);
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }

    public static void main(String[] args)
            throws Exception {

//        AuthorDaoStrategy dao = new AuthorDao(new MySqlDbStrategy(),
//                 "com.mysql.jdbc.Driver",
//                 "jdbc:mysql://localhost:3306/book?useSSL=false","root","admin");
////         
//         AuthorService service = new AuthorService(dao);
//         List<Author> authors = service.getAuthorsList();
//         System.out.println(authors);
//         service.deleteAuthor("6");
//         List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//       
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("El Kocho");
//        colValues.add("2015-11-04");
//         service.createAuthor("author", colNames, colValues);
    }
}
