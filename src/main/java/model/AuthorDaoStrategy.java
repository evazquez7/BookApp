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
    public void createAuthor(List<String> colNames,List<Object> colValues) throws Exception;
    public Author getSpecificAuthor(Integer authorId) throws SQLException, ClassNotFoundException;
    public void updateAuthor( List<String> colNameList, 
            List<Object> colValueList, int whereValue) throws Exception;
    public void initDao(String driverClass, String url, String userName, String password);
    
}
