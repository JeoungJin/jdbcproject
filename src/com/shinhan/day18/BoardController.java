package com.shinhan.day18;

import java.util.List;
import java.util.Scanner;

public class BoardController {

	static Scanner sc = new Scanner(System.in);
	static BoardService boardService = new BoardService();
	
	public static void main(String[] args) {
		boolean isStop = false;
		while(!isStop) {
			BoardView.menuDisplay();
			int job = sc.nextInt();
			switch(job) {
			case 1->{ f_selectAll();}
			case 2->{ f_selectById();}
			case 3->{ f_insert();}
			case 4->{ f_update();}
			case 5->{ f_delete();}
			case 9->{ isStop=true;}
			}
		}
		System.out.println("====bye====");
	}
	private static void f_delete() {
		sc.nextLine(); //작업선택후 뒷부분(next()) enter 버리기 

		System.out.print("삭제할 bno>>");
		int bno = Integer.parseInt( sc.nextLine() );	
		int result = boardService.deleteService(bno);
		BoardView.print("삭제됨", result);	
		
	}
	private static void f_update() {
		sc.nextLine(); //작업선택후 뒷부분(next()) enter 버리기 

		System.out.print("수정할 bno>>");
		int bno = Integer.parseInt( sc.nextLine() );	
		System.out.print("수정할 title>>");
		String title = sc.nextLine();
		System.out.print("수정할 contents>>");
		String contents = sc.nextLine();
		System.out.print("수정할 writer>>");
		String writer = sc.nextLine();
		BoardDTO board = BoardDTO.builder()
				.bno(bno)
				.title(title)
				.contents(contents)
				.writer(writer)
				.build();		
		int result = boardService.updateService(board);
		BoardView.print("수정됨", result);		
	}
	
	private static void f_insert() {
		sc.nextLine(); //작업선택후 뒷부분(next()) enter 버리기 
		System.out.print("title>>");
		String title = sc.nextLine();
		System.out.print("contents>>");
		String contents = sc.nextLine();
		System.out.print("writer>>");
		String writer = sc.nextLine();
		
		BoardDTO board = BoardDTO.builder()
				.title(title)
				.contents(contents)
				.writer(writer)
				.build();
		
		int result = boardService.insertService(board);
		BoardView.print("입력됨", result);
		
	}
	private static void f_selectById() {
		System.out.print("조회할 bno>>");
		int bno = sc.nextInt();
		BoardDTO board = boardService.selectByIdService(bno);
		BoardView.print(board);
	}
	private static void f_selectAll() {
		List<BoardDTO> blist = boardService.selectAllService();
		BoardView.print(blist);
	}

}
