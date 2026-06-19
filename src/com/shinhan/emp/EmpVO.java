package com.shinhan.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO,VO,Entity,Beans
//@RequiredArgsConstructor
@NoArgsConstructor@AllArgsConstructor
@Builder@Setter@Getter@ToString
public class EmpVO {
	 private Integer employee_id;      
	 private String first_name;       
	 private String last_name;        
	 private String email ;           
	 private String phone_number ;    
	 private Date hire_date;        
	 private String job_id;           
	 private Double salary ;          
	 private Double commission_pct;   
	 private Integer manager_id ;      
	 private Integer department_id ;   
	
}





