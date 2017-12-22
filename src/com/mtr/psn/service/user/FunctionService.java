package com.mtr.psn.service.user;

import java.util.List;

import com.mtr.psn.model.user.Authority;
import com.mtr.psn.model.user.Function;

public interface FunctionService {

	public Function selectById(Long id)throws Exception;

	public List<Function> selectAll(Function function)throws Exception;

	public int psn_insert(Function function)throws Exception;

	public int psn_update(Function function)throws Exception;

	public int psn_delete(Function function)throws Exception;
	/**
	 * 获得主菜单
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public List<Function> getMainFunctionList(Authority authority)throws Exception;
	/**
	 * 某个主功能下的子菜单列表
	 * @param function
	 * @return
	 * @throws Exception
	 */
	public List<Function> getSubFunctionList(Function function)throws Exception;

}
