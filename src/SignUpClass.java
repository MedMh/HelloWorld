import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class SignUpClass extends JFrame{
	JLabel lab=new JLabel("Sign up");
	JLabel birth=new JLabel("Birthday:");
	JTextField name=new JTextField();
	JTextField lname=new JTextField();
	JTextField email=new JTextField();
	JTextField cemail=new JTextField();
	JPasswordField pwd=new JPasswordField();
	JRadioButton male=new JRadioButton("Male");
	JRadioButton fem=new JRadioButton("Female");
	String[] tday=new String[31];
	String[] tmonth=new String[12];
	String[] tyear=new String[30];
	JComboBox day;
	JComboBox month;
	JComboBox year;
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JButton reg=new JButton("Sign up");
	JButton cancel=new JButton("Cancel");
	public SignUpClass(){
		setTitle("Sign up");
		setVisible(true);
		setBounds(100,50,470,520);
		Container c=getContentPane();
		c.add(p1);
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setBackground(Color.WHITE);
		p1.add(p);
		p.setLayout(null);
		int i;
		for(i=0;i<31;i++){
			tday[i]=String.valueOf(i+1);
		}
		for(i=0;i<12;i++){
			tmonth[i]=String.valueOf(i+1);
		}
		for(i=0;i<30;i++){
			tyear[i]=String.valueOf(i+1990);
		}
		
		day=new JComboBox(tday);
		month=new JComboBox(tmonth);
		year=new JComboBox(tyear);
		
		lab.setBounds(10, 10, 200, 30);
		lab.setFont(Design.f1);
		lab.setForeground(Design.base);
		p.add(lab);
		
		name.setBounds(10,50,200,25);
		p.add(name);
		Design.focusDesign(name, "name...");
		
		lname.setBounds(230,50,200,25);
		p.add(lname);
		Design.focusDesign(lname, "Last name...");
		
		email.setBounds(10, 100, 420, 25);
		p.add(email);
		Design.focusDesign(email, "email...");
		
		cemail.setBounds(10, 150, 420, 25);
		p.add(cemail);
		Design.focusDesign(cemail, "confirme your email...");
		
		pwd.setBounds(10, 200, 420, 25);
		p.add(pwd);
		Design.focusPassDesign(pwd, "password...");
		
		birth.setBounds(10, 250, 100, 30);
		p.add(birth);
		
		day.setBounds(10,300,50,30);
		p.add(day);
		
		month.setBounds(80,300,50,30);
		p.add(month);
		
		year.setBounds(150,300,70,30);
		p.add(year);
		
		ButtonGroup g=new ButtonGroup();
		g.add(male);
		g.add(fem);
		
		male.setBounds(10, 350, 70, 30);
		p.add(male);
		male.setBackground(Color.white);
		fem.setBounds(100, 350, 70, 30);
		p.add(fem);
		fem.setBackground(Color.white);
		
		reg.setBounds(10, 400, 100, 30);
		reg.setForeground(Color.WHITE);
		reg.setBackground(Design.base);
		reg.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		p.add(reg);
		
		reg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String myemail=email.getText();
				String myname=name.getText();
				String myLname=lname.getText();
				String myBdate=String.valueOf(year.getSelectedItem())+"-"+String.valueOf(month.getSelectedItem())+"-"+String.valueOf(day.getSelectedItem());
				String mymf;
				String mypwd=pwd.getText();
				if(male.isSelected()){
					mymf="male";
				}else{
					mymf="female";
				}
				account ac=new account(myemail, myname, myLname, myBdate, mymf, mypwd);
				ac.addAccount();
			}
		});
		
		cancel.setBounds(130, 400, 100, 30);
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Design.base);
		cancel.setBorder(BorderFactory.createEtchedBorder(Design.base,Design.base));
		p.add(cancel);
	}
}
