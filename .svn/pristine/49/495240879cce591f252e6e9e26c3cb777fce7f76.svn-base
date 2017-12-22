package com.mtr.psn.service.member;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.member.MerchantMapper;
import com.mtr.psn.model.member.Merchant;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Resource
	private MerchantMapper merchantMapper;

	@Override
	public Merchant selectById(Long id)throws Exception{
		return	merchantMapper.selectById(id);
	}
	@Override
	public List<Merchant> selectAll(Merchant merchant)throws Exception{
		return	merchantMapper.selectAll(merchant);
	}
	@Override
	public int psn_insert(Merchant merchant)throws Exception{
		return	merchantMapper.insert(merchant);
	}
	@Override
	public int psn_update(Merchant merchant)throws Exception{
		return	merchantMapper.update(merchant);
	}
	@Override
	public int psn_delete(Merchant merchant)throws Exception{
		return	merchantMapper.delete(merchant);
	}
}
