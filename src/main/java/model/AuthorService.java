/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Emilio
 */
public class AuthorService  {
    
    private AuthorDaoStrategy dao;
    
    public AuthorService(AuthorDaoStrategy dao){
        this.dao = dao;
    }
   
   

    public List<Author> getAuthorsList() 
            throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorList();    
    }
    
    public void createAuthor(String tableName,List<String> colNames,List<Object> colValues) throws Exception{
        dao.createAuthor(tableName, colNames, colValues);
    }
    
    public void deleteAuthor(String id) 
            throws Exception{
        
        dao.deleteAuthorById(id);
    }
            
    public static void main(String[] args) 
            throws Exception {
         
        AuthorDaoStrategy dao = new AuthorDao(new MySqlDbStrategy(),
                 "com.mysql.jdbc.Driver",
                 "jdbc:mysql://localhost:3306/book?useSSL=false","root","admin");
         
         AuthorService service = new AuthorService(dao);
         List<Author> authors = service.getAuthorsList();
         System.out.println(authors);
         service.deleteAuthor("6");
         List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");
       
        List<Object> colValues = new ArrayList<>();
        colValues.add("El Kocho");
        colValues.add("2015-11-04");
         service.createAuthor("author", colNames, colValues);
    }
}

