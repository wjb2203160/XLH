package com.mtr.psn.service.member;

import java.util.List;
import com.mtr.psn.model.member.Merchant;

public interface MerchantService {

	public Merchant selectById(Long id)throws Exception;

	public List<Merchant> selectAll(Merchant merchant)throws Exception;

	public int psn_insert(Merchant merchant)throws Exception;

	public int psn_update(Merchant merchant)throws Exception;

	public int psn_delete(Merchant merchant)throws Exception;

}
