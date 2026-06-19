package com.shinhan.day16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1.JDBCDrive load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1.JDBCDrive load success");
		//2.Connection
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("2.Connection success");
		//3.SQL문장을 보낸 통로만들기 
		//Statement st = conn.createStatement();
		
		int deptid = 60;
		String sql = """
				select *
				from employees
				where department_id = 60
				""";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		//4.SQL문을 보낸다.
		pst.setInt(1, deptid); //첫번째?에 값을 setting 
		ResultSet rs = pst.executeQuery();	 
		//5.실행결과를 가져온다.
		while(rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print(rs.getString("first_name"));
			System.out.println(rs.getDouble("salary"));
		}
		//6.자원반납(DB연결해제) 
		rs.close();pst.close();conn.close();
	}

}



