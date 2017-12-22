package com.mtr.psn.service.pay;

import java.util.List;
import com.mtr.psn.model.pay.Salary_d;

public interface Salary_dService {

	public Salary_d selectById(Long id)throws Exception;

	public List<Salary_d> selectAll(Salary_d salary_d)throws Exception;
	
	public int Count(Salary_d salary_d)throws Exception;

	public int psn_insert(Salary_d salary_d)throws Exception;

	public int psn_update(Salary_d salary_d)throws Exception;

	public int psn_delete(Salary_d salary_d)throws Exception;

}
