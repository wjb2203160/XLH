package com.mtr.psn.mapper.pay;

import java.util.List;

import com.mtr.psn.model.pay.QueryWage;
import com.mtr.psn.model.pay.Salary_h;

public interface Salary_hMapper {

	public Salary_h selectById(Long id)throws Exception;
	
	public Salary_h selectBysiteId(Long id)throws Exception;
	
	public List<Salary_h> selectAll(Salary_h salary_h)throws Exception;

	public int Count(Salary_h salary_h)throws Exception;
	
	public int insert(Salary_h salary_h)throws Exception;

	public int update(Salary_h salary_h)throws Exception;

	public int delete(Salary_h salary_h)throws Exception;

}
