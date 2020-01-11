package com.client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import com.tools.Message;

public class StudentFrame implements ActionListener {
   // private String ip="192.168.43.143";
   	private String ip="127.0.0.1";
	private JLabel selfInfoJLabel, imgLabel;
	private JTextArea chatArea, sendMessageArea;
	private JButton sendButton;
	private JRadioButton sendToEveryone,sendToTeacher;
	private ButtonGroup sendWho;
	private JFrame chatRoomFrame;
	private String selfInfo;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;


	public StudentFrame() {


		chatRoomFrame = new JFrame("微聊天室");
		chatRoomFrame.setLayout(null);
		boolean selfInfoRight=false;
		while(!selfInfoRight){
			selfInfo=JOptionPane.showInputDialog(chatRoomFrame,"输入自己的姓名，输入后不可更改，请谨慎输入","请输入姓名",JOptionPane.PLAIN_MESSAGE);
			System.out.println(selfInfo);
			if(selfInfo.length()==0){
				JOptionPane.showMessageDialog(chatRoomFrame,"姓名不能为空","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(selfInfo.length()>10){
				JOptionPane.showMessageDialog(chatRoomFrame,"姓名长度不能大于10个字符","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(selfInfo.equals("TEACHER")){
				JOptionPane.showMessageDialog(chatRoomFrame,"不允许将“TEACHER”设为学生名","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
				selfInfoRight = true;
			}
		}

		if(selfInfo.isEmpty()){
			System.exit(-1);
		}
		selfInfoJLabel = new JLabel("你好，" + selfInfo ,
				SwingConstants.CENTER);
		imgLabel = new JLabel(new ImageIcon("image/ser背景图片.jpg"));
		chatArea = new JTextArea();
		sendMessageArea = new JTextArea();
		JScrollPane sendJS = new JScrollPane(sendMessageArea);
		JScrollPane chatJS = new JScrollPane(chatArea);

		sendButton = new JButton("发送");
		//sendButton的快捷键，ALT+ENTER
		sendButton.setMnemonic(KeyEvent.VK_ENTER);
		sendWho=new ButtonGroup();
		sendToEveryone=new JRadioButton("公开");
		sendToTeacher=new JRadioButton("私发");
		sendWho.add(sendToEveryone);
		sendWho.add(sendToTeacher);
		sendToEveryone.setSelected(true);




		chatRoomFrame.setSize(800, 600);
		selfInfoJLabel.setBounds(0, 0, 800, 50);
		imgLabel.setBounds(0, 0, 800, 600);
		chatJS.setBounds(10, 60, 770, 385);
		sendJS.setBounds(10, 460, 600, 90);

		sendButton.setBounds(660, 510, 75, 35);
		sendToEveryone.setBounds(630, 470, 65, 20);
		sendToTeacher.setBounds(715, 470, 65, 20);


		selfInfoJLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		selfInfoJLabel.setBackground(new Color(135, 206, 235));
		chatArea.setFont(new Font("宋体", Font.PLAIN, 20));
		sendMessageArea.setFont(new Font("宋体", Font.PLAIN, 20));
		sendButton.setFont(new Font("宋体", Font.PLAIN, 20));
		sendToTeacher.setFont(new Font("宋体", Font.PLAIN, 18));
		sendToEveryone.setFont(new Font("宋体", Font.PLAIN, 18));

		chatRoomFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/图标.jpg"));
		selfInfoJLabel.setOpaque(true);// 设为不透明
		chatArea.setEditable(false);// 设为不可编辑
		chatArea.setOpaque(false);// 设为透明
		chatArea.setLineWrap(true);// 自动换行
		sendMessageArea.setLineWrap(true);// 自动换行
		sendToEveryone.setFocusable(false);
		sendToTeacher.setFocusable(false);
		sendToEveryone.setOpaque(false);
		sendToTeacher.setOpaque(false);
		sendButton.setFocusable(false);


		chatRoomFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/图标.png"));



		chatRoomFrame.add(selfInfoJLabel);

		chatRoomFrame.add(chatJS);
		chatRoomFrame.add(sendJS);
		chatRoomFrame.add(sendToEveryone);
		chatRoomFrame.add(sendToTeacher);
		chatRoomFrame.add(sendButton);
		chatRoomFrame.add(imgLabel);

		// 设置为屏幕正中央
		chatRoomFrame.setLocationRelativeTo(null);
		// 不可调整大小
		chatRoomFrame.setResizable(false);
		// 窗体可见
		chatRoomFrame.setVisible(true);

		// 事件监听的注册
		sendButton.addActionListener(this);

       // ip=JOptionPane.showInputDialog(chatRoomFrame,"请输入服务器IP","IP设置",JOptionPane.PLAIN_MESSAGE);
		while(socket==null){
			try {
				// 创建一个套接字
				socket = new Socket(ip, 28888);
				//socket = new Socket("192.168.1.154", 28888);
				// 创建一个往套接字中写数据的管道，即输出流，给服务器发送信息
				oos = new ObjectOutputStream(socket.getOutputStream());
				// 创建一个从套接字中读数据的管道，即输入流，读服务器的返回信息
				ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Message message=new Message();
				message.setMsgType(2);
				message.setSender(selfInfo);
				oos.writeObject(message);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				chatArea.append("<=="+df.format(new Date())+"==>\n"+"服务器已开启\n");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ConnectException ce) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				chatArea.append("<=="+df.format(new Date())+"==>\n"+"服务器未开启\n");

				System.out.println("服务器未开启");
			}
			catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		GetMsgFromServer getMsgFromServer = new GetMsgFromServer();
		// 启动线程
		if(socket!=null&&socket.isConnected()){
			getMsgFromServer.start();
		}


		// 窗口关闭时的处理
		chatRoomFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(socket!=null&&socket.isConnected()){
					try {
						//发送最后的断连请求
						Message message=new Message();
						message.setMsgType(-1);
						oos.writeObject(message);
						oos.flush();
						//退出接受信息的线程
						getMsgFromServer.exit=true;
						if(!getMsgFromServer.isAlive()){
							socket.close();
							oos.close();
							ois.close();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sendButton) {
			String strMsg = sendMessageArea.getText();
			if(!strMsg.isEmpty()){
				if(strMsg.length()>200){
					JOptionPane.showMessageDialog(chatRoomFrame,"单条信息长度不能大于200字符","ERROR",JOptionPane.ERROR_MESSAGE);
				}else{
					Message message=new Message();
					Date sendTime=new Date();
					message.setMsgType(1);
					message.setSender(selfInfo);
					message.setMsg(strMsg);
					message.setTime(sendTime);

					if(sendToTeacher.isSelected()){
						message.setAcceptor(false);
					}
					if(sendToEveryone.isSelected()){
						message.setAcceptor(true);
					}
					// 通过输出流将数据发送给服务器
					try {
						oos.writeObject(message);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 清空文本框
					sendMessageArea.setText("");
				}
			}
		}
	}

	/**
	 * 继承了基本的线程类
	 * 接受服务器的返回信息的线程
	 *
	 */
	class GetMsgFromServer extends Thread {
		public volatile boolean exit = false;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!exit&&socket.isConnected()){// flag为true时表示客户端还在运行；
				try {
					Object obj = ois.readObject();
					if (obj != null) {
						Message message = (Message) obj;// 把接收到的对象转化为message
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
						if(message.getMsgType()==1){
							chatArea.append("<=="+df.format(new Date())+"==>\n");
							chatArea.append("【" + message.getSender() + "】说："
									+ message.getMsg() + "\n");
						}
						if(message.getMsgType()==3){
							sendButton.setEnabled(false);
							chatArea.append("<=="+df.format(new Date())+"==>\n");
							chatArea.append("【系统提示】教师已关闭发言功能\n");
						}
						if(message.getMsgType()==4){
							sendButton.setEnabled(true);
							chatArea.append("<=="+df.format(new Date())+"==>\n");
							chatArea.append("【系统提示】教师已打开发言功能\n");
						}
					}
				}
				catch (SocketException e){
					//e.printStackTrace();
					System.out.println("服务端停止服务");
					break;
				}catch (EOFException e){
					e.printStackTrace();
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
