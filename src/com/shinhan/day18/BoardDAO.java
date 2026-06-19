package com.shinhan.day18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

//영속성영역을 담당 
//Repository 
//DAO(Data Access Object)
public class BoardDAO {
	// DB연결
	Connection conn;
	// SQL문보내기위한 통로
	Statement st;
	// SQL문보내기위한 통로 , ?가능
	PreparedStatement ps;
	// Select결과를 받음
	ResultSet rs;
	// DML결과, 영향을 받은 건수
	int resultCount;

	// 삭제
	public int deleteBoard(int bno) {
		String sql = "delete from board  where bno=?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		return resultCount;
	}

	// 수정
	public int updateBoard(BoardDTO board) {
		String sql = "update board set " + " title=?, contents=?, writer=? , update_date=sysdate " + " where bno=?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContents());
			ps.setString(3, board.getWriter());
			ps.setInt(4, board.getBno());
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		return resultCount;
	}

	// 입력
	public int insertBoard(BoardDTO board) {
		String sql = "insert into board values(seq_boardno.nextval, ?, ?, ?, sysdate, null)";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContents());
			ps.setString(3, board.getWriter());
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		return resultCount;
	}

	// PK로 조회
	public BoardDTO selectById(int bno) {
		BoardDTO board = null;
		String sql = "select * from board where bno = ?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			while (rs.next()) {
				board = makeBoard(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		return board;
	}

	// 모두조회
	public List<BoardDTO> selectAll() {
		List<BoardDTO> boardList = new ArrayList<>();
		String sql = "select * from board";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BoardDTO board = makeBoard(rs);
				boardList.add(board);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return boardList;
	}

	private BoardDTO makeBoard(ResultSet rs) throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setBno(rs.getInt(1));
		board.setContents(rs.getString("Contents"));
		board.setReg_date(rs.getDate("Reg_date"));
		board.setTitle(rs.getString("title"));
		board.setUpdate_date(rs.getDate("update_date"));
		board.setWriter(rs.getString("Writer"));
		return board;
	}

}
