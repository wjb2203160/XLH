package com.mtr.psn.service.user;

import java.util.List;
import com.mtr.psn.model.user.UserType;

public interface UserTypeService {

	public UserType selectById(Long id)throws Exception;

	public List<UserType> selectAll(UserType userType)throws Exception;

	public int psn_insert(UserType userType)throws Exception;

	public int psn_update(UserType userType)throws Exception;

	public int psn_delete(UserType userType)throws Exception;

}
