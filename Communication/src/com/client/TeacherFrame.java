package com.client;

import com.tools.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lxd
 */
public class TeacherFrame implements ActionListener {

    private String ip="127.0.0.1";
    private JTextArea chatArea;
    private JRadioButton openChat, closeChat,showDis,notShowDis;
    private ButtonGroup sendWho,showD;
    private JFrame displayFrame,controlFrame;
    private JLabel systemMsg,imgLabel;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public TeacherFrame() {

        /*创建聊天区域的JFrame*/
        displayFrame = new JFrame("发言——显示面板");
        displayFrame.setLayout(null);
        //此句用该改掉
        chatArea = new JTextArea();
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        displayFrame.setSize(500, 250);
        chatScrollPane.setBounds(0, 0, 600, 250);
        chatArea.setFont(new Font("宋体", Font.PLAIN, 20));
        displayFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/图标.jpg"));
        // 设为不可编辑
        chatArea.setEditable(false);
        // 设为透明
        chatArea.setOpaque(false);
        // 自动换行
        chatArea.setLineWrap(true);
        chatArea.setVisible(true);
        displayFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/图标.png"));
        displayFrame.add(chatScrollPane);
        chatArea.setOpaque(false);
        chatScrollPane.setOpaque(false);
        chatScrollPane.getViewport().setOpaque(false);

        displayFrame.setUndecorated(true);
        displayFrame.setBackground(new Color(0, 0, 0, 0));
        displayFrame.setAlwaysOnTop(true);
        displayFrame.setLocation(550, 0);
        displayFrame.setResizable(false);
        displayFrame.setVisible(false);
        displayFrame.setType(JFrame.Type.UTILITY);



        imgLabel = new JLabel(new ImageIcon("image/controlBG.jpg"));
        imgLabel.setBounds(0, 0, 400, 300);
        controlFrame = new JFrame("发言——控制面板");
        systemMsg =new JLabel("系统消息显示处",SwingConstants.CENTER);
        sendWho=new ButtonGroup();
        showD=new ButtonGroup();
        openChat =new JRadioButton("打开发言");
        closeChat =new JRadioButton("关闭发言");
        showDis=new JRadioButton("显示发言内容");
        notShowDis=new JRadioButton("隐藏发言内容");


        controlFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/图标.png"));

        controlFrame.setSize(400,300);
        //controlFrame.setLocation(1000,400);
        controlFrame.setLocationRelativeTo(null);
        systemMsg.setBounds(0, 20, 400, 30);

        openChat.setBounds(20, 80, 100, 20);
        closeChat.setBounds(220, 80, 100, 20);
        showDis.setBounds(20, 140, 150, 20);
        notShowDis.setBounds(220, 140, 150, 20);

        systemMsg.setFont(new Font("宋体", Font.PLAIN, 22));
        closeChat.setFont(new Font("宋体", Font.PLAIN, 18));
        openChat.setFont(new Font("宋体", Font.PLAIN, 18));
        showDis.setFont(new Font("宋体", Font.PLAIN, 18));
        notShowDis.setFont(new Font("宋体", Font.PLAIN, 18));

        sendWho.add(openChat);
        sendWho.add(closeChat);
        showD.add(showDis);
        showD.add(notShowDis);

        controlFrame.add(closeChat);
        controlFrame.add(openChat);
        controlFrame.add(showDis);
        controlFrame.add(notShowDis);
        controlFrame.add(systemMsg);
        controlFrame.add(imgLabel);

        controlFrame.setResizable(false);
        controlFrame.setLayout(null);
        controlFrame.setVisible(true);

        openChat.setFocusable(false);
        closeChat.setFocusable(false);
        openChat.setOpaque(false);
        closeChat.setOpaque(false);

        showDis.setFocusable(false);
        notShowDis.setFocusable(false);
        showDis.setOpaque(false);
        notShowDis.setOpaque(false);

        //设置默认选项
        openChat.setSelected(true);
        notShowDis.setSelected(true);

        // 事件监听的注册
        closeChat.addActionListener(this);
        openChat.addActionListener(this);
        showDis.addActionListener(this);
        notShowDis.addActionListener(this);



        while(socket==null){
            try {
                // 创建一个套接字
                socket = new Socket(ip, 28888);
                // 创建一个往套接字中写数据的管道，即输出流，给服务器发送信息
                oos = new ObjectOutputStream(socket.getOutputStream());
                // 创建一个从套接字中读数据的管道，即输入流，读服务器的返回信息
                ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Message message=new Message();
                message.setMsgType(2);
                message.setSender("TEACHER");
                oos.writeObject(message);
                systemMsg.setText("已成功连接服务器");

            } catch (ConnectException ce) {
               systemMsg.setText("服务器未开启");
                System.out.println("服务器未开启");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        GetMsgFromServerThread getMsgFromServer = new GetMsgFromServerThread();
        // 启动线程
        if(socket!=null&&socket.isConnected()){
            getMsgFromServer.start();
        }

        // 窗口关闭时的处理
        controlFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {



                if(socket!=null&&socket.isConnected()){
                    try {
                        //发送最后的断连请求
                        Message message=new Message();
                        message.setMsgType(-1);
                        oos.writeObject(message);
                        oos.flush();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                System.out.println("dis");

                displayFrame.setVisible(false);
                System.out.println("con");
                controlFrame.setVisible(false);
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeChat && socket!=null &&socket.isConnected()) {
            System.out.println("关闭聊天");
            //点击设置serClose后无法使用
            //发送一个信息，使得各客户端的button无法点击
            try {
                Message msg = new Message();
                msg.setMsgType(3);
                oos.writeObject(msg);
                oos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() ==openChat  && socket!=null && socket.isConnected()) {
            System.out.println("打开聊天");
            //点击设置serClose后无法使用
            try {
                Message msg = new Message();
                msg.setMsgType(4);
                oos.writeObject(msg);
                oos.flush();
            } catch (IOException e1) {
                //TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (e.getSource() == showDis) {
           displayFrame.setVisible(true);
           Point p= controlFrame.getLocation();
           System.out.println(p.x);
            System.out.println(p.y);
        }
        if (e.getSource() == notShowDis) {
            displayFrame.setVisible(false);
        }

    }
    // 接受服务器的返回信息的线程


    class GetMsgFromServerThread extends Thread {
        volatile boolean exit = false;
        @Override
        public void run() {
            System.out.println("jieshou");
            // TODO Auto-generated method stub
            while (!exit && socket.isConnected()){// flag为true时表示客户端还在运行；
                try {
                    Object obj = ois.readObject();
                    if (obj != null) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        Message message = (Message) obj;// 把接收到的对象转化为message
                        if(message.getMsgType()==1){
                            chatArea.append("<=="+df.format(new Date())+"==>\n");
                            chatArea.append("【" + message.getSender() + "】说："
                                    + message.getMsg() + "\n");
                            System.out.println("<=="+df.format(new Date())+"==>\n"+"【" + message.getSender() + "】说：");
                        }
                        if(message.getMsgType()==5){
                            exit=true;
                        }
                    }
                }catch (EOFException e){
                    e.printStackTrace();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                ois.close();
                oos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}





