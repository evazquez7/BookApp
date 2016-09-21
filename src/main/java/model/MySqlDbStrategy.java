package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author evazquez7
 */
public class MySqlDbStrategy {
    private Connection conn;
    
    public void openConnection(String driverClass, String url, String userName,
            String password)
            throws ClassNotFoundException, SQLException{
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
        
    }
    
    public void closeConnection() throws SQLException{
        
        conn.close();
    }
    
    public List<Map> findAllRecords(String tableName) throws SQLException{
        String sql = "SELECT * FROM" + tableName;
        Statement stmt = conn.createStatement();
        ResultSet rs =stmt.executeQuery(sql);
        List<Map> records = new ArrayList<>();
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
    
    public static void main(String[]args) throws ClassNotFoundException, SQLException{
        MySqlDbStrategy db = new MySqlDbStrategy();
        
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Map> records = db.findAllRecords("author");
        System.out.println(records);
        db.closeConnection();
    }
}