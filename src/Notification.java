import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.util.Vector;

public class Notification extends JFrame {

	private JPanel contentPane;

	public Notification() {
		setTitle("Notification");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 336);
		contentPane = new JPanel();
		setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		JPanel p1=getNotificationPanel(LogInClass.loggedin);
		panel.add(p1);
	}
	
	public JPanel getNotificationPanel(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		
		try {
			Statement st=c.createStatement();
			String query="select a.name,a.Last_name,d.state from demande d,account a where a.email=d.email_rec and d.email_sender='"+email+"' and (d.state='acceptee' or d.state='rejetee')";
			ResultSet res=st.executeQuery(query);
			JPanel panel=new JPanel();
			JLabel label;
			String etat="";
			String not="";
			Vector v=new Vector();
			while(res.next()){
				if(res.getString(3).equals("acceptee")){
					etat="accepted";
				}else{
					etat="rejected";
				}
				not=res.getString(1)+" "+res.getString(2)+" "+etat+" your request";
				label=new JLabel(not);
				v.add(label);
			}
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			for(int i=0;i<v.size();i++){
				panel.add(Box.createRigidArea(new Dimension(0, 20)));
				panel.add((JLabel)v.elementAt(i));
			}
			return panel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

}
