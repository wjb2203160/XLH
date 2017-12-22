package com.mtr.psn.service.orders;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.orders.ComplaintFormMapper;
 
import com.mtr.psn.model.orders.ComplaintForm;
 
import com.mtr.psn.model.orders.QueryComplaintForm;

@Service
public class ComplaintFormServiceImpl implements ComplaintFormService {

	@Resource
	private ComplaintFormMapper complaintFormMapper;

	@Override
	public ComplaintForm selectById(Long id)throws Exception{
		return	complaintFormMapper.selectById(id);
	}
	@Override
	public List<ComplaintForm> selectAll(ComplaintForm complaintForm)throws Exception{
		return	complaintFormMapper.selectAll(complaintForm);
	}
	@Override
	public List<ComplaintForm> selectComplaintFormList(QueryComplaintForm appealform)throws Exception{
		return	complaintFormMapper.selectComplaintFormList(appealform);
	}
	@Override
	public int Count(QueryComplaintForm appealform)throws Exception{
		return	complaintFormMapper.Count(appealform);
	}
	@Override
	public int psn_insert(ComplaintForm complaintForm)throws Exception{
		return	complaintFormMapper.insert(complaintForm);
	}
	@Override
	public int psn_update(ComplaintForm complaintForm)throws Exception{
		return	complaintFormMapper.update(complaintForm);
	}
	@Override
	public int psn_delete(ComplaintForm complaintForm)throws Exception{
		return	complaintFormMapper.delete(complaintForm);
	}
	@Override
	public List<ComplaintForm> selectMoneyByRider(QueryComplaintForm complaintForm)throws Exception{
		return	complaintFormMapper.selectMoneyByRider(complaintForm);
	}
}
