package com.mtr.psn.service.member;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.member.FranchiseeMapper;
import com.mtr.psn.model.member.Franchisee;

@Service
public class FranchiseeServiceImpl implements FranchiseeService {

	@Resource
	private FranchiseeMapper franchiseeMapper;

	@Override
	public Franchisee selectById(Long id)throws Exception{
		return	franchiseeMapper.selectById(id);
	}
	@Override
	public List<Franchisee> selectAll(Franchisee franchisee)throws Exception{
		return	franchiseeMapper.selectAll(franchisee);
	}
	@Override
	public int psn_insert(Franchisee franchisee)throws Exception{
		return	franchiseeMapper.insert(franchisee);
	}
	@Override
	public int psn_update(Franchisee franchisee)throws Exception{
		return	franchiseeMapper.update(franchisee);
	}
	@Override
	public int psn_delete(Franchisee franchisee)throws Exception{
		return	franchiseeMapper.delete(franchisee);
	}
}
