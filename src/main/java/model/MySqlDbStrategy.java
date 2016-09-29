package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author evazquez7
 */
public class MySqlDbStrategy implements DbStrategy {
    private Connection conn;
    
    @Override
    public void openConnection(String driverClass, String url, String userName,
            String password)
            throws ClassNotFoundException, SQLException{
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
        
    }
    
    @Override
    public void closeConnection() throws SQLException{
        
        conn.close();
    }
    
    @Override
    public List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException{
        String sql = "SELECT * FROM " + tableName + " LIMIT "+ maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs =stmt.executeQuery(sql);
        List<Map<String,Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        while(rs.next()) {
            Map<String,Object> record = new LinkedHashMap<>();
            for (int i=0; i <colCount; i++){
                String colName= rsmd.getColumnName(i+1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);
        }
        
        return records;
    }
    
    @Override
    public Map<String,Object> findRecord(String tableName,String columnName, Object primaryKeyValue)
            throws SQLException{
        
       PreparedStatement stmt = buildFindStatement(tableName,columnName,primaryKeyValue);
       
        
        ResultSet rs = stmt.executeQuery();
        Map<String,Object> record = new LinkedHashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        while(rs.next()) {
         
         for(int i=0; i< colCount; i++){
             String colName = rsmd.getColumnName(i+1);
             Object colData = rs.getObject(colName);
             record.put(colName, colData);
         }
        }
        
        return record;
    }
    
    
    @Override
    public void insertRecord(String tableName,List<String> colNamesList, List<Object> colValuesList) throws SQLException{
         
        PreparedStatement stmt = buildInsertStatement(tableName,colNamesList,colValuesList);
        
        stmt.executeUpdate();
        
        
        
    }
    
    @Override
    public void UpdateRecord(String tableName, List<String> colNameList, 
            List<Object> colValueList, String whereField, Object whereValue) throws SQLException{
        PreparedStatement stmt = null;
        
        String sql = "UPDATE " + tableName + " SET ";
        
        StringJoiner sjSet = new StringJoiner(", ");
        
        for (int i = 0; i < colNameList.size(); i++) {
            sjSet.add(colNameList.get(i) + "= ?");
        }
        
        sql += sjSet.toString() + " WHERE " + whereField + "= ?";
//        System.out.println(sql);
//        System.exit(0);
        stmt = conn.prepareStatement(sql);
        
        for (int i = 0; i < colValueList.size(); i++){
            stmt.setObject(i+1,colValueList.get(i));
        }
        
        stmt.setObject(colValueList.size()+1, whereValue);
        
        
        
        
        
        stmt.executeUpdate();
    }
    
    @Override
    public void deleteById(String tableName, String primaryKeyName, Object primaryKeyValue) 
            throws SQLException{
        PreparedStatement stmt = buildDeleteStatement(tableName,primaryKeyName,primaryKeyValue);
        
        
        stmt.executeUpdate();
    }

//   
//    public int deleteRecord (String tableName,String columnName ,int primaryKey ) 
//            throws SQLException{
//        
//        String sql = "DELETE FROM " + tableName + " WHERE " + columnName +" = " +primaryKey;
//        Statement stmt = conn.createStatement();
//        int rs = stmt.executeUpdate(sql);
//        return rs;
//    }
    private PreparedStatement buildInsertStatement(String tableName,List<String> colNamesList, List<Object> colValuesList) throws SQLException {
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO " + tableName;
        List<String> colNames = colNamesList; 
        List<Object> colValues = colValuesList;
        StringJoiner sjColNames = new StringJoiner(", " , " (" , ") ");
        StringJoiner sjColValues = new StringJoiner(", ", " (", ") ");
        
        for(int i=0; i < colNames.size(); i++){
           sjColNames.add(colNames.get(i));
           
        }
        
        sql += sjColNames.toString() + "VALUES";
        
        for (Object colValue : colValues) {
            sjColValues.add("?");
        }   
        sql += sjColValues.toString();
        
        stmt = conn.prepareStatement(sql);
                
        for (int i = 0; i < colValues.size(); i++){
            stmt.setObject(i+1, colValues.get(i));    
        } 
        
        
        return stmt;
    }
    
    private PreparedStatement buildFindStatement(String tableName, String primaryKeyName, Object primaryKeyValue) throws SQLException{
         String sql ="SELECT * FROM " + tableName + " WHERE " + primaryKeyName +  " =? ";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, primaryKeyValue);
        
        return stmt;
    }
    
    private PreparedStatement buildDeleteStatement(String tableName, String primaryKeyName, Object primaryKeyValue) 
            throws SQLException{
        
        String sql = "DELETE FROM " + tableName + " WHERE " +primaryKeyName + " =? ";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, primaryKeyValue);
        
        return stmt;
    }
    
    
    
    public static void main(String[]args) throws ClassNotFoundException, SQLException{
        DbStrategy db = new MySqlDbStrategy();
        
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Map<String,Object>> records = db.findAllRecords("author", 500);
        System.out.println(records);
        
       // System.out.println(db.findRecord("author", "author_id",3 ));
        
//        db.deleteById("author", "author_id", 2);
//        System.out.println(db.findRecord("author", "author_id", 7));
        
        List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");
        
       
        List<Object> colValues = new ArrayList<>();
        colValues.add("Jose El Cua");
        colValues.add("2000-01-01");
        
        
        db.UpdateRecord("author", colNames, colValues,"author_id", 8);
        
        
        db.closeConnection();
        
        
    }
}