package com.mtr.psn.service.goods;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.goods.GoodsRegistrationFormMapper;
import com.mtr.psn.model.goods.GoodsRegistrationForm;
import com.mtr.psn.model.goods.QueryRegistrationForm;
import com.mtr.psn.model.webEntity.GoodsRegistrationFormEntiy;

@Service
public class GoodsRegistrationFormServiceImpl implements GoodsRegistrationFormService {

	@Resource
	private GoodsRegistrationFormMapper goodsRegistrationFormMapper;

	@Override
	public GoodsRegistrationForm selectById(Long id)throws Exception{
		return	goodsRegistrationFormMapper.selectById(id);
	}
	@Override
	public int count(GoodsRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.count(goodsRegistrationForm);
	}
	@Override
	public List<GoodsRegistrationFormEntiy> getPagesList(GoodsRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.getPagesList(goodsRegistrationForm);
	}
	@Override
	public List<GoodsRegistrationForm> selectAll(GoodsRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.selectAll(goodsRegistrationForm);
	}
	@Override
	public List<GoodsRegistrationForm> selectClaimantBy(QueryRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.selectClaimantBy(goodsRegistrationForm);
	}
	@Override
	public int psn_insert(GoodsRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.insert(goodsRegistrationForm);
	}
	@Override
	public int psn_update(GoodsRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.update(goodsRegistrationForm);
	}
	@Override
	public int psn_delete(GoodsRegistrationForm goodsRegistrationForm)throws Exception{
		return	goodsRegistrationFormMapper.delete(goodsRegistrationForm);
	}
	
	
	
}
