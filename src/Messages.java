import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Messages extends JFrame {
	private JPanel contentPane;
	static String emailfr,name,lname,message="";
	static JPanel panel_2;
	public Messages() {
		setTitle("Messages");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		new AcceptDataClass().start();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(200, panel.getHeight()));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		
		JList list = MessageClass.addFriendList(LogInClass.loggedin);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 200, 347);
		panel.add(scrollPane);
		
		
		int n=panel.getWidth();
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setPreferredSize(new Dimension(500, 100));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(0, 0, 200, 100);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setBackground(Design.base);
		
		JTextArea textArea=new JTextArea();
		Design.focusDesigntextArea(textArea, "Type your message here...");
		JScrollPane scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(210, 0, 564, 100);
		panel_1.add(scrollPane_1);
		
		
		panel_2 = new JPanel();
		panel_2.setLayout(new BoxLayout(panel_2,BoxLayout.PAGE_AXIS));
		panel_2.setBackground(new Color(255, 255, 255));
		JScrollPane spr=new JScrollPane(panel_2);
		contentPane.add(spr, BorderLayout.CENTER);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				String f=(String) list.getSelectedValue();
				//JOptionPane.showMessageDialog(null, f);
				name=f.substring(0, f.indexOf(" "));
				lname=f.substring(f.indexOf(" ")+1);
				emailfr=account.getEmailByName(name, lname);
				panel_2.removeAll();
				panel_2.add(loadMessage(LogInClass.loggedin, emailfr));
				
			}
		});
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				message=textArea.getText();
				account a1=new account();
				account a2=a1.getAccountObj(LogInClass.loggedin);
				String message1=a2.getName()+" "+a2.getLname()+">> "+message;
				if(account.isOnline(emailfr)){
					new SendDataClass(account.getIp(emailfr), message1).start();
				}else{
					JOptionPane.showMessageDialog(null, "your friend is not online");
				}
				addMessage(LogInClass.loggedin, emailfr);
				panel_2.removeAll();
				panel_2.add(loadMessage(LogInClass.loggedin, emailfr));
				
			}
		});
		
		
	}
	public static JPanel loadMessage(String email_ac,String email_f){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		
		try {
			Statement st=c.createStatement();
			ResultSet res = st.executeQuery("select a.name,a.Last_name,m.message from message m,account a where a.email=m.email_sender and ((m.email_sender='"+email_ac+"' and m.email_rec='"+email_f+"') or (m.email_rec='"+email_ac+"' and m.email_sender='"+email_f+"'))");
			JLabel fullName,ms;
			JPanel p1;
			Vector v=new Vector();
			
			while(res.next()){
				fullName=new JLabel(res.getString(1)+" "+res.getString(2)+">> ");
				ms=new JLabel(res.getString(3));
				p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
				fullName.setForeground(Design.base);
				ms.setForeground(Design.base);
				p1.add(fullName);
				p1.add(ms);
				p1.setBackground(Color.WHITE);
				v.add(p1);
			}
			JPanel panel = new JPanel();
			panel.setForeground(Color.WHITE);
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			for(int i=0;i<v.size();i++){
				panel.add((JPanel)v.elementAt(i));
				panel.add(new JSeparator(JSeparator.HORIZONTAL));
			}
			return panel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		
	}
	
	public static JPanel displayMessage(String msg){
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel(msg);
		label.setForeground(Design.base);
		p1.setBackground(Color.WHITE);
		p1.add(label);
		return p1;
	}
	
	public static void addMessage(String emails,String emailf){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		String datemsg = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		try {
			Statement st=c.createStatement();
			st.executeUpdate("insert into message (message,email_sender,email_rec,Date_msg) values ('"+message+"','"+emails+"','"+emailf+"','"+datemsg+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}












