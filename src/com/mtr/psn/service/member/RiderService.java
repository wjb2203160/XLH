package com.mtr.psn.service.member;

import java.util.List;
import com.mtr.psn.model.member.Rider;

public interface RiderService {

	public Rider selectById(Long id)throws Exception;

	public long selectTeamId(Long id)throws Exception;
	
	public int count(Rider rider)throws Exception;

	public List<Rider> getPagesList(Rider rider)throws Exception;

	public List<Rider> selectAll(Rider rider)throws Exception;
	
	public Rider selectRiderName(Rider rider)throws Exception;
	
	public List<Rider> selectRider(Rider rider)throws Exception;

	public int psn_insert(Rider rider)throws Exception;

	public int psn_update(Rider rider)throws Exception;

	public int psn_delete(Rider rider)throws Exception;

}
