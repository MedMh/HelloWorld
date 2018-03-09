import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmeClass extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JLabel lblEnterTheCode;
	public ConfirmeClass(String email) {
		setTitle("Confirmation");
		setBounds(100, 100, 478, 204);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 153, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAConfirmationCode = new JLabel("A confirmation code has been sent to your email!");
		lblAConfirmationCode.setFont(new Font("Arial", Font.BOLD, 18));
		lblAConfirmationCode.setForeground(new Color(0, 153, 255));
		lblAConfirmationCode.setBackground(new Color(255, 255, 255));
		lblAConfirmationCode.setBounds(10, 11, 440, 27);
		contentPane.add(lblAConfirmationCode);
		
		t1 = new JTextField();
		t1.setBounds(187, 73, 221, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		lblEnterTheCode = new JLabel("Enter the code here:");
		lblEnterTheCode.setForeground(new Color(0, 153, 255));
		lblEnterTheCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterTheCode.setBounds(10, 76, 147, 14);
		contentPane.add(lblEnterTheCode);
		
		JButton b1 = new JButton("Confirme");
		b1.setForeground(new Color(255, 255, 255));
		b1.setBackground(new Color(51, 153, 255));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account acc=new account();
				int code=acc.getCode(email);
				String s=t1.getText();
				int c=Integer.parseInt(s);
				if(code==c){
					JOptionPane.showMessageDialog(null, "Your account has been confirmed!");
					acc.setConfirmed(email);
					setVisible(false);
					new Home();
					
				}else{
					JOptionPane.showMessageDialog(null, "wrong code");
				}
			}
		});
		b1.setBounds(172, 132, 89, 23);
		contentPane.add(b1);
		b1.setBackground(Design.base);
		setVisible(true);
	}
}
