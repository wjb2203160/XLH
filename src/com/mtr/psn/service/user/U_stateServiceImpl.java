package com.mtr.psn.service.user;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.user.U_stateMapper;
import com.mtr.psn.model.user.U_state;

@Service
public class U_stateServiceImpl implements U_stateService {

	@Resource
	private U_stateMapper u_stateMapper;

	@Override
	public U_state selectById(Long id)throws Exception{
		return	u_stateMapper.selectById(id);
	}
	@Override
	public List<U_state> selectAll(U_state u_state)throws Exception{
		return	u_stateMapper.selectAll(u_state);
	}
	@Override
	public int psn_insert(U_state u_state)throws Exception{
		return	u_stateMapper.insert(u_state);
	}
	@Override
	public int psn_update(U_state u_state)throws Exception{
		return	u_stateMapper.update(u_state);
	}
	@Override
	public int psn_delete(U_state u_state)throws Exception{
		return	u_stateMapper.delete(u_state);
	}
}
