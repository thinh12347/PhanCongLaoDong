package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class KetNoiCSDL {
	public static Connection con=null;
    private static KetNoiCSDL instance=new KetNoiCSDL();
    public static KetNoiCSDL getinstance() {
    	return instance;
    }
    
    public void Connection() throws SQLException {
    	String url="jdbc:sqlserver://localhost:1433;databaseName=Baitaplon";
    	String user="sa";
    	String pass="123";
    	con=DriverManager.getConnection(url, user, pass);
       
    }
    public void disConnect(){
       if(con!=null) {
    	   try {
    		   con.close();
    	   }catch(SQLException e) {
    		   e.printStackTrace();
    	   }
       }
        
    }
    public static Connection getConnection() {
    	return con;
    }

}
