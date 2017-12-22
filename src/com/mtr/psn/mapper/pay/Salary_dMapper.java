package com.mtr.psn.mapper.pay;

import java.util.List;
import com.mtr.psn.model.pay.Salary_d;
import com.mtr.psn.model.pay.Salary_h;

public interface Salary_dMapper {

	public Salary_d selectById(Long id)throws Exception;

	public List<Salary_d> selectAll(Salary_d salary_d)throws Exception;
	
	public int Count(Salary_d salary_d)throws Exception;

	public int insert(Salary_d salary_d)throws Exception;

	public int update(Salary_d salary_d)throws Exception;

	public int delete(Salary_d salary_d)throws Exception;

}
