package com.mtr.psn.service.user;

import java.util.List;
import com.mtr.psn.model.user.User;
import com.mtr.psn.model.webEntity.UserEntity;

public interface UserService {
	/**
	 * 更新用户（删除用户图片）
	 * @return
	 * @throws Exception
	 */
	public int delUserPic(User user)throws Exception;
	
	public User selectByPhone(String cellphone)throws Exception;

	public User selectUser(User user)throws Exception;

	public User selectById(Long id)throws Exception;

	public int count(User user)throws Exception;

	public List<User> getPagesList(User user)throws Exception;

	public List<User> selectAll(User user)throws Exception;

	public int psn_insert(User user)throws Exception;

	public int psn_update(User user)throws Exception;

	public int psn_delete(User user)throws Exception;
	
	public List<UserEntity> getUserEntities(User user) throws Exception;

}
