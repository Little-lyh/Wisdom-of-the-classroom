package com.tools;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ��Ϣ�������
	 * 1��ʾ��������Ϣ�ࣻ-1��ʾ�ͻ��˶�������0��ʾ����˹ر����ѣ�2��ʾ�ͻ��˳�������
	 * 3��ʾ��ʦ�رշ��ԣ�4��ʾ��ʦ�򿪷���,5��ʾ���������Ͽ�����
	 */
	private int msgType=1;
    /**
     * ������ΪTEACHERʱ��ʾ��Ϣ�ɽ�ʦ����
     */
	private String sender=null;
	/**
	 * ��Ϣ��Ľ�����
	 * false��ʾ˽����true��ʾ����
	 */
	private boolean acceptor=true;
	private String msg=null;
	private Date time=null;



	public Message(){ }

	public Message(String sender, boolean acceptor, String msg) {
		this.sender = sender;
		this.acceptor = acceptor;
		this.msg = msg;
	}
	public Message(String sender, boolean acceptor, String msg, Date time) {
		this.sender = sender;
		this.acceptor = acceptor;
		this.msg = msg;
		this.time = time;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType =msgType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public boolean getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(boolean acceptor) {
		this.acceptor = acceptor;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
