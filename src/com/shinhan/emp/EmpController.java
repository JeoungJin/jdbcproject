package com.shinhan.emp;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmpController {
    static Scanner sc = new Scanner(System.in);
    static EmpService empService = new EmpService();
	public static void main(String[] args) {
		boolean isStop = false;
		while(!isStop) {
			EmpView.menuDisplay();
			String job = sc.nextLine();
			switch(job) {
			case "1"->{f_selectAll();}
			case "2"->{f_selectById();}
			case "3"->{f_insert();}
			case "4"->{f_update();}
			case "5"->{f_delete();}
			case "6"->{f_selectByDept();}
			case "7"->{f_selectByJob();}
			case "8"->{f_selectByCondition();}
			case "9"->{f_sp();}
			case "10"->{f_join2();}
			case "11"->{f_join1();}
			case "99"->{isStop = true; }			
			default->{System.out.println("작업선택 오류. 다시선택");}
			}
		}
		
		System.out.println("=====bye=====");
	}
	private static void f_join1() {
		String deptid = getData("조회할 부서>>");
		List<EmpJoinDTO> emplist = empService.join_emp_dept_job1(Integer.parseInt(deptid));
		//이 부분은 view로 옮기기 
		for(EmpJoinDTO emp :emplist) {
			System.out.println(emp);
		}
	}
	
	private static void f_join2() {
		String deptid = getData("조회할 부서>>");
		List<Map<String, Object>> emplist = empService.join_emp_dept_job2(Integer.parseInt(deptid));
		//이 부분은 view로 옮기기 
		for(Map<String, Object> map :emplist) {
			System.out.println("===============");
			for(String key:map.keySet()) {
				System.out.println(key + "==>"+ map.get(key));
			}
		}
	}
	private static void f_sp() {
		String empid = getData("급여를 수정할 직원>>");
		String comm = getData("커미션>>");
		int result = empService.spcall_raise_salary(Integer.parseInt(empid),
				                                    Double.parseDouble(comm) );
		EmpView.print("급여인상", result);
	}
	private static void f_selectByCondition() {
		String deptid = getData("조회할 deptid>>");
		String jobid = getData("조회할 jobid>>");
		String salary = getData("조회할 salary>>");
		String hdate = getData("조회할 hire_date(yyyy-mm-dd)>>");
		List<EmpVO> emplist = empService.selectByCondition(
				Integer.parseInt(deptid), jobid, Double.parseDouble(salary), 
				Date.valueOf(hdate));
		EmpView.print(emplist);
	}
	private static void f_selectByJob() {
		System.out.print("조회할 jobid>>");
		String jobid = sc.nextLine() ;
		List<EmpVO> emplist = empService.selectByJob(jobid);
		EmpView.print(emplist);
	}
	private static void f_selectByDept() {
		System.out.print("조회할 deptid>>");
		int deptid = Integer.parseInt( sc.nextLine() );
		List<EmpVO> emplist = empService.selectByDeptService(deptid);
		EmpView.print(emplist);
		
	}
	private static void f_delete() {
		System.out.print("삭제할 empid>>");
		int empid = Integer.parseInt( sc.nextLine() );
		int result = empService.deleteService(empid);
		EmpView.print("삭제", result);
	}
	private static void f_update() {
		System.out.println("-----수정할 data입력----");
		EmpVO emp = inputEmp();
		int result = empService.updateService(emp);
		EmpView.print("수정", result);
	}
	private static void f_insert() {
		EmpVO emp = inputEmp();
		int result = empService.insertService(emp);
		EmpView.print("입력", result);
	}
	private static String getData(String message) {
		System.out.print(message);
		return sc.nextLine().trim();
	}
	private static EmpVO inputEmp() {
		String empid = getData("직원번호(필수)>>");
		String fname = getData("이름>>");
		String lname = getData("성(필수)>>");
		String comm = getData("commission_pct>>");
		String deptid = getData("department_id>>");
		String email = getData("email(필수)>>");
		String hdate = getData("입사일(필수 2026-01-02),default>>");
		String job_id = getData("job_id(필수),default>>");
		String mid = getData("manager_id>>");
		String phone = getData("phone_number>>");
		String salary = getData("salary>>");
		System.out.println("comm:" + comm.isEmpty());
		EmpVO emp = EmpVO.builder()
				.commission_pct( comm.isEmpty()?null:Double.parseDouble(comm))
				.department_id( deptid.isEmpty()?null:Integer.parseInt(deptid)) //FK
				.email( email.isEmpty()?null:email) //UNIQUE
				.employee_id( empid.isEmpty()?null:Integer.parseInt(empid))
				.first_name(fname.isEmpty()?null:fname)
				.hire_date( hdate.isEmpty()?null:
							Date.valueOf(hdate))
				.job_id( job_id.isEmpty()?null:job_id) //FK
				.last_name(lname.isEmpty()?null:lname)
				.manager_id( mid.isEmpty()? null: Integer.parseInt(mid) ) //FK
				.phone_number(phone.isEmpty()?null:phone)
				.salary(salary.isEmpty()?null:Double.parseDouble(salary))
				.build();
		System.out.println(emp);
		return emp;
	}
	private static void f_selectById() {
		System.out.print("조회할 직원번호>>");
		int empId = Integer.parseInt( sc.nextLine().trim() );
		EmpVO emp = empService.selectByIdService(empId);
		EmpView.print(emp);
	}
	private static void f_selectAll() {
		List<EmpVO> emplist = empService.selectAllService();
		EmpView.print(emplist);
	}

}







