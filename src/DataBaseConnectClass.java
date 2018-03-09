import java.sql.*;

import javax.swing.JOptionPane;

public class DataBaseConnectClass {
    Connection conn=null;
	public DataBaseConnectClass(){
		String url="jdbc:mysql://"+HelloWorldClass.ipserver+"/helloworld";
		String user="root";
		try{
		conn=DriverManager.getConnection(url,user,"");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public Connection getConnection(){
		return conn;
	}

}
