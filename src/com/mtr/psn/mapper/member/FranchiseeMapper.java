package com.mtr.psn.mapper.member;

import java.util.List;
import com.mtr.psn.model.member.Franchisee;

public interface FranchiseeMapper {

	public Franchisee selectById(Long id)throws Exception;

	public List<Franchisee> selectAll(Franchisee franchisee)throws Exception;

	public int insert(Franchisee franchisee)throws Exception;

	public int update(Franchisee franchisee)throws Exception;

	public int delete(Franchisee franchisee)throws Exception;

}
