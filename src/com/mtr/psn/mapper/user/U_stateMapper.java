package com.mtr.psn.mapper.user;

import java.util.List;
import com.mtr.psn.model.user.U_state;

public interface U_stateMapper {

	public U_state selectById(Long id)throws Exception;

	public List<U_state> selectAll(U_state u_state)throws Exception;

	public int insert(U_state u_state)throws Exception;

	public int update(U_state u_state)throws Exception;

	public int delete(U_state u_state)throws Exception;

}
