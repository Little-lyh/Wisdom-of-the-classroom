package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxd
 */
public class MySQLTool {
	private Connection con = null;
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
	private final String user = "root";
	private final String password = "123456789";

	public MySQLTool() {
		try {
			// ������������
			Class.forName(driver);
			// 1.getConnection()����������MySQL���ݿ⣡��
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// ���ݿ��������쳣����
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			// ���ݿ�����ʧ���쳣����
			e.printStackTrace();
		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		}
	}


	// �������ݵķ���
	public void insert(Message message) {
		try {
			PreparedStatement psql;
			psql = con.prepareStatement("insert into chat_record (sender,acceptor,message,time) " + "values(?,?,?,?)");
			psql.setString(1, message.getSender());
			if( message.getAcceptor()){
				psql.setString(2, "����");
			}else{
				psql.setString(2, "˽��");
			}
			psql.setString(3, message.getMsg());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			psql.setString(4, df.format( message.getTime()));
			psql.executeUpdate();// ִ�и���
			psql.close();
			con.close();// �ر�����
		} catch (SQLException e) {
			// ���ݿ�����ʧ���쳣����
			e.printStackTrace();
		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		}
	}
}
