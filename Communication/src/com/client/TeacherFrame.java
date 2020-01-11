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

        /*�������������JFrame*/
        displayFrame = new JFrame("���ԡ�����ʾ���");
        displayFrame.setLayout(null);
        //�˾��øøĵ�
        chatArea = new JTextArea();
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        displayFrame.setSize(500, 250);
        chatScrollPane.setBounds(0, 0, 600, 250);
        chatArea.setFont(new Font("����", Font.PLAIN, 20));
        displayFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ͼ��.jpg"));
        // ��Ϊ���ɱ༭
        chatArea.setEditable(false);
        // ��Ϊ͸��
        chatArea.setOpaque(false);
        // �Զ�����
        chatArea.setLineWrap(true);
        chatArea.setVisible(true);
        displayFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ͼ��.png"));
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
        controlFrame = new JFrame("���ԡ����������");
        systemMsg =new JLabel("ϵͳ��Ϣ��ʾ��",SwingConstants.CENTER);
        sendWho=new ButtonGroup();
        showD=new ButtonGroup();
        openChat =new JRadioButton("�򿪷���");
        closeChat =new JRadioButton("�رշ���");
        showDis=new JRadioButton("��ʾ��������");
        notShowDis=new JRadioButton("���ط�������");


        controlFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ͼ��.png"));

        controlFrame.setSize(400,300);
        //controlFrame.setLocation(1000,400);
        controlFrame.setLocationRelativeTo(null);
        systemMsg.setBounds(0, 20, 400, 30);

        openChat.setBounds(20, 80, 100, 20);
        closeChat.setBounds(220, 80, 100, 20);
        showDis.setBounds(20, 140, 150, 20);
        notShowDis.setBounds(220, 140, 150, 20);

        systemMsg.setFont(new Font("����", Font.PLAIN, 22));
        closeChat.setFont(new Font("����", Font.PLAIN, 18));
        openChat.setFont(new Font("����", Font.PLAIN, 18));
        showDis.setFont(new Font("����", Font.PLAIN, 18));
        notShowDis.setFont(new Font("����", Font.PLAIN, 18));

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

        //����Ĭ��ѡ��
        openChat.setSelected(true);
        notShowDis.setSelected(true);

        // �¼�������ע��
        closeChat.addActionListener(this);
        openChat.addActionListener(this);
        showDis.addActionListener(this);
        notShowDis.addActionListener(this);



        while(socket==null){
            try {
                // ����һ���׽���
                socket = new Socket(ip, 28888);
                // ����һ�����׽�����д���ݵĹܵ��������������������������Ϣ
                oos = new ObjectOutputStream(socket.getOutputStream());
                // ����һ�����׽����ж����ݵĹܵ����������������������ķ�����Ϣ
                ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Message message=new Message();
                message.setMsgType(2);
                message.setSender("TEACHER");
                oos.writeObject(message);
                systemMsg.setText("�ѳɹ����ӷ�����");

            } catch (ConnectException ce) {
               systemMsg.setText("������δ����");
                System.out.println("������δ����");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        GetMsgFromServerThread getMsgFromServer = new GetMsgFromServerThread();
        // �����߳�
        if(socket!=null&&socket.isConnected()){
            getMsgFromServer.start();
        }

        // ���ڹر�ʱ�Ĵ���
        controlFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {



                if(socket!=null&&socket.isConnected()){
                    try {
                        //�������Ķ�������
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
            System.out.println("�ر�����");
            //�������serClose���޷�ʹ��
            //����һ����Ϣ��ʹ�ø��ͻ��˵�button�޷����
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
            System.out.println("������");
            //�������serClose���޷�ʹ��
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
    // ���ܷ������ķ�����Ϣ���߳�


    class GetMsgFromServerThread extends Thread {
        volatile boolean exit = false;
        @Override
        public void run() {
            System.out.println("jieshou");
            // TODO Auto-generated method stub
            while (!exit && socket.isConnected()){// flagΪtrueʱ��ʾ�ͻ��˻������У�
                try {
                    Object obj = ois.readObject();
                    if (obj != null) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                        Message message = (Message) obj;// �ѽ��յ��Ķ���ת��Ϊmessage
                        if(message.getMsgType()==1){
                            chatArea.append("<=="+df.format(new Date())+"==>\n");
                            chatArea.append("��" + message.getSender() + "��˵��"
                                    + message.getMsg() + "\n");
                            System.out.println("<=="+df.format(new Date())+"==>\n"+"��" + message.getSender() + "��˵��");
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





