import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Demande {
	String state,from,to,date;

	public Demande(String from, String to) {
		super();
		this.state = "En attente";
		this.from = from;
		this.to = to;
		this.date = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void addContact(){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			String query="insert into demande (state,email_sender,email_rec,dateDem) values('"+state+"','"+from+"','"+to+"','"+date+"')";
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Request sent successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public static ResultSet getRequest(String from,String to){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from demande where email_sender='"+from+"' and email_rec='"+to+"'and state='En attente'");
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public static ResultSet getAcceptedRequest(String from,String to){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from demande where email_sender='"+from+"' and email_rec='"+to+"'and state='acceptee'");
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public static ResultSet getRejectedRequest(String from,String to){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from demande where email_sender='"+from+"' and email_rec='"+to+"'and state='rejetee'");
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	public static boolean ismyFriend(String emailacc,String emailstr){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from freinds where email_ac='"+emailacc+"' and email_f='"+emailstr+"'");
			return res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
}
	
