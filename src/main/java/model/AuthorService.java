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
    
    public int deleteAuthor() 
            throws ClassNotFoundException, SQLException{
        
        return dao.deleteAuthor();
    }
            
    public static void main(String[] args) 
            throws ClassNotFoundException, SQLException {
         
        AuthorDaoStrategy dao = new AuthorDao(new MySqlDbStrategy(),
                 "com.mysql.jdbc.Driver",
                 "jdbc:mysql://localhost:3306/book?useSSL=false","root","admin");
         
         AuthorService service = new AuthorService(dao);
         List<Author> authors = service.getAuthorsList();
         System.out.println(authors);
         System.out.println(service.deleteAuthor()+ " author deleted");
    }
}

