package com.mtr.psn.service.member;

import java.util.List;
import com.mtr.psn.model.member.Franchisee;

public interface FranchiseeService {

	public Franchisee selectById(Long id)throws Exception;

	public List<Franchisee> selectAll(Franchisee franchisee)throws Exception;

	public int psn_insert(Franchisee franchisee)throws Exception;

	public int psn_update(Franchisee franchisee)throws Exception;

	public int psn_delete(Franchisee franchisee)throws Exception;

}
