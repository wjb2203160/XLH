package com.mtr.psn.service.member;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.member.RiderMapper;
import com.mtr.psn.model.member.Rider;

@Service
public class RiderServiceImpl implements RiderService {

	@Resource
	private RiderMapper riderMapper;

	@Override
	public Rider selectById(Long id)throws Exception{
		return	riderMapper.selectById(id);
	}
	@Override
	public long selectTeamId(Long id)throws Exception{
		return	riderMapper.selectTeamId(id);
	}
	@Override
	public int count(Rider rider)throws Exception{
		return	riderMapper.count(rider);
	}
	@Override
	public List<Rider> getPagesList(Rider rider)throws Exception{
		return	riderMapper.getPagesList(rider);
	}
	
	@Override
	public Rider selectRiderName(Rider rider)throws Exception{
		return	riderMapper.selectRiderName(rider);
	}
	@Override
	public List<Rider> selectAll(Rider rider)throws Exception{
		return	riderMapper.selectAll(rider);
	}
	@Override
	public int psn_insert(Rider rider)throws Exception{
		return	riderMapper.insert(rider);
	}
	@Override
	public int psn_update(Rider rider)throws Exception{
		return	riderMapper.update(rider);
	}
	@Override
	public int psn_delete(Rider rider)throws Exception{
		return	riderMapper.delete(rider);
	}
	@Override
	public List<Rider> selectRider(Rider rider)throws Exception{
		return	riderMapper.selectRider(rider);
	}
}
