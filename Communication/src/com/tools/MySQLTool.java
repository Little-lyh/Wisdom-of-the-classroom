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
			// 加载驱动程序
			Class.forName(driver);
			// 1.getConnection()方法，连接MySQL数据库！！
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// 数据库驱动类异常处理
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		}
	}


	// 增加数据的方法
	public void insert(Message message) {
		try {
			PreparedStatement psql;
			psql = con.prepareStatement("insert into chat_record (sender,acceptor,message,time) " + "values(?,?,?,?)");
			psql.setString(1, message.getSender());
			if( message.getAcceptor()){
				psql.setString(2, "公开");
			}else{
				psql.setString(2, "私发");
			}
			psql.setString(3, message.getMsg());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			psql.setString(4, df.format( message.getTime()));
			psql.executeUpdate();// 执行更新
			psql.close();
			con.close();// 关闭连接
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		}
	}
}
