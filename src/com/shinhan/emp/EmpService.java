package com.shinhan.emp;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class EmpService {

	EmpDAO empDAO = new EmpDAO();
	
	public List<EmpJoinDTO>join_emp_dept_job1(int deptid) {
		List<EmpJoinDTO> emplist = empDAO.join_emp_dept_job1(deptid);
		return emplist;
	}
	
	public List<Map<String,Object>>join_emp_dept_job2(int deptid) {
		List<Map<String,Object>> emplist = empDAO.join_emp_dept_job2(deptid);
		return emplist;
	}
	public List<EmpVO> selectByCondition(int deptid, String jobid, 
			  double salary, Date hiredate) {
		List<EmpVO> emplist = empDAO.selectByCondition(deptid, jobid, salary, hiredate);
		return emplist;
	}
	public List<EmpVO> selectByJob(String jobid) {
		List<EmpVO> emplist = empDAO.selectByJob(jobid);
		return emplist;
	}
	
	public List<EmpVO> selectByDeptService(int deptid) {
		List<EmpVO> emplist = empDAO.selectByDept(deptid);
		return emplist;
	}
	public List<EmpVO> selectAllService() {
		List<EmpVO> emplist = empDAO.selectAll();
		return emplist;
	}

	public EmpVO selectByIdService(int empid) {
		EmpVO emp = empDAO.selectById(empid);
		return emp;
	}

	public int insertService(EmpVO emp) {
		int result = empDAO.insert(emp);
		return result;
	}

	public int updateService(EmpVO emp) {
		int result = empDAO.update(emp);
		return result;
	}

	public int deleteService(int empid) {
		int result = empDAO.delete(empid);
		return result;
		 
	}
	public int spcall_raise_salary(int empid, double comm) {
		int result = empDAO.spcall_raise_salary(empid, comm);
		return result;
	}

 

}
