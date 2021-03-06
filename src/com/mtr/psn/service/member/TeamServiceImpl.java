package com.mtr.psn.service.member;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.member.TeamMapper;
import com.mtr.psn.model.member.Team;

@Service
public class TeamServiceImpl implements TeamService {

	@Resource
	private TeamMapper teamMapper;

	@Override
	public Team selectById(Long id)throws Exception{
		return	teamMapper.selectById(id);
	}
	@Override
	public List<Team> selectAll(Team team)throws Exception{
		return	teamMapper.selectAll(team);
	}
	@Override
	public int psn_insert(Team team)throws Exception{
		return	teamMapper.insert(team);
	}
	@Override
	public int psn_update(Team team)throws Exception{
		return	teamMapper.update(team);
	}
	@Override
	public int psn_delete(Team team)throws Exception{
		return	teamMapper.delete(team);
	}
}
