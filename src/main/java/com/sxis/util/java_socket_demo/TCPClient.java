package com.sxis.util.java_socket_demo; /**
 *TCPClient
 *@author Winty wintys@gmail.com
 *@version 2008-12-15
 */
import java.io.*;
import java.net.*;

class TCPClient{
	public static void main(String[] args)throws IOException{
		Socket client = new Socket("127.0.0.1" , 5050);

		InputStream in = client.getInputStream();
		OutputStream out = client.getOutputStream();
		
		out.write('c');

		char c = (char)in.read();
		System.out.println("�յ�:" + c);

		out.close();
		in.close();
		client.close();
	}
}
