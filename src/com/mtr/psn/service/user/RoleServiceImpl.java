package com.mtr.psn.service.user;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.user.RoleMapper;
import com.mtr.psn.model.user.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;

	@Override
	public Role selectById(Long id)throws Exception{
		return	roleMapper.selectById(id);
	}
	@Override
	public List<Role> selectAll(Role role)throws Exception{
		return	roleMapper.selectAll(role);
	}
	@Override
	public int psn_insert(Role role)throws Exception{
		return	roleMapper.insert(role);
	}
	@Override
	public int psn_update(Role role)throws Exception{
		return	roleMapper.update(role);
	}
	@Override
	public int psn_delete(Role role)throws Exception{
		return	roleMapper.delete(role);
	}
}
