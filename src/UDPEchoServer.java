

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class ServerTask extends Thread {
	private DatagramSocket serverSocket;
	private final int portNumber;

	public ServerTask() throws SocketException {
		portNumber = 1234;
		serverSocket = new DatagramSocket(portNumber);

	}
	
	@Override
	public void run() {
		while (true){
			byte[] byteBuffer = new byte[256];
			DatagramPacket packet = new DatagramPacket(byteBuffer, byteBuffer.length);
			try {
				serverSocket.receive(packet);
				System.out.printf("Server received: %s\n", new String(byteBuffer));
				InetAddress clientAddress = packet.getAddress();
				DatagramPacket packet2 = new DatagramPacket("Hi client".getBytes(),
						"Hi client".getBytes().length,
						clientAddress,
						packet.getPort());
				serverSocket.send(packet2);
				
				
				
			} catch (IOException e) {
				System.err.println("IOException!");
				System.exit(1);
			}
		}
		
	}

}

public class UDPEchoServer {
	public static void main(String[] args) throws SocketException{
		ServerTask myServerTask = new ServerTask();
		myServerTask.start();
	}

}
