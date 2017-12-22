package com.mtr.psn.mapper.user;

import java.util.List;
import com.mtr.psn.model.user.UserType;

public interface UserTypeMapper {

	public UserType selectById(Long id)throws Exception;

	public List<UserType> selectAll(UserType userType)throws Exception;

	public int insert(UserType userType)throws Exception;

	public int update(UserType userType)throws Exception;

	public int delete(UserType userType)throws Exception;

}
