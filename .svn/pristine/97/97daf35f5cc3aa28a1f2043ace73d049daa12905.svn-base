package com.mtr.psn.service.orders;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

 
import com.mtr.psn.mapper.orders.AppealformMapper;
 
import com.mtr.psn.model.orders.Appealform;
import com.mtr.psn.model.orders.QueryAppealForm;

@Service
public class AppealFormServiceImpl implements AppealFormService {

	@Resource
	private AppealformMapper appealformMapper;

	@Override
	public Appealform selectById(Long id)throws Exception{
		return	appealformMapper.selectById(id);
	}
	@Override
	public List<Appealform> selectAll(Appealform appealform)throws Exception{
		return	appealformMapper.selectAll(appealform);
	}
	@Override
 
	public List<Appealform> selectAppealFormList(QueryAppealForm appealform)throws Exception{
		return	appealformMapper.selectAppealFormList(appealform);
	}
	@Override
	public Integer Count(QueryAppealForm appealform)throws Exception{
		return	appealformMapper.Count(appealform);
	}
	@Override
	public int psn_insert(Appealform appealform)throws Exception{
 
		return	appealformMapper.insert(appealform);
	}
	@Override
	public int psn_update(Appealform appealform)throws Exception{
		return	appealformMapper.update(appealform);
	}
	@Override
	public int psn_delete(Appealform appealform)throws Exception{
		return	appealformMapper.delete(appealform);
	}
	@Override
	public List<Appealform> selectMoneyByRider(QueryAppealForm appealform)throws Exception{
		return	appealformMapper.selectMoneyByRider(appealform);
	}
	
}
