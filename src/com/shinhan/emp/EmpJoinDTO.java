package com.shinhan.emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString@Builder
public class EmpJoinDTO {

	private String first_name;
	private String last_name;
	private Double salary;
	private String department_name;
	private String job_title;
	
}





