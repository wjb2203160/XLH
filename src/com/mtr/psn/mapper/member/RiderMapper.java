package com.mtr.psn.mapper.member;

import java.util.List;
import com.mtr.psn.model.member.Rider;

public interface RiderMapper {

	public Rider selectById(Long id)throws Exception;
	
	public long selectTeamId(Long id)throws Exception;

	public int count(Rider rider)throws Exception;

	public List<Rider> getPagesList(Rider rider)throws Exception;
	
	public Rider selectRiderName(Rider rider)throws Exception;
	
	public List<Rider> selectAll(Rider rider)throws Exception;
	
	public List<Rider> selectRider(Rider rider)throws Exception;

	public int insert(Rider rider)throws Exception;

	public int update(Rider rider)throws Exception;

	public int delete(Rider rider)throws Exception;

}
