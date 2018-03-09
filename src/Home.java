import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.log.Log;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Home extends JFrame {

	private JPanel contentPane;
	File selectedFile;

	public Home() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(10, 10, 1300, 700);
		setTitle("Home");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		account.setIpTo(LogInClass.loggedin);
		
		
		account a=new account();
		a=a.getAccountObj(LogInClass.loggedin);
		account.setOnline(LogInClass.loggedin);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1300, 70));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 1, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBackground(Design.base);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblLabel = new JLabel("Label1");
		lblLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 11));
		lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel.setForeground(Color.WHITE);
		lblLabel.setBounds(75, 11, 96, 14);
		panel_3.add(lblLabel);
		lblLabel.setText(a.getName());
		
		
		
		JLabel lblLabel_1 = new JLabel("Label2");
		lblLabel_1.setForeground(Color.WHITE);
		lblLabel_1.setFont(new Font("Arial Unicode MS", Font.BOLD, 11));
		lblLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel_1.setBounds(75, 36, 96, 14);
		panel_3.add(lblLabel_1);
		lblLabel_1.setText(a.getLname());
		
		ImageIcon ic=a.getProfilePic(a.getEmail());
		JLabel l=new JLabel(ic);
		l.setBounds(0, 0, 70, 70);
		panel_3.add(l);
		
		
		
		JButton btnNewButton_2 = new JButton("Profile");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(Design.base);
		panel.add(btnNewButton_2);
		btnNewButton_2.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Profile();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Messages");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Messages m=new Messages();
				m.setVisible(true);
				
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(Design.base);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		
		JButton btnContact = new JButton("Contact");
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Contact cn = new Contact();
				cn.setVisible(true);
			}
		});
		btnContact.setForeground(new Color(255, 255, 255));
		btnContact.setBackground(Design.base);
		panel.add(btnContact);
		btnContact.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		
		JButton btnNotification = new JButton("Notification");
		btnNotification.setForeground(Color.WHITE);
		btnNotification.setBackground(Design.base);
		panel.add(btnNotification);
		btnNotification.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		btnNotification.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Notification();
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account.setOffline(LogInClass.loggedin);
				setVisible(false);
				new LogInClass();
				account.delIp(LogInClass.loggedin);
			}
		});
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setBackground(Design.base);
		panel.add(btnLogout);
		btnLogout.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(UIManager.getColor("Button.background"));
		panel_4.setBounds(10, 11, 999, 128);
		panel_4.setPreferredSize(new Dimension(1300, 100));
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		panel_4.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		
		JPanel panel_6 = new JPanel();
		//panel_6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_6, BorderLayout.CENTER);
		//panel_4.add(new JSeparator(JSeparator.HORIZONTAL));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		
		JTextArea textArea=new JTextArea();
		
		Design.focusDesigntextArea(textArea, "What's on your mind...");
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		panel_6.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_7.setBackground(Color.WHITE);
		panel_4.add(panel_7, BorderLayout.NORTH);
		//panel_4.add(new JSeparator(JSeparator.HORIZONTAL));
		
		
		JLabel lblPic = new JLabel("pic..");
		panel_7.add(lblPic);
		
		JButton btnChoosePic = new JButton("choose pic");
		btnChoosePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc=new JFileChooser();
				jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
				jfc.addChoosableFileFilter(filter);
				 int result = jfc.showSaveDialog(null);
	            	if(result == JFileChooser.APPROVE_OPTION){
	            		selectedFile = jfc.getSelectedFile();
						String path = selectedFile.getAbsolutePath();
					    lblPic.setText(path);
				     }
				     else if(result == JFileChooser.CANCEL_OPTION){
						 System.out.println("No Data");
			 }
			}
		});
		
		
		
		btnChoosePic.setBackground(new Color(51, 153, 255));
		btnChoosePic.setForeground(Color.WHITE);
		panel_7.add(btnChoosePic);
		
		JButton btnPost_1 = new JButton("Post");
		btnPost_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Publication.addPhotoPub(LogInClass.loggedin, selectedFile);
			}
		});
		
		
		btnPost_1.setBackground(new Color(51, 153, 255));
		btnPost_1.setForeground(Color.WHITE);
		panel_7.add(btnPost_1);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_8.setBackground(Color.WHITE);
		panel_4.add(panel_8, BorderLayout.SOUTH);
		
		JButton btnPost = new JButton("Post");
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Publication.addTextPub(LogInClass.loggedin, textArea.getText());
				textArea.setText("");
			}
		});
		btnPost.setForeground(new Color(255, 255, 255));
		panel_8.add(btnPost);
		btnPost.setBackground(Design.base);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 255, 255));
		panel_8.add(btnCancel);
		btnCancel.setBackground(Design.base);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 150, 999, 416);
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout());
		JScrollPane scp=new JScrollPane(Publication.getPub(LogInClass.loggedin));
		panel_5.add(scp,BorderLayout.CENTER);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setPreferredSize(new Dimension(250, panel_2.getHeight()));
		contentPane.add(panel_2, BorderLayout.EAST);	
		JScrollPane spr=new JScrollPane(getOnlineFr(LogInClass.loggedin));
		//spr.setPreferredSize(new Dimension(panel_2.getWidth(), panel_2.getHeight()));
		spr.setBorder(null);
		panel_2.add(spr);
	}
	
	public JList getOnlineFr(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			
			//change a.on_off from 0 to 1 later
			
			String query="select a.name, a.Last_name from account a,freinds f where a.email=f.email_f and a.on_off=0 and f.email_ac='"+email+"'";
			ResultSet res=st.executeQuery(query);
			Vector v=new Vector();
			String str="";
			while(res.next()){
				str=res.getString(1)+" "+res.getString(2);
				v.add(str);
			}
			String[]tab=new String[v.size()];
			for(int i=0;i<v.size();i++){
				tab[i]=(String)v.elementAt(i);
			}
			JList list=new JList(tab);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
}
