package com.mtr.psn.service.pay;

import java.util.List;
import com.mtr.psn.model.pay.Salary_h;

public interface Salary_hService {

	public Salary_h selectById(Long id)throws Exception;

	public Salary_h selectBysiteId(Long id)throws Exception;
	
	public List<Salary_h> selectAll(Salary_h salary_h)throws Exception;

	public int Count(Salary_h salary_h)throws Exception;
	
	public int psn_insert(Salary_h salary_h)throws Exception;

	public int psn_update(Salary_h salary_h)throws Exception;

	public int psn_delete(Salary_h salary_h)throws Exception;

}
