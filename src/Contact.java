import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Contact extends JFrame {

	private JPanel contentPane;

	public Contact() {
		setTitle("Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 587, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(587, 50));
		panel.setLayout(new GridLayout(1, 0, 2, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				JPanel list=account.getAllListPanel(LogInClass.loggedin);
				panel_1.add(list);
			}
		});
		btnAddContact.setForeground(Color.WHITE);
		panel.add(btnAddContact);
		btnAddContact.setBackground(Design.base);
		
		
		
		
		
		JButton btnRequests = new JButton("Requests");
		btnRequests.setForeground(Color.WHITE);
		int x=account.getRequestNumber(LogInClass.loggedin);
		if(x!=0){
			String text=btnRequests.getText();
			btnRequests.setText(text+" ("+x+")");
		}
		panel.add(btnRequests);
		
		btnRequests.setBackground(Design.base);
		
		btnRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				JPanel list=account.getAllRequestsPanel(LogInClass.loggedin);
				panel_1.add(list);
			}
		});
		
		
	}

}
