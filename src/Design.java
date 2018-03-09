import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Design {
	static Color base=new Color(6, 152, 255);
	static Font f1=new Font("Bold", Font.BOLD, 25);
	
	public static void focusPassDesign(JTextField t,String s){
		t.setText(s);
		t.setForeground(Color.gray);
		t.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=t.getText();
				if(s1.equals("")){
					t.setText(s);
					t.setForeground(Color.GRAY);
			}
		}
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=t.getText();
				if((s1.equals(""))||(s1.equals(s))){
					t.setText("");
					t.setForeground(Color.black);
				}
			}
		});
		
	}
	
	public static void focusDesign(JTextField t,String s){
		t.setText(s);
		t.setForeground(Color.gray);
		t.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=t.getText();
				if(s1.equals("")){
					t.setText(s);
					t.setForeground(Color.GRAY);
			}
		}
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=t.getText();
				if((s1.equals(""))||(s1.equals(s))){
					t.setText("");
					t.setForeground(Color.black);
				}
			}
		});
		
	}
	
	public static void focusDesigntextArea(JTextArea ta,String s){
		ta.setText(s);
		ta.setForeground(Color.gray);
		ta.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=ta.getText();
				if(s1.equals("")){
					ta.setText(s);
					ta.setForeground(Color.GRAY);
			}
		}
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String s1=ta.getText();
				if((s1.equals(""))||(s1.equals(s))){
					ta.setText("");
					ta.setForeground(Color.black);
				}
			}
		});
		
	}
	public static String removePar(String s){
		String s1=s;
		if(s.contains("(")){
			int x=s.length()-s.indexOf("(");
			s1=s.substring(0, x);
		}
		return s1;
	}
}

