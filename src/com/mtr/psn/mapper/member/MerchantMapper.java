package com.mtr.psn.mapper.member;

import java.util.List;
import com.mtr.psn.model.member.Merchant;

public interface MerchantMapper {

	public Merchant selectById(Long id)throws Exception;

	public List<Merchant> selectAll(Merchant merchant)throws Exception;

	public int insert(Merchant merchant)throws Exception;

	public int update(Merchant merchant)throws Exception;

	public int delete(Merchant merchant)throws Exception;

}
