import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class LogInClass extends JFrame{
	int n=0;
	public static String loggedin;
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	ImageIcon img=new ImageIcon("pics/world.jpg");
	JLabel image=new JLabel(img);
	JLabel label1=new JLabel("Login and say Hello World");
	JTextField login=new JTextField();
	JPasswordField pwd=new JPasswordField();
	JButton log=new JButton("Login");
	JButton sign=new JButton ("you don't have an account? signup here");
	public LogInClass(){
		setTitle("Login");
		setVisible(true);
		setBounds(400,50,400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.add(p1);
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setBackground(Color.WHITE);
		p1.add(p);
		p.setLayout(null);
		image.setBounds(80, 20, 210, 250);
		p.add(image);
		label1.setForeground(Design.base);
		label1.setBounds(30, 290, 360, 30);
		label1.setFont(Design.f1);
		p.add(label1);
		login.setBounds(40, 360, 300, 25);
		login.setText("Email...");
		login.setForeground(Color.GRAY);
		p.add(login);
		login.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=login.getText();
				if(s1.equals("")){
					login.setText("Email...");
					login.setForeground(Color.GRAY);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				String s1=login.getText();
				if((s1.equals(""))||(s1.equals("Email..."))){
					login.setText("");
					login.setForeground(Color.black);
				}
				
			}
		});
		pwd.setBounds(40, 410, 300, 25);
		pwd.setEchoChar((char)0);
		p.add(pwd);
		pwd.setText("password...");
		pwd.setForeground(Color.GRAY);
		pwd.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=pwd.getText();
				if(s1.equals("")){
					pwd.setText("password...");
					pwd.setEchoChar((char)0);
					pwd.setForeground(Color.GRAY);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				String s1=pwd.getText();
				if((s1.equals(""))||(s1.equals("password..."))){
					pwd.setText("");
					pwd.setEchoChar((char)42);
					pwd.setForeground(Color.black);
				}
				
			}
		});
		log.setBounds(155, 460, 70, 30);
		p.add(log);
		log.setBackground(Design.base);
		log.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		log.setForeground(Color.white);
		
		
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataBaseConnectClass dbc=new DataBaseConnectClass();
				String s1=login.getText();
				String s2=pwd.getText();
				if((s1.equals(""))||(s1.equals("Email..."))){
					JOptionPane.showMessageDialog(null, "Enter the email first");
				}else{
					account acc=new account();
					try {
						DESEncryptionClass des=new DESEncryptionClass();
						ResultSet res=  acc.getAccount(s1);
						if(res!=null){
						if(res.next()){
							String pass=res.getString("password");
							String pdcr=des.decrypt(pass);
							if(s2.equals(pdcr)){
							if(acc.isConfirmed(s1)){
							loggedin=s1;
							String sn=res.getString("name");
							JOptionPane.showMessageDialog(null, "Welcome "+sn);
							
							new Home();
							setVisible(false);
							}else{
								if(acc.hasaCode(s1)==false){
									acc.generateCode(s1);
									loggedin=s1;
								}
								new ConfirmeClass(s1);
								loggedin=s1;
							}
							
							}else{
								JOptionPane.showMessageDialog(null, "password incorrect");
								n++;
								if(n==3){
									JOptionPane.showMessageDialog(null, "you can not access to this account");
									System.exit(0);
								}
							}
						}else{
							JOptionPane.showMessageDialog(null, "email incorrect");
						}
						}else{
							JOptionPane.showMessageDialog(null, "account not found");
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
						
					}
					
				}
				
			}
		});
		sign.setBounds(40, 510, 300, 30);
		sign.setBackground(Color.white);
		sign.setBorder(null);
		sign.setForeground(Design.base);
		p.add(sign);
		sign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SignUpClass();
				
			}
		});
	}
}
