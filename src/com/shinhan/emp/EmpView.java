package com.shinhan.emp;

import java.util.List;

//View : 나중에 웹전환
public class EmpView {

	public static void menuDisplay() {
		System.out.println("------------------------------");
		System.out.println("1.조회 2.empid조회 3.입력 4.수정 5.삭제 ");
		System.out.println("6.부서로 조회 7.job으로 조회 8.조건4개로 조회 ");
		System.out.println("9.sp호출 99.EXIT");
		System.out.println("------------------------------");
		System.out.print("작업선택>>");
	}

	public static void print(String message, int resultCount) {

		System.out.println("[알림]" + resultCount + "건 " + message);

	}

	public static void print(EmpVO emp) {
		System.out.println("=============한건 보여주기===========");
		if (emp == null) {
			System.out.println("해당정보가 없습니다.");
			return;
		}
		System.out.println("커미션:" + emp.getCommission_pct());
		System.out.println("Department_id:" + emp.getDepartment_id());
		System.out.println("Email:" + emp.getEmail());
		System.out.println("Employee_id:" + emp.getEmployee_id());
		System.out.println("First_name:" + emp.getFirst_name());
		System.out.println("Job_id:" + emp.getJob_id());
		System.out.println("First_name:" + emp.getFirst_name());
		System.out.println("Last_name:" + emp.getLast_name());
		System.out.println("Manager_id:" + emp.getManager_id());
		System.out.println("Phone_number:" + emp.getPhone_number());
		System.out.println("Salary:" + emp.getSalary());
	}

	public static void print(List<EmpVO> blist) {
		System.out.println("============여러건 보여주기==========");
		blist.stream().forEach(System.out::println);
	}
}
