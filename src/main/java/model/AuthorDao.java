/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Emilio
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy,Serializable {
    
    @Inject
    private DbStrategy db;
    
    
    
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDao() {
      
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
        
        

        
        return authors;
    }
    
    @Override
    public Author getSpecificAuthor(Integer authorId) throws SQLException, ClassNotFoundException{
        db.openConnection(driverClass, url, userName, password);
        Map<String,Object> record = db.findRecord("author", "author_id", authorId);
        Author author = new Author();
        author.setAuthorId((Integer)record.get("author_id"));
        author.setAuthorName(record.get("author_name").toString());
        author.setDateAdded((Date)record.get("date_added"));
        
       
        
        return author;
    }
    
    
    
    @Override 
    public void createAuthor(List<String> colNames,List<Object> colValues) throws Exception{
        db.openConnection(driverClass, url, userName, password);
 
       
        db.insertRecord("author", colNames, colValues);
        
        
        
    }
    
    @Override
    public void updateAuthor( List<String> colNameList, 
            List<Object> colValueList, int id) throws Exception{
        db.openConnection(driverClass, url, userName, password);
        
        db.UpdateRecord("author", colNameList, colValueList, "author_id", id );
        
       
    }
    
    @Override
    public void deleteAuthorById(String id) throws Exception{
        
        db.openConnection(driverClass, url, userName, password);
        Integer  primaryKeyValue = Integer.parseInt(id);
        db.deleteById("author", "author_id", primaryKeyValue);
        

        
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
    
    @Override
    public void initDao(String driverClass, String url, String userName, String password){
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }
    
    
    public DbStrategy getDb() {
        return db;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    
    
    public void setDb(DbStrategy db) {
        this.db = db;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public static void main(String[] args) 
            throws ClassNotFoundException, SQLException, Exception {
        
//        AuthorDaoStrategy dao =  new AuthorDao(new MySqlDbStrategy(),"com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book?useSSL=false","root","admin");
        
//        List<Author> authors = dao.getAuthorList();
//        List<Author> authorRecord = dao.getSpecificAuthor();
//        System.out.println(authors);
//        
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//       
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("Jose el Loco");
//        colValues.add("2015-12-02");
//        
//        dao.createAuthor("author", colNames, colValues);
//        dao.updateAuthor("author", colNames, colValues,"author_id", 8);
//        
//        System.out.println(authorRecord);
     
    }
}
