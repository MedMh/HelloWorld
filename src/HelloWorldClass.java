import javax.swing.*;
public class HelloWorldClass {
	static String ipserver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ipserver=JOptionPane.showInputDialog("enter the server ip");
		LogInClass log=new LogInClass();
	}
}
