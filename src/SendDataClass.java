import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendDataClass extends Thread{
	InetAddress addr;
	String msg="";
	public SendDataClass(byte[]ip, String message){
		try {
			addr=InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg=message;
	}
	public void run(){
		try {
			DatagramSocket ds=new DatagramSocket();
			byte[]ms=msg.getBytes();
			DatagramPacket dp=new DatagramPacket(ms, ms.length, addr, 3000);
			ds.send(dp);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
