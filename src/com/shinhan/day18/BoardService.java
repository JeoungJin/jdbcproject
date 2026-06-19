package com.shinhan.day18;

import java.util.List;

//Service : 비지니스 로직담당 
//A계좌에서 B계좌로 이체 
//1)A계좌 인출
//2)B계좌로입금 
public class BoardService {
	
	BoardDAO boardDAO = new BoardDAO();
	
	public int deleteService(int bno) {
		int result = boardDAO.deleteBoard(bno);
		return result;
	}
	public int updateService(BoardDTO board) {
		int result = boardDAO.updateBoard(board);
		return result;
	}
	public int insertService(BoardDTO board) {
		int result = boardDAO.insertBoard(board);
		return result;
	}
	
	public BoardDTO selectByIdService(int bno) {
		BoardDTO board = boardDAO.selectById(bno);
		return board;
	}
	public List<BoardDTO> selectAllService() {
		List<BoardDTO> boardList = boardDAO.selectAll();
		return boardList;
	}
	
}







