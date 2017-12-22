package com.mtr.psn.service.pay;

import java.util.List;

import com.mtr.psn.model.goods.GoodsRegistrationForm;
import com.mtr.psn.model.pay.QueryWage;
import com.mtr.psn.model.pay.Wage;

public interface WageService {

	public Wage selectById(Long id)throws Exception;

	public List<Wage> selectAll(Wage wage)throws Exception;

	public int psn_insert(Wage wage)throws Exception;

	public int psn_update(Wage wage)throws Exception;

	public int psn_delete(Wage wage)throws Exception;
	
	public int deleteByMonth(QueryWage wage)throws Exception;

	public int CountByRider(QueryWage wage)throws Exception;
	
	public List<Wage> selectWageList(QueryWage wage)throws Exception;
	
	public List<GoodsRegistrationForm> selectGoodsList(QueryWage wage)throws Exception;
	
	public List<Wage> selectTeamList(QueryWage wage)throws Exception;
	
	public int CountByTeam(QueryWage wage)throws Exception;
}
