package com.server;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.tools.Message;
import com.tools.MySQLTool;
import com.tools.SocketInfo;

/**
 * @author lxd
 */
public class ServerFrame implements ActionListener {
    private JComboBox<String> chooseUser;
    private JButton serStart, serClose;
    private static JTextArea chatArea;
    private JTextArea sendMessageArea;
    private JButton sendButton;


    /**
     *  serverSocket
     *  �������������׽���ServerSocket
     */
    private ServerSocket serverSocket;

    /**
     *  serverSocket
     *  ������Ϣ������
     */
    private LinkedList<Message> messageList = new LinkedList<Message>();

    /**
     *  serverSocket
     *  SocketInfo������
     */
    private LinkedList<SocketInfo> socketInfoList = new LinkedList<SocketInfo>();


    ServerFrame() {
        // serFrame����
        JFrame serFrame = new JFrame("΢�����ҡ�������");
        JLabel imgLabel = new JLabel(new ImageIcon("image/ser����ͼƬ.jpg"));
        serStart = new JButton("����������");
        serClose = new JButton("�رշ�����");

        chatArea = new JTextArea();
        sendMessageArea = new JTextArea();
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        JScrollPane sendScrollPane = new JScrollPane(sendMessageArea);
        chooseUser = new JComboBox<>();
        sendButton = new JButton("����");
        //sendButton�Ŀ�ݼ���ALT+ENTER
        sendButton.setMnemonic(KeyEvent.VK_ENTER);
        serFrame.setLayout(null);

        serFrame.setSize(800, 600);
        imgLabel.setBounds(0, 0, 800, 600);
        serStart.setBounds(25, 5, 130, 30);
        serClose.setBounds(180, 5, 130, 30);

        chatScrollPane.setBounds(10, 40, 770, 400);
        sendScrollPane.setBounds(10, 460, 600, 90);
        chooseUser.setBounds(622, 463, 160, 30);
        sendButton.setBounds(660, 510, 75, 35);

        serStart.setFont(new Font("����", Font.PLAIN, 18));
        serClose.setFont(new Font("����", Font.PLAIN, 18));

        chatArea.setFont(new Font("����", Font.PLAIN, 20));
        sendMessageArea.setFont(new Font("����", Font.PLAIN, 20));
        sendButton.setFont(new Font("����", Font.PLAIN, 19));

        serStart.setFocusable(false);
        serClose.setFocusable(false);

        sendButton.setFocusable(false);
        // ��Ϊ���ɱ༭
        chatArea.setEditable(false);
        // ��Ϊ͸��
        chatArea.setOpaque(false);
        // �Զ�����
        chatArea.setLineWrap(true);
        // �Զ�����
        sendMessageArea.setLineWrap(true);
        chooseUser.addItem("������");

        serFrame.add(serStart);
        serFrame.add(serClose);

        serFrame.add(chatScrollPane);
        serFrame.add(sendScrollPane);
        serFrame.add(chooseUser);
        serFrame.add(sendButton);
        serFrame.add(imgLabel);

        serFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ͼ��.png"));
        serFrame.setVisible(true);
        // ����Ϊ��Ļ������
        serFrame.setLocationRelativeTo(null);
        // ���ɵ�����С
        serFrame.setResizable(false);
        serFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // �¼�ע��
        serStart.addActionListener(this);
        serClose.addActionListener(this);

        sendButton.addActionListener(this);
    }

