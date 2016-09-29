/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emilio
 */
public interface DbStrategy {

    void closeConnection() throws SQLException;

    List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;
    public void deleteById(String tableName, String primaryKeyName, Object primaryKeyValue) throws SQLException;
    public Map<String,Object> findRecord(String tableName,String columnName, Object primaryKeyValue) throws SQLException;
    public void insertRecord(String tableName,List<String> colNamesList, List<Object> colValuesList) throws SQLException;
    public void UpdateRecord(String tableName, List<String> colNameList,List<Object> colValueList, String whereField, Object whereValue) throws SQLException;
    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
}
