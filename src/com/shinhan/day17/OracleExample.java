package com.shinhan.day17;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleExample {

	public static void main(String[] args) {
		//1.JDBC Driver load (먼저 class path에서 JDBC library가 존재)
		Connection conn = null; //DB연결
		Statement st = null; //SQL문보내는 통로, 바인딩변수 사용불가능 
		ResultSet rs = null; //Select문 결과가 들어온다. 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr", password="hr";
		String sql = """
				SELECT job_id,  max(EMPLOYEE_ID) max_empid, 
					      min(FIRST_NAME) min_name, 
					      sum(SALARY) sum_sal, min(HIRE_DATE) min_hiredate  
				FROM EMPLOYEES    
				WHERE department_id >= 10  
				GROUP BY job_id  
				HAVING  sum(SALARY) >= 10000   
				ORDER BY job_id  
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.1.JDBC Driver load 성공");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("2.DB연결성공");
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			System.out.println("3.SQL문 전송하고 결과받음(Java APP Memory에있음");
			while(rs.next()) {
				String job_id = rs.getString(1); //칼럼의순서
				int empid = rs.getInt(2);
				String fname = rs.getString("min_name"); //칼럼이름, 별명(Alias)
				double sal = rs.getDouble("sum_sal");
				Date hdate = rs.getDate("min_hiredate");
				System.out.printf("%s %d %s %f %s \n", job_id, empid,fname ,sal,  hdate );
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("===main end====");
	}

}
