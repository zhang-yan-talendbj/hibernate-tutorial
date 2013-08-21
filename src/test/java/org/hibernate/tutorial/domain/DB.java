package org.hibernate.tutorial.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        <property name="connection.driver_class">org.hsqldb.jdbcDriver</property>
//        <property name="connection.url">jdbc:hsqldb:hsql://localhost</property>
//        <property name="connection.username">sa</property>
//        <property name="connection.password"></property>
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost","sa","");
		Statement stat = conn.createStatement();
		boolean execute = stat.execute("select * from EVENTS where title='bruce'");
		ResultSet rs = stat.getResultSet();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for(int i=1;i<=columnCount;i++){
			System.out.print(metaData.getColumnName(i)+"\t");
		}
		while(rs.next()){
			System.out.println();
			for(int i=1;i<=columnCount;i++){
				System.out.print(rs.getString(i)+"\t");
			}
		}
	}
}
