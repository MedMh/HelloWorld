import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class AcceptDataClass extends Thread{
	public AcceptDataClass(){
		
	}
	public void run(){
		try {
			DatagramSocket ds=new DatagramSocket(3000);
			String msg;
			byte[]buffer=new byte[1000];
			DatagramPacket dp=new DatagramPacket(buffer, buffer.length);
			while(true){
				
				ds.receive(dp);
				msg=new String(dp.getData(),0,dp.getLength());
				//Messages.panel_2.removeAll();
				//Messages.panel_2.add(Messages.loadMessage(LogInClass.loggedin, Messages.emailfr));
				Messages.panel_2.add(Messages.displayMessage(msg));
				
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
