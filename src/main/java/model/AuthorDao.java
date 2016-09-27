/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emilio
 */
public class AuthorDao implements AuthorDaoStrategy {
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDao(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

   
    
    @Override
    public List<Author> getAuthorList()
            throws ClassNotFoundException, SQLException{
        
        db.openConnection(driverClass, url, userName, password);
        List<Map<String,Object>> records = db.findAllRecords("author", 500);
        List<Author> authors = new ArrayList<>();
        for(Map<String,Object> rec : records){
            Author author = new Author();
            int id = Integer.parseInt(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name").toString();
            author.setAuthorName(name != null ? name : "");
            Date date = (Date)rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }
        
        
        db.closeConnection();
        
        return authors;
    }
    
    @Override
    public List<Author> getSpecificAuthor() throws SQLException, ClassNotFoundException{
        db.openConnection(driverClass, url, userName, password);
        Map<String,Object> record = db.findRecord("author", "author_id", 3);
        List<Author> authorRecord = new ArrayList<>();
        Author author = new Author();
        int id =Integer.parseInt(record.get("author_id").toString());
        author.setAuthorId(id);
        String name = record.get("author_name").toString();
        author.setAuthorName(name);
        Date date = (Date)record.get("date_added");
        author.setDateAdded(date);
        authorRecord.add(author);
        
        db.closeConnection();
        
        return authorRecord;
    }
    
    @Override
    public void deleteAuthorById(String id) throws Exception{
        
        db.openConnection(driverClass, url, userName, password);
        Integer  primaryKeyValue = Integer.parseInt(id);
        db.deleteById("author", "author_id", primaryKeyValue);
        
        db.closeConnection();
        
    }
    
//    @Override
//    public int deleteAuthor()
//            throws ClassNotFoundException, SQLException {
//        
//        
//        db.openConnection(driverClass, url, userName, password);
//        int recordDeleted = db.deleteRecord("author", "author_id", 2);
//        db.closeConnection();
//        
//      return  recordDeleted;
//    } 
    
    public DbStrategy getDb() {
        return db;
    }

    public void setDb(DbStrategy db) {
        this.db = db;
    }
    
    
    public static void main(String[] args) 
            throws ClassNotFoundException, SQLException {
        
        AuthorDaoStrategy dao =  new AuthorDao(new MySqlDbStrategy(),"com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book?useSSL=false","root","admin");
        
        List<Author> authors = dao.getAuthorList();
        List<Author> authorRecord = dao.getSpecificAuthor();
        System.out.println(authors);
        
        
        System.out.println(authorRecord);
     
    }
}