    public void writeLog(String  msg){

        try{
            File f = new File("log.txt");
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f,true),"gbk");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write( msg);
            writer.close();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //���������̵߳Ŀ���
    private AcceptSocketThread acceptSocketThread = null;
    private SendMsgToClientThread sendMsgToClientThread = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == serStart) {
            try {
                // �������������׽���ServerSocket,��28888�˿ڼ���
                serverSocket = new ServerSocket(28888);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // �������ܿͻ���Socket���߳�ʵ����������
            acceptSocketThread = new AcceptSocketThread();
            acceptSocketThread.start();
            // �������ͻ��˷�����Ϣ���߳�ʵ����������
            sendMsgToClientThread = new SendMsgToClientThread();
            sendMsgToClientThread.start();
            chatArea.append("--------�������ѿ���--------\n");
            writeLog("--------�������ѿ���--------");



            // �������serStart���޷�ʹ��
            serStart.setEnabled(false);
            // �������serClose�����ʹ��
            serClose.setEnabled(true);
        }

        if (e.getSource() == serClose) {
            if (acceptSocketThread != null && acceptSocketThread.isAlive()) {
                acceptSocketThread.exit = true;
            }
            if (sendMsgToClientThread != null && sendMsgToClientThread.isAlive()) {
                sendMsgToClientThread.exit = true;
            }

            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            chatArea.append("--------�������ѹر�--------\n");
            writeLog("\"--------�������ѹر�--------");

            // �������serClose���޷�ʹ��
            serClose.setEnabled(false);
            // �������serStart�����ʹ��
            serStart.setEnabled(true);
        }

        if (e.getSource() == sendButton) {
            String strMsg = sendMessageArea.getText();
            // ��ȡ��ǰϵͳʱ�䣬��ʹ�����ڸ�ʽ���໯Ϊָ����ʽ���ַ���
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = "<==" + dateFormat.format(new Date()) + "==>\n";
            if (strMsg != null) {
                // ����һ����Ϣ��Ķ���
                Message message = new Message("ϵͳ", true, strMsg, new Date());
                messageList.addFirst(message);
                // ����Ϣ�洢�����ݿ���
                new MySQLTool().insert(message);
                chatArea.append(time + "��ϵͳ���ԡ�" + chooseUser.getSelectedItem() + "��˵��" + strMsg + "\n");
            }
            sendMessageArea.setText(null);
        }

    }

    /**
     *
     * ���տͻ���Socket�׽����߳�
     *
     */
    class AcceptSocketThread extends Thread {
        volatile boolean exit = false;

        @Override
        public void run() {
            while (!exit) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("====================================");
                    System.out.println("==============�пͻ�������============");
                    System.out.println("������ϢΪ��IP:" + socket.getInetAddress() + "   �˿�:" + socket.getPort());
                    writeLog("==============�пͻ�������============");
                    writeLog("������ϢΪ��IP:" + socket.getInetAddress() + "   �˿�:" + socket.getPort());
                    if (socket.isConnected()) {
                        System.out.println("socket������");
                        GetMsgFromClientThread getMsgFromClientThread = new GetMsgFromClientThread(socket);
                        System.out.println("����������Ϣ�̣߳�");
                        System.out.println("\t�̺߳�Ϊ��" + getMsgFromClientThread.getId());
                        writeLog("socket������  "+"����������Ϣ�̣߳�\n"+"\t�̺߳�Ϊ��" + getMsgFromClientThread.getId());
                        getMsgFromClientThread.start();
                    }

                } catch (SocketException e) {
                    System.out.println("serverSocket�ѹر�");
                    writeLog("serverSocket�ѹر�");
                    if (exit && serverSocket.isClosed()) {
                        break;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            System.out.println("����˼����߳����˳�");
            writeLog("����˼����߳����˳�");

        }
    }


    /**
     *
     * ���տͻ��˵�������Ϣ���߳�
     *
     */
    class GetMsgFromClientThread extends Thread {
        volatile boolean exit = false;
        Socket clientSocket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        String sender;
        Message message;
        SocketInfo socketInfo;

        /**
         * @param socket
         */
        GetMsgFromClientThread(Socket socket) {
            // TODO Auto-generated constructor stub
            this.clientSocket = socket;
            try {
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                message = ((Message) ois.readObject());
                if (message.getMsgType() == 2) {
                    System.out.println("���յ��ͻ��˵�һ�η��͵���������");
                    writeLog("���յ��ͻ��˵�һ�η��͵���������");
                    sender = message.getSender();
                    socketInfo = new SocketInfo(socket, oos, sender);
                    if(sender.equals("TEACHER")){
                        socketInfoList.addFirst(socketInfo);
                    }else{
                        socketInfoList.add(socketInfo);
                    }

                }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            while (!exit) {
                try {
                    Object obj = ois.readObject();
                    if (obj != null) {
                        Message message = (Message) obj;// �ѽ��յ��Ķ���ת��Ϊmessage
                        //����ͨ��������Ϣ,����ʦ�Ŀ�����Ϣ
                        if (message.getMsgType() == 1) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            // �����ܵ���Ϣ�ڷ������ʾ
                            chatArea.append("<==" + dateFormat.format(message.getTime()) + "==>\n" + "��" + message.getSender() + "��˵��"
                                    + message.getMsg() + "\n");
                            // ����Ϣ�洢�����ݿ���
                            new MySQLTool().insert(message);
                            // Ȼ�����Ϣ���뵽��Ϣ�б���
                            messageList.addFirst(message);
                        }
                        if (message.getMsgType() == 3 ||message.getMsgType() == 4) {
                            System.out.println("��ʦ����������Ϣ");
                            writeLog("��ʦ����������Ϣ");
                            // ����Ϣ�洢�����ݿ���
                            //new MySQLTool().insert(message);
                            // Ȼ�����Ϣ���뵽��Ϣ�б���
                            messageList.addFirst(message);
                        }

                        //�յ��˿������������Ϣ
                        if (message.getMsgType() == -1) {
                            Message returnMsg=new Message();
                            returnMsg.setMsgType(5);
                            socketInfo.oos.writeObject( returnMsg);
                            socketInfo.oos.flush();
                            socketInfoList.remove(socketInfo);

                        }
                    }
                }catch (SocketException se){
                    System.out.println("socket�ѹر�");
                    break;
                } catch (IOException | ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } // TODO: handle exception

            }
        }

    }


    /**
     *
     * ���ͻ��˷���������Ϣ���߳�
     *
     */
    class SendMsgToClientThread extends Thread {
        volatile boolean exit = false;

        @Override
        public void run() {
            while (!exit) {
                try {
                    //�����Ϣ�����ϲ��գ�����������Ϣδ���ͣ�
                    if (!messageList.isEmpty()) {
                        //ȡ��Ϣ�������ж����һ�������Ƴ�
                        Message message = messageList.removeLast();

                        if(message.getAcceptor()){//��������Ϣ
                            for (SocketInfo socketInfo : socketInfoList) {
                                socketInfo.oos.writeObject(message);
                                socketInfo.oos.flush();
                            }
                        }else{
                            //˽�������͸���ʦ,ͬʱ�Լ�ҲӦ���յ�
                            for (SocketInfo socketInfo : socketInfoList) {
                                if (socketInfo.userInfo.equals(message.getSender())) {
                                    socketInfo.oos.writeObject(message);
                                    socketInfo.oos.flush();
                                    break;
                                }
                            }
                            socketInfoList.get(0).oos.writeObject(message);
                            socketInfoList.get(0).oos.flush();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("������Ϣʱ��������쳣��");
                    writeLog("������Ϣʱ��������쳣��");
                    // TODO: handle exception
                }
            }
            //�������ر�
            for (SocketInfo socketInfo : socketInfoList) {
                try {
                    socketInfo.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


















































