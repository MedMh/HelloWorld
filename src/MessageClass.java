import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;


public class MessageClass {
	public MessageClass(){
		
	}
	public static JList addFriendList(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		JList list;
		
		try {
			Statement st=c.createStatement();
			String query="select a.name,a.last_name from account a,freinds f where a.email=f.email_f and f.email_ac='"+email+"'";
			ResultSet res=st.executeQuery(query);
			String fr;
			JLabel l;
			Vector v=new Vector();
			while(res.next()){
				fr=res.getString(1)+" "+res.getString(2);
				//JOptionPane.showMessageDialog(null, fr);
				l=new JLabel(fr);
				v.add(fr);
			}
			String[]tab=new String[v.size()];
			for(int i=0;i<v.size();i++){
				tab[i]=(String)v.elementAt(i);
			}
			
			list=new JList(tab);
			
			JOptionPane.showMessageDialog(null, list.getModel().getSize());
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return new JList();
		}
		
	}

}
