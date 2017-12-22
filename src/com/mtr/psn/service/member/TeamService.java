package com.mtr.psn.service.member;

import java.util.List;
import com.mtr.psn.model.member.Team;

public interface TeamService {

	public Team selectById(Long id)throws Exception;

	public List<Team> selectAll(Team team)throws Exception;

	public int psn_insert(Team team)throws Exception;

	public int psn_update(Team team)throws Exception;

	public int psn_delete(Team team)throws Exception;

}
