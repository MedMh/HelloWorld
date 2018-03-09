import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class ProfileClass {
	public ProfileClass(){
		
	}
	public static JPanel getProfile(String email){
		account a=new account();
		ImageIcon imc=a.getProfilePic(email);
		account a1=a.getAccountObj(email);
		JLabel img=new JLabel(imc);
		JLabel nom=new JLabel("Name: ");
		JLabel lnom=new JLabel("Last name: ");
		JLabel mail=new JLabel("email: ");
		JLabel bdate=new JLabel("Birth day: ");
		JLabel gender=new JLabel("Gender: ");
		JLabel joined=new JLabel("Joined: ");
		
		JLabel pnom=new JLabel(a1.getName());
		JLabel plnom=new JLabel(a1.getLname());
		JLabel pmail=new JLabel(a1.getEmail());
		JLabel pbdate=new JLabel(a1.getBdate());
		JLabel pgender=new JLabel(a1.getMf());
		JLabel pjoined=new JLabel(a1.getDateR());
		
		JPanel panel=new JPanel();
		panel.setBackground(Color.WHITE);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		p1.setBackground(Color.white);
		p2.setBackground(Color.WHITE);
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		
		p1.add(img);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(nom);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(lnom);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(mail);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(bdate);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(gender);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(joined);
		
		
		p2.add(Box.createRigidArea(new Dimension(0, 90)));
		p2.add(pnom);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(plnom);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pmail);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pbdate);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pgender);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pjoined);
		
		panel.setLayout(new GridLayout(1, 2));
		panel.add(p1);
		panel.add(p2);
		
		return panel;
		
	}
	public static JPanel getEditPanel(String email){
		
		account a=new account();
		ImageIcon imc=a.getProfilePic(email);
		
		account a1=a.getAccountObj(email);
		JLabel img=new JLabel(imc);
		JLabel nom=new JLabel("Name: ");
		JLabel lnom=new JLabel("Last name: ");
		JLabel bdate=new JLabel("Birth day: ");
		JLabel gender=new JLabel("Gender: ");
		JButton editPic=new JButton("Change picture");
		JButton updateButton=new JButton("Save changes");
		editPic.setForeground(Color.white);
		editPic.setBackground(Design.base);
		updateButton.setForeground(Color.white);
		updateButton.setBackground(Design.base);
		
		
		JTextField pnom=new JTextField(a1.getName());
		JTextField plnom=new JTextField(a1.getLname());
		JTextField pbdate=new JTextField(a1.getBdate());
		JTextField pgender=new JTextField(a1.getMf());
		
		JPanel panel=new JPanel();
		panel.setBackground(Color.WHITE);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		p1.setBackground(Color.white);
		p2.setBackground(Color.WHITE);
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		
		p1.add(img);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(nom);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(lnom);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(bdate);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(gender);
		
		
		
		p2.add(Box.createRigidArea(new Dimension(0, 40)));
		p2.add(editPic);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pnom);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(plnom);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pbdate);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(pgender);
		p2.add(Box.createRigidArea(new Dimension(0, 20)));
		p2.add(updateButton);
		
		editPic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();
				jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
	            jfc.addChoosableFileFilter(filter);
	            int result = jfc.showSaveDialog(null);
	            	if(result == JFileChooser.APPROVE_OPTION){
	            		File selectedFile = jfc.getSelectedFile();
						String path = selectedFile.getAbsolutePath();
						img.setIcon(ResizeImage(path,img));
						account.updateProfilePic(email, path);
						//  s = path;
				     }
				     else if(result == JFileChooser.CANCEL_OPTION){
						 System.out.println("No Data");
			 }
			}
		});
		
		
		panel.setLayout(new GridLayout(1, 2));
		panel.add(p1);
		panel.add(p2);
		return panel;
	}
	
	
	
	public static ImageIcon ResizeImage(String imgPath,JLabel photoLabel){
	    ImageIcon MyImage = new ImageIcon(imgPath);
	    Image img = MyImage.getImage();
	    Image newImage = img.getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(),Image.SCALE_SMOOTH);
	    ImageIcon image = new ImageIcon(newImage);
	    return image;
	    }
	
	
	
}
