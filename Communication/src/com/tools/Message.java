package com.tools;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 信息类的类型
	 * 1表示正常的信息类；-1表示客户端断连请求，0表示服务端关闭提醒，2表示客户端初次连接
	 * 3表示老师关闭发言，4表示老师打开发言,5表示服务端允许断开连接
	 */
	private int msgType=1;
    /**
     * 发送者为TEACHER时表示信息由教师发送
     */
	private String sender=null;
	/**
	 * 信息类的接受者
	 * false表示私发，true表示公开
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
