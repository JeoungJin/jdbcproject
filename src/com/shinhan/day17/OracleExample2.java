package com.shinhan.day17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.util.DBUtil;

public class OracleExample2 {
	static Connection conn = null; //DB연결
	static Statement st = null; //SQL문보내는 통로, 바인딩변수 사용불가능 
	static PreparedStatement pst = null;
	static ResultSet rs = null; //Select문 결과가 들어온다. 
	
	
	 
	 
	 

	public static void main(String[] args) {
		f_insert(1,"부서1", 100, 1700);
		//f_insert(2,"부서2", 100, 1700);
		//f_selectall();
		//f_update(1, "개발부");
		//f_delete(2);
		//f_selectall();
		//f_commit();
		
	}
	private static void f_commit() {
		String sql1 = "update departments set department_name ='영업부' where department_id = 270";
		String sql2 = "insert into departments values(280,'총무부', 100,1700)";
		Statement st2 = null;
		conn = DBUtil.dbConnect();
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			int result1 = st.executeUpdate(sql1);
			st2 = conn.createStatement();
			int result2 = st2.executeUpdate(sql2);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
 
			}
			e.printStackTrace();
		}
		
		
		
		
	}
	private static void f_delete(int deptid) {
		String sql_delete = "delete from departments where department_id = ?";
		conn = DBUtil.dbConnect();
		
		try {
			//자동으로 autoCommit이다. conn.setAutoCommit(true);
           pst = conn.prepareStatement(sql_delete);
           pst.setInt(1, deptid);

           int result = pst.executeUpdate();
           System.out.println(result + "건 delete");
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
	}
	
	private static void f_update(int deptid,String deptname) {
		String sql_update = "update departments set department_name =? where department_id = ?";
		conn = DBUtil.dbConnect();
		
		try {
			//자동으로 autoCommit이다. conn.setAutoCommit(true);
           pst = conn.prepareStatement(sql_update);
           pst.setInt(2, deptid);
           pst.setString(1, deptname);

           int result = pst.executeUpdate();
           System.out.println(result + "건 update");
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
	}
	private static void f_insert(int deptid,String deptname, int mid, int loc) {
		String sql_insert = "insert into departments values(seq_deptid.nextval,?,?,?)";
		conn = DBUtil.dbConnect();
		
		try {
			//자동으로 autoCommit이다. conn.setAutoCommit(true);
           pst = conn.prepareStatement(sql_insert);
           //pst.setInt(1, deptid);
           pst.setString(1, deptname);
           pst.setInt(2, mid);
           pst.setInt(3, loc);
           int result = pst.executeUpdate();
           System.out.println(result + "건 insert");
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
	}
	
	private static void f_selectall() {
		String sql_select = "select * from departments order by 1";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql_select);
			while(rs.next()) {
				int deptid = rs.getInt(1);
				String deptname = rs.getString(2);
				int mid = rs.getInt(3);
				int loc = rs.getInt(4);
				System.out.printf("%d %s %d %d \n", deptid, deptname, mid, loc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
	}

}







