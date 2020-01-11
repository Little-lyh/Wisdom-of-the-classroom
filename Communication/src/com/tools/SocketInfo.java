package com.tools;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketInfo {
	public Socket socket;
	public String userInfo;
	public ObjectOutputStream oos;

	public SocketInfo(Socket socket, ObjectOutputStream oos,String userInfo) {
		this.socket = socket;
		this.oos=oos;
		this.userInfo = userInfo;
	}

}
