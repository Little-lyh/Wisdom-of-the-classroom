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


		chatRoomFrame = new JFrame("΢������");
		chatRoomFrame.setLayout(null);
		boolean selfInfoRight=false;
		while(!selfInfoRight){
			selfInfo=JOptionPane.showInputDialog(chatRoomFrame,"�����Լ�������������󲻿ɸ��ģ����������","����������",JOptionPane.PLAIN_MESSAGE);
			System.out.println(selfInfo);
			if(selfInfo.length()==0){
				JOptionPane.showMessageDialog(chatRoomFrame,"��������Ϊ��","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(selfInfo.length()>10){
				JOptionPane.showMessageDialog(chatRoomFrame,"�������Ȳ��ܴ���10���ַ�","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(selfInfo.equals("TEACHER")){
				JOptionPane.showMessageDialog(chatRoomFrame,"��������TEACHER����Ϊѧ����","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
				selfInfoRight = true;
			}
		}

		if(selfInfo.isEmpty()){
			System.exit(-1);
		}
		selfInfoJLabel = new JLabel("��ã�" + selfInfo ,
				SwingConstants.CENTER);
		imgLabel = new JLabel(new ImageIcon("image/ser����ͼƬ.jpg"));
		chatArea = new JTextArea();
		sendMessageArea = new JTextArea();
		JScrollPane sendJS = new JScrollPane(sendMessageArea);
		JScrollPane chatJS = new JScrollPane(chatArea);

		sendButton = new JButton("����");
		//sendButton�Ŀ�ݼ���ALT+ENTER
		sendButton.setMnemonic(KeyEvent.VK_ENTER);
		sendWho=new ButtonGroup();
		sendToEveryone=new JRadioButton("����");
		sendToTeacher=new JRadioButton("˽��");
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


		selfInfoJLabel.setFont(new Font("����", Font.PLAIN, 18));
		selfInfoJLabel.setBackground(new Color(135, 206, 235));
		chatArea.setFont(new Font("����", Font.PLAIN, 20));
		sendMessageArea.setFont(new Font("����", Font.PLAIN, 20));
		sendButton.setFont(new Font("����", Font.PLAIN, 20));
		sendToTeacher.setFont(new Font("����", Font.PLAIN, 18));
		sendToEveryone.setFont(new Font("����", Font.PLAIN, 18));

		chatRoomFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ͼ��.jpg"));
		selfInfoJLabel.setOpaque(true);// ��Ϊ��͸��
		chatArea.setEditable(false);// ��Ϊ���ɱ༭
		chatArea.setOpaque(false);// ��Ϊ͸��
		chatArea.setLineWrap(true);// �Զ�����
		sendMessageArea.setLineWrap(true);// �Զ�����
		sendToEveryone.setFocusable(false);
		sendToTeacher.setFocusable(false);
		sendToEveryone.setOpaque(false);
		sendToTeacher.setOpaque(false);
		sendButton.setFocusable(false);


		chatRoomFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ͼ��.png"));



		chatRoomFrame.add(selfInfoJLabel);

		chatRoomFrame.add(chatJS);
		chatRoomFrame.add(sendJS);
		chatRoomFrame.add(sendToEveryone);
		chatRoomFrame.add(sendToTeacher);
		chatRoomFrame.add(sendButton);
		chatRoomFrame.add(imgLabel);

		// ����Ϊ��Ļ������
		chatRoomFrame.setLocationRelativeTo(null);
		// ���ɵ�����С
		chatRoomFrame.setResizable(false);
		// ����ɼ�
		chatRoomFrame.setVisible(true);

		// �¼�������ע��
		sendButton.addActionListener(this);

       // ip=JOptionPane.showInputDialog(chatRoomFrame,"�����������IP","IP����",JOptionPane.PLAIN_MESSAGE);
		while(socket==null){
			try {
				// ����һ���׽���
				socket = new Socket(ip, 28888);
				//socket = new Socket("192.168.1.154", 28888);
				// ����һ�����׽�����д���ݵĹܵ��������������������������Ϣ
				oos = new ObjectOutputStream(socket.getOutputStream());
				// ����һ�����׽����ж����ݵĹܵ����������������������ķ�����Ϣ
				ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Message message=new Message();
				message.setMsgType(2);
				message.setSender(selfInfo);
				oos.writeObject(message);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				chatArea.append("<=="+df.format(new Date())+"==>\n"+"�������ѿ���\n");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ConnectException ce) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				chatArea.append("<=="+df.format(new Date())+"==>\n"+"������δ����\n");

				System.out.println("������δ����");
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
		// �����߳�
		if(socket!=null&&socket.isConnected()){
			getMsgFromServer.start();
		}


		// ���ڹر�ʱ�Ĵ���
		chatRoomFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(socket!=null&&socket.isConnected()){
					try {
						//�������Ķ�������
						Message message=new Message();
						message.setMsgType(-1);
						oos.writeObject(message);
						oos.flush();
						//�˳�������Ϣ���߳�
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
					JOptionPane.showMessageDialog(chatRoomFrame,"������Ϣ���Ȳ��ܴ���200�ַ�","ERROR",JOptionPane.ERROR_MESSAGE);
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
					// ͨ������������ݷ��͸�������
					try {
						oos.writeObject(message);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// ����ı���
					sendMessageArea.setText("");
				}
			}
		}
	}

	/**
	 * �̳��˻������߳���
	 * ���ܷ������ķ�����Ϣ���߳�
	 *
	 */
	class GetMsgFromServer extends Thread {
		public volatile boolean exit = false;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!exit&&socket.isConnected()){// flagΪtrueʱ��ʾ�ͻ��˻������У�
				try {
					Object obj = ois.readObject();
					if (obj != null) {
						Message message = (Message) obj;// �ѽ��յ��Ķ���ת��Ϊmessage
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
						//System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
						if(message.getMsgType()==1){
							chatArea.append("<=="+df.format(new Date())+"==>\n");
							chatArea.append("��" + message.getSender() + "��˵��"
									+ message.getMsg() + "\n");
						}
						if(message.getMsgType()==3){
							sendButton.setEnabled(false);
							chatArea.append("<=="+df.format(new Date())+"==>\n");
							chatArea.append("��ϵͳ��ʾ����ʦ�ѹرշ��Թ���\n");
						}
						if(message.getMsgType()==4){
							sendButton.setEnabled(true);
							chatArea.append("<=="+df.format(new Date())+"==>\n");
							chatArea.append("��ϵͳ��ʾ����ʦ�Ѵ򿪷��Թ���\n");
						}
					}
				}
				catch (SocketException e){
					//e.printStackTrace();
					System.out.println("�����ֹͣ����");
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
