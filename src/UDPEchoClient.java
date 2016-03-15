

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {
	public static void main(String[] args) throws SocketException{
		DatagramSocket socket = new DatagramSocket();
		SocketAddress ss  = socket.getLocalSocketAddress();
		System.out.println(ss);
		try {
			DatagramPacket packet = new DatagramPacket(
					"Hello server".getBytes(), 
					"Hello server".getBytes().length, 
					InetAddress.getByName("127.0.0.1"), 
					1234);
			socket.send(packet);
			byte[] byteArray = new byte[256];
			DatagramPacket packet2 = new DatagramPacket(byteArray, byteArray.length);
			socket.receive(packet2);
			String myString = new String(byteArray);
			System.out.println("Client received: " + myString);
		} catch (UnknownHostException e) {
			System.err.println("UnknownHostException!");
			System.exit(1);
		} catch (IOException e){
			System.err.println("IOException!");
			System.exit(1);
		}
		socket.close();
	}

}
