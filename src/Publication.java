import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Vector;

public class Publication {
	public Publication(){
		
	}
	public static void addTextPub(String email,String pub){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			
			String query="insert into publication (pub,email,code_cat) values (?,?,?)";
			PreparedStatement pst=c.prepareStatement(query);
			pst.setString(1, pub);
			pst.setString(2, email);
			pst.setInt(3, 1);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Posted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void addPhotoPub(String email,File file){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			
			String query="insert into publication (email,code_cat,photo_pub) values (?,?,?)";
			PreparedStatement pst=c.prepareStatement(query);
			pst.setString(1, email);
			pst.setInt(2, 2);
			FileInputStream fis=new FileInputStream(file);
			pst.setBlob(3, fis);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Posted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static JPanel getPub(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			String query="select p.pub, p.email, p.code_cat, p.photo_pub, a.name, a.Last_name,ph.photo, p.code"
					+ " from publication p,account a,photo ph"
					+ " where a.email=p.email and a.email=ph.email and p.email=ph.email and ph.prof=1 and (a.email in"
					+ "(select email_f from freinds where email_ac='"+email+"') or a.email='"+email+"') order by p.code desc";
			ResultSet res=st.executeQuery(query);
			Vector v=new Vector();
			JPanel p1;
			JLabel profpic,fullName,pub;
			String tpub;
			ImageIcon phpub;
			JButton cmnt;
			while(res.next()){
				//JOptionPane.showMessageDialog(null, "pubs");
				 profpic=new JLabel(new ImageIcon(res.getBytes(7)));
				 fullName=new JLabel(res.getString(5)+" "+res.getString(6));
				 if(res.getInt(3)==1){
					 tpub=res.getString(1);
					 pub=new JLabel(tpub);
				 }else{
					 phpub=new ImageIcon(res.getBytes(4));
					 pub=new JLabel(phpub);
				 }
				 p1=new JPanel();
				 p1.setBackground(Color.WHITE);
				 JPanel p2=new JPanel();
				 p2.setBackground(Color.WHITE);
				 JPanel p3=new JPanel();
				 p3.setBackground(Color.WHITE);
				 p1.setLayout(new BorderLayout());
				 p2.setLayout(new FlowLayout(FlowLayout.LEFT));
				 p3.setLayout(new FlowLayout(FlowLayout.LEFT));
				 p2.add(profpic);
				 p2.add(fullName);
				 p1.add(p2, BorderLayout.NORTH);
				 p3.add(pub);
				 p1.add(p3);
				 JPanel p4=new JPanel(new FlowLayout(FlowLayout.LEFT));
				 String cmntlab="Comment";
				 int cdpst=res.getInt(8);
				 int x=Comment.getCommentNumber(cdpst);
				 if(x>0){
					 cmntlab=cmntlab+" ("+x+")";
				 }
				 cmnt=new JButton(cmntlab);
				 cmnt.setBorder(null);
				 cmnt.setBackground(Color.WHITE);
				 cmnt.setForeground(Design.base);
				 p4.setBackground(Color.WHITE);
				 p4.add(cmnt);
				 
				 cmnt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
							new Comment(cdpst);
						
					}
				});
				 p1.add(p4,BorderLayout.SOUTH);
				 v.add(p1);
			}
			JPanel panel=new JPanel();
			panel.setBackground(Color.white);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			for(int i=0;i<v.size();i++){
				panel.add((JPanel)v.elementAt(i));
				panel.add(new JSeparator(JSeparator.HORIZONTAL));
			}
			
			//JOptionPane.showMessageDialog(null, "Ready");
			
			return panel;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	

}
