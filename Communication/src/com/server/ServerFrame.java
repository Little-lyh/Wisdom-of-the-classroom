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
     *  声明服务器端套接字ServerSocket
     */
    private ServerSocket serverSocket;

    /**
     *  serverSocket
     *  聊天信息链表集合
     */
    private LinkedList<Message> messageList = new LinkedList<Message>();

    /**
     *  serverSocket
     *  SocketInfo链表集合
     */
    private LinkedList<SocketInfo> socketInfoList = new LinkedList<SocketInfo>();


    ServerFrame() {
        // serFrame设置
        JFrame serFrame = new JFrame("微聊天室—服务器");
        JLabel imgLabel = new JLabel(new ImageIcon("image/ser背景图片.jpg"));
        serStart = new JButton("启动服务器");
        serClose = new JButton("关闭服务器");

        chatArea = new JTextArea();
        sendMessageArea = new JTextArea();
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        JScrollPane sendScrollPane = new JScrollPane(sendMessageArea);
        chooseUser = new JComboBox<>();
        sendButton = new JButton("发送");
        //sendButton的快捷键，ALT+ENTER
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

        serStart.setFont(new Font("宋体", Font.PLAIN, 18));
        serClose.setFont(new Font("宋体", Font.PLAIN, 18));

        chatArea.setFont(new Font("宋体", Font.PLAIN, 20));
        sendMessageArea.setFont(new Font("宋体", Font.PLAIN, 20));
        sendButton.setFont(new Font("宋体", Font.PLAIN, 19));

        serStart.setFocusable(false);
        serClose.setFocusable(false);

        sendButton.setFocusable(false);
        // 设为不可编辑
        chatArea.setEditable(false);
        // 设为透明
        chatArea.setOpaque(false);
        // 自动换行
        chatArea.setLineWrap(true);
        // 自动换行
        sendMessageArea.setLineWrap(true);
        chooseUser.addItem("所有人");

        serFrame.add(serStart);
        serFrame.add(serClose);

        serFrame.add(chatScrollPane);
        serFrame.add(sendScrollPane);
        serFrame.add(chooseUser);
        serFrame.add(sendButton);
        serFrame.add(imgLabel);

        serFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/图标.png"));
        serFrame.setVisible(true);
        // 设置为屏幕正中央
        serFrame.setLocationRelativeTo(null);
        // 不可调整大小
        serFrame.setResizable(false);
        serFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 事件注册
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
    //用于下面线程的控制
    private AcceptSocketThread acceptSocketThread = null;
    private SendMsgToClientThread sendMsgToClientThread = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == serStart) {
            try {
                // 创建服务器端套接字ServerSocket,在28888端口监听
                serverSocket = new ServerSocket(28888);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // 创建接受客户端Socket的线程实例，并启动
            acceptSocketThread = new AcceptSocketThread();
            acceptSocketThread.start();
            // 创建给客户端发送信息的线程实例，并启动
            sendMsgToClientThread = new SendMsgToClientThread();
            sendMsgToClientThread.start();
            chatArea.append("--------服务器已开启--------\n");
            writeLog("--------服务器已开启--------");



            // 点击设置serStart后无法使用
            serStart.setEnabled(false);
            // 点击设置serClose后可以使用
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
            chatArea.append("--------服务器已关闭--------\n");
            writeLog("\"--------服务器已关闭--------");

            // 点击设置serClose后无法使用
            serClose.setEnabled(false);
            // 点击设置serStart后可以使用
            serStart.setEnabled(true);
        }

        if (e.getSource() == sendButton) {
            String strMsg = sendMessageArea.getText();
            // 获取当前系统时间，并使用日期格式化类化为指定格式的字符串
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = "<==" + dateFormat.format(new Date()) + "==>\n";
            if (strMsg != null) {
                // 创建一个信息类的对象
                Message message = new Message("系统", true, strMsg, new Date());
                messageList.addFirst(message);
                // 将信息存储到数据库中
                new MySQLTool().insert(message);
                chatArea.append(time + "【系统】对【" + chooseUser.getSelectedItem() + "】说：" + strMsg + "\n");
            }
            sendMessageArea.setText(null);
        }

    }

    /**
     *
     * 接收客户端Socket套接字线程
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
                    System.out.println("==============有客户端连接============");
                    System.out.println("连接信息为：IP:" + socket.getInetAddress() + "   端口:" + socket.getPort());
                    writeLog("==============有客户端连接============");
                    writeLog("连接信息为：IP:" + socket.getInetAddress() + "   端口:" + socket.getPort());
                    if (socket.isConnected()) {
                        System.out.println("socket已连接");
                        GetMsgFromClientThread getMsgFromClientThread = new GetMsgFromClientThread(socket);
                        System.out.println("开启接收信息线程：");
                        System.out.println("\t线程号为：" + getMsgFromClientThread.getId());
                        writeLog("socket已连接  "+"开启接收信息线程：\n"+"\t线程号为：" + getMsgFromClientThread.getId());
                        getMsgFromClientThread.start();
                    }

                } catch (SocketException e) {
                    System.out.println("serverSocket已关闭");
                    writeLog("serverSocket已关闭");
                    if (exit && serverSocket.isClosed()) {
                        break;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            System.out.println("服务端监听线程已退出");
            writeLog("服务端监听线程已退出");

        }
    }


    /**
     *
     * 接收客户端的聊天信息的线程
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
                    System.out.println("接收到客户端第一次发送的连接请求");
                    writeLog("接收到客户端第一次发送的连接请求");
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
                        Message message = (Message) obj;// 把接收到的对象转化为message
                        //收普通的往来信息,或老师的控制信息
                        if (message.getMsgType() == 1) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            // 将接受的信息在服务端显示
                            chatArea.append("<==" + dateFormat.format(message.getTime()) + "==>\n" + "【" + message.getSender() + "】说："
                                    + message.getMsg() + "\n");
                            // 将信息存储到数据库中
                            new MySQLTool().insert(message);
                            // 然后把信息加入到信息列表中
                            messageList.addFirst(message);
                        }
                        if (message.getMsgType() == 3 ||message.getMsgType() == 4) {
                            System.out.println("教师发出控制信息");
                            writeLog("教师发出控制信息");
                            // 将信息存储到数据库中
                            //new MySQLTool().insert(message);
                            // 然后把信息加入到信息列表中
                            messageList.addFirst(message);
                        }

                        //收到端开连接请求的信息
                        if (message.getMsgType() == -1) {
                            Message returnMsg=new Message();
                            returnMsg.setMsgType(5);
                            socketInfo.oos.writeObject( returnMsg);
                            socketInfo.oos.flush();
                            socketInfoList.remove(socketInfo);

                        }
                    }
                }catch (SocketException se){
                    System.out.println("socket已关闭");
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
     * 给客户端发送聊天信息的线程
     *
     */
    class SendMsgToClientThread extends Thread {
        volatile boolean exit = false;

        @Override
        public void run() {
            while (!exit) {
                try {
                    //如果信息链表集合不空（还有聊天信息未发送）
                    if (!messageList.isEmpty()) {
                        //取信息链表集合中读最后一条，并移除
                        Message message = messageList.removeLast();

                        if(message.getAcceptor()){//公开的消息
                            for (SocketInfo socketInfo : socketInfoList) {
                                socketInfo.oos.writeObject(message);
                                socketInfo.oos.flush();
                            }
                        }else{
                            //私发仅发送给老师,同时自己也应该收到
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
                    System.out.println("发送信息时，输出流异常！");
                    writeLog("发送信息时，输出流异常！");
                    // TODO: handle exception
                }
            }
            //服务器关闭
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


















































