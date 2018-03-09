import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.sql.*;
import java.util.Vector;


public class Comment extends JFrame {

	private JPanel contentPane;
	int code_post;
	public Comment(int code) {
		code_post=code;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 375);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		
		JScrollPane spr=new JScrollPane(loadCmnt(code_post));
		spr.setBorder(null);
		panel.add(spr);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setPreferredSize(new Dimension(504, 85));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Comment");
		btnNewButton.setBackground(new Color(51, 153, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(0, 0, 112, 85);
		panel_1.add(btnNewButton);
		
		JTextArea textarea=new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textarea);
		scrollPane.setBounds(124, 0, 354, 85);
		panel_1.add(scrollPane);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addComment(LogInClass.loggedin, code, textarea.getText());
				panel.repaint();
			}
		});
		
		
		
	}
	
	public JPanel loadCmnt(int code){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		//JOptionPane.showMessageDialog(null, "load commnet here");
		try {
			Statement st=c.createStatement();
			String query="select a.name,a.Last_name,c.comment from comment c,account a where a.email=c.email and c.code_pub="+code;
			ResultSet res=st.executeQuery(query);
			JLabel cmntr;
			Vector v=new Vector();
			while(res.next()){
				cmntr=new JLabel(res.getString(1)+" "+res.getString(2)+"> "+res.getString(3));
				cmntr.setForeground(Design.base);
				JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
				p1.setBackground(Color.white);
				p1.add(cmntr);
				p1.setPreferredSize(new Dimension(480, 30));
				v.add(p1);
			}
			JPanel panel=new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			for(int i=0;i<v.size();i++){
				panel.add((JPanel)v.elementAt(i));
			}
			return panel;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public static int getCommentNumber(int code){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		int n=0;
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from comment where code_pub="+code);
			if(res.next()){
				res.last();
				n=res.getRow();
				res.beforeFirst();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	
	public void addComment(String email,int code,String comment){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			PreparedStatement pst=c.prepareStatement("insert into comment (email,code_pub,comment) values (?,?,?)");
			pst.setString(1, email);
			pst.setInt(2, code);
			pst.setString(3, comment);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
