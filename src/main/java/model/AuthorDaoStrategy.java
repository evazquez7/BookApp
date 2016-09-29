/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Emilio
 */
public interface AuthorDaoStrategy {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    public void deleteAuthorById(String id) throws Exception;
    public void createAuthor(String tableName,List<String> colNames,List<Object> colValues) throws Exception;
    public List<Author> getSpecificAuthor() throws SQLException, ClassNotFoundException;
    
}
