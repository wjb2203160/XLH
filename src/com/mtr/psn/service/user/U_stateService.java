package com.mtr.psn.service.user;

import java.util.List;
import com.mtr.psn.model.user.U_state;

public interface U_stateService {

	public U_state selectById(Long id)throws Exception;

	public List<U_state> selectAll(U_state u_state)throws Exception;

	public int psn_insert(U_state u_state)throws Exception;

	public int psn_update(U_state u_state)throws Exception;

	public int psn_delete(U_state u_state)throws Exception;

}
