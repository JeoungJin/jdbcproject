package com.shinhan.day18;

import java.util.List;

//View : 나중에 웹전환
public class BoardView {

	
	public static void menuDisplay() {
		System.out.println("------------------------------");
		System.out.println("1.조회 2.bno조회 3.입력 4.수정 5.삭제 9.EXIT");
		System.out.println("------------------------------");
		System.out.print("작업선택>>");
	}
	
	
	public static void print(String message, int resultCount) {
		
		System.out.println("[알림]" + resultCount + "건 " + message);
		
	}
	public static void print(BoardDTO board) {
		System.out.println("=============한건 보여주기===========");
		if(board == null) {
			System.out.println("해당정보가 없습니다.");
			return;
		}
		System.out.println("번호:" + board.getBno());
		System.out.println("제목:" + board.getTitle());
		System.out.println("내용:" + board.getContents());
		System.out.println("작성자:" + board.getWriter());
		System.out.println("등록일:" + board.getReg_date());
		System.out.println("수정일:" + board.getUpdate_date());
	}
	public static void print(List<BoardDTO> blist) {
		System.out.println("============여러건 보여주기==========");
		blist.stream().forEach(System.out::println);
	}
}









