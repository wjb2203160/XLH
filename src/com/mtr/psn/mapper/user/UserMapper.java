package com.mtr.psn.mapper.user;

import java.util.List;
import com.mtr.psn.model.user.User;

public interface UserMapper {


	public int count(User user)throws Exception;
	
	public int insert(User user)throws Exception;
	
	public int update(User user)throws Exception;
	
	public int delete(User user)throws Exception;
	
	public User selectById(Long id)throws Exception;
	
	public List<User> selectAll(User user)throws Exception;
	
	public User selectByPhone(String cellphone)throws Exception;

	public User selectUser(User user)throws Exception;

	public List<User> getPagesList(User user)throws Exception;

	/**
	 * 更新用户（删除用户图片）
	 * @return
	 * @throws Exception
	 */
	public int delUserPic(User user)throws Exception;

}
