package com.mtr.psn.service.user;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.user.AuthorityMapper;
import com.mtr.psn.model.user.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Resource
	private AuthorityMapper authorityMapper;

	@Override
	public Authority selectById(Long id)throws Exception{
		return	authorityMapper.selectById(id);
	}
	@Override
	public List<Authority> selectAll(Authority authority)throws Exception{
		return	authorityMapper.selectAll(authority);
	}
	@Override
	public int psn_insert(Authority authority)throws Exception{
		return	authorityMapper.insert(authority);
	}
	@Override
	public int psn_update(Authority authority)throws Exception{
		return	authorityMapper.update(authority);
	}
	@Override
	public int psn_delete(Authority authority)throws Exception{
		return	authorityMapper.delete(authority);
	}
}
