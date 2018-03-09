import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class account{
	String email;
	String name;
	String Lname;
	String Bdate;
	String dateR;
	String mf;
	String pwd;
    DESEncryptionClass des=null;
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getBdate() {
		return Bdate;
	}
	public void setBdate(String bdate) {
		Bdate = bdate;
	}
	public String getDateR() {
		return dateR;
	}
	public void setDateR(String dateR) {
		this.dateR = dateR;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public account(){
    	
    }
	public account(String email, String name, String lname, String bdate, String mf, String pwd) {
		
		this.email = email;
		this.name = name;
		Lname = lname;
		Bdate = bdate;
		this.mf = mf;
		try {
			des=new DESEncryptionClass();
			this.pwd=des.encrypt(pwd);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void addAccount(){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		String dateR = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		Connection c=dbc.getConnection();
		Statement st;
		try {
			st=c.createStatement();
			String query="insert into account (email,name,last_name,bdate,DateReg,m_f,password) values('"+email+"','"+name+"','"+Lname+"','"+Bdate+"','"+dateR+"','"+mf+"','"+pwd+"')";
			st.executeUpdate(query);
			String path="";
			if(mf.equals("male")){
				path="pics/male - tiny.png";
			}else{
				path="pics/female -tiny.jpg";
			}
			
			File image = new File(path);
			FileInputStream fis;
			
			try {
				fis = new FileInputStream ( image );
				String query1="insert into photo (photo,prof,email) values(?,?,?)";
				PreparedStatement pst=c.prepareStatement(query1);
				pst.setBinaryStream (1, fis, (int) image.length() );
				pst.setInt(2, 1);
				pst.setString(3, email);
				pst.executeUpdate();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			JOptionPane.showMessageDialog(null, "your account has been added to the database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
	}
	public ResultSet getAccount(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from account where email='"+email+"'");
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	public boolean isConfirmed(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from account where email='"+email+"'");
			if(res.next()){
				String cnf=res.getString("state");
				if(cnf.equals("notconf")){
					return false;
				}else{
					return true;
				}
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public boolean hasaCode(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from account where email='"+email+"'");
			if(res.next()){
				int code=res.getInt("Code_Conf");
				if(code==0){
					return false;
				}else{
					return true;
				}
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			return false;
		}
	}
	public void generateCode(String email){
		Random r=new Random();
		int i= r.nextInt(99999);
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			st.executeUpdate("update account set Code_Conf="+i+" where email='"+email+"'");
			new MailClass(email,i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public int getCode(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from account where email='"+email+"'");
			if(res.next()){
				int code=res.getInt("Code_Conf");
				return code;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 0;
		}
	}
	public void setConfirmed(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			st.executeUpdate("update account set state='confirmed' where email='"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setOnline (String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			st.executeUpdate("update account set on_off=1 where email='"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setOffline (String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			st.executeUpdate("update account set on_off=0 where email='"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public account getAccountObj(String email){
		
		ResultSet res=getAccount(email);
		
		String s2="",s3="",s4="",s5="",s6="",s7="";
		try {
			res.next();
			s2 = res.getString("name");
		    s3=res.getString("last_name");
			s4=res.getString("bdate");
			s5=res.getString("m_f");
			s6=res.getString("password");
			s7=res.getString("DateReg");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		account a=new account(email, s2, s3, s4, s5, s6);
		a.setDateR(s7);
		return a;
	}
	
	/*public void setDefaultProfilePicture(String email){
		
	}*/
	
	public ImageIcon getProfilePic(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		ImageIcon ic=null;;
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select photo from photo where email='"+email+"' and prof=1");
			if(res.next()){
				ic=new ImageIcon(res.getBytes(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ic;
	}
	
	
	
	
	public static JPanel getAllListPanel(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		JLabel image,FullName;
		ImageIcon ic=null;
		Vector v=new Vector();
		JPanel p1,panelList;
		
		int w;
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select name,last_name,photo,a.email from account a,photo p where a.email=p.email and p.prof=1 and a.email<>'"+email+"' and a.state='confirmed'");
			
			while(res.next()){
				ic=new ImageIcon(res.getBytes(3));
				image=new JLabel(ic);
				String em=res.getString(4);
				FullName=new JLabel(res.getString(1)+" "+res.getString(2));
				p1=new JPanel();
				FlowLayout fl=new FlowLayout(FlowLayout.LEFT);
				p1.setLayout(fl);
				p1.add(image);
				p1.add(Box.createRigidArea(new Dimension(20,0)));
				p1.add(FullName);
				
				JButton add=new JButton("add");
				FullName.setPreferredSize(new Dimension(400, 20));
				add.setForeground(Color.white);
				add.setBackground(Design.base);
				add.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Demande dem=new Demande(email, em);
						dem.addContact();
						add.setEnabled(false);
					}
				});
				if(Demande.getRequest(email, em).next()){
					
					JLabel lb=new JLabel("Sent...");
					lb.setForeground(Design.base);
					p1.add(lb);
				}else if(Demande.ismyFriend(email, em)){
					
					JLabel lb=new JLabel("Friends");
					lb.setForeground(Color.GREEN);
					p1.add(lb);
					
				}else if(Demande.getRejectedRequest(email, em).next()){
					
					JLabel lb=new JLabel("Rejected");
					lb.setForeground(Color.RED);
					p1.add(lb);
					
				}else{	
				p1.add(add);
				}
				p1.setBackground(Color.WHITE);
				
				v.add(p1);
			}
			panelList=new JPanel();
			panelList.setBackground(Color.white);
			panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
			for(int i=0;i<v.size();i++){
				panelList.add((JPanel)v.elementAt(i));
				panelList.add(new JSeparator(JSeparator.HORIZONTAL));
			}
			return panelList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	public static int getRequestNumber(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		int n=0;
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from demande where email_rec='"+email+"' and state='En attente'");
				res.last();
				n=res.getRow();
				res.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return n;
	}
	
	
	
	
	
	public static JPanel getAllRequestsPanel(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		account a=new account();
		account a1=new account();
		ImageIcon imc;
		JLabel img,FullName;
		
		Vector v=new Vector();
		JPanel p1;
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select * from demande where email_rec='"+email+"' and state='En attente'");
			
			while(res.next()){
				String em=res.getString(2);
				imc=a.getProfilePic(res.getString(2));
				img=new JLabel(imc);
				a1=a.getAccountObj(res.getString(2));
				FullName=new JLabel(a1.name+" "+a1.Lname);
				p1=new JPanel();
				p1.setLayout(new FlowLayout());
				p1.add(img);
				p1.add(Box.createRigidArea(new Dimension(20,0)));
				p1.add(FullName);
				FullName.setPreferredSize(new Dimension(300, 20));
				JButton accp=new JButton("Accept");
				JButton rej=new JButton("Reject");
				accp.setForeground(Color.white);
				accp.setBackground(Design.base);
				rej.setForeground(Color.white);
				rej.setBackground(Design.base);
				p1.add(accp);
				p1.add(rej);
				accp.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						account.addFriend(email, em);
						accp.setEnabled(false);
						rej.setEnabled(false);
					}
				});
				rej.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						account.rejectFriend(email, em);
						accp.setEnabled(false);
						rej.setEnabled(false);
					}
				});
				p1.setBackground(Color.WHITE);
				v.add(p1);
			}
			JPanel reqList=new JPanel();
			reqList=new JPanel();
			reqList.setBackground(Color.white);
			reqList.setLayout(new BoxLayout(reqList, BoxLayout.Y_AXIS));
			for(int i=0;i<v.size();i++){
				reqList.add((JPanel)v.elementAt(i));
				reqList.add(new JSeparator(JSeparator.HORIZONTAL));
			}
				return reqList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void addFriend(String emailTo,String emailFrom){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			String query="update demande set state='acceptee' where email_sender='"+emailFrom+"' and email_rec='"+emailTo+"'";
			st.executeUpdate(query);
			String queryadd="insert into freinds values ('"+emailFrom+"','"+emailTo+"')";
			st.executeUpdate(queryadd);
			String queryadd2="insert into freinds values ('"+emailTo+"','"+emailFrom+"')";
			st.executeUpdate(queryadd2);
			JOptionPane.showMessageDialog(null, "Friend added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void rejectFriend(String emailTo,String emailFrom){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			String query="update demande set state='rejetee' where email_sender='"+emailFrom+"' and email_rec='"+emailTo+"'";
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Friend rejected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void updateAccount (String email,String name,String lname,String bdate,String gender){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			String query="update account set name='"+name+"',Last_name='"+lname+"',bdate='"+bdate+"',m_f='"+gender+"' where email='"+email+"'";
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Profile updated successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void updateProfilePic(String email, String path){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			String query="update photo set prof=0 where email='"+email+"'";
			st.executeUpdate(query);
			PreparedStatement pst=c.prepareStatement("insert into photo (photo,prof,email) values(?,?,?)");
			FileInputStream fis=new FileInputStream(new File(path));
			pst.setBlob(1, fis);
			pst.setInt(2, 1);
			pst.setString(3, email);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "new profile picture saved");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	public static String getIp(){
		
		String wln=JOptionPane.showInputDialog("enter your wireless interface name");
		
		String ip="";
		try {
			NetworkInterface nfc=NetworkInterface.getByName(wln);
			Enumeration<InetAddress>e=nfc.getInetAddresses();
			if(e.hasMoreElements()){
				ip=e.nextElement().getHostAddress();
				
			}
			return ip;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public static void setIpTo(String email){
		String ip=JOptionPane.showInputDialog("enter your ip address");
		
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		
		String[]t=new String[4];
		int x=-1,p;
		while(ip.contains(".")){
			x++;
			p=ip.indexOf(".");
			t[x]=ip.substring(0, p);
			ip=ip.substring(p+1);
		}	
		x++;
		t[x]=ip;
		
		byte[]myip=new byte[4];
		myip[0]=(byte)Integer.parseInt(t[0]);
		myip[1]=(byte)Integer.parseInt(t[1]);
		myip[2]=(byte)Integer.parseInt(t[2]);
		myip[3]=(byte)Integer.parseInt(t[3]);
		
		try {
			PreparedStatement pst=c.prepareStatement("update account set Addr=? where email='"+email+"'");
			pst.setBytes(1,myip);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ip added");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void delIp(String email){
		
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		
		byte[]myip=new byte[1];
		
		try {
			PreparedStatement pst=c.prepareStatement("update account set Addr=? where email='"+email+"'");
			pst.setBytes(1,myip);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String getEmailByName(String fname,String lname){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		String em="";
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select email from account where name='"+fname+"' and Last_name='"+lname+"'");
			if(res.next()){
				em=res.getString(1);
			}else{
				JOptionPane.showMessageDialog(null, em);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return em;
	}
	public static byte[] getIp(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		byte[] ip=new byte[4];
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select Addr from account where email='"+email+"'");
			if(res.next()){
				ip=res.getBytes(1);
			}else{
				JOptionPane.showMessageDialog(null, "ip not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	public static boolean isOnline(String email){
		DataBaseConnectClass dbc=new DataBaseConnectClass();
		Connection c=dbc.getConnection();
		try {
			Statement st=c.createStatement();
			ResultSet res=st.executeQuery("select on_off from account where email='"+email+"'");
			if(res.next()){
				if(res.getInt(1)==1){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
	}
	
}







