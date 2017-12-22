package com.mtr.psn.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.user.UserMapper;
import com.mtr.psn.model.user.U_state;
import com.mtr.psn.model.user.User;
import com.mtr.psn.model.webEntity.UserEntity;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private U_stateService stateService;

	@Override
	public User selectById(Long id)throws Exception{
		return	userMapper.selectById(id);
	}
	@Override
	public int count(User user)throws Exception{
		return	userMapper.count(user);
	}
	@Override
	public List<User> getPagesList(User user)throws Exception{
		return	userMapper.getPagesList(user);
	}
	@Override
	public List<User> selectAll(User user)throws Exception{
		return	userMapper.selectAll(user);
	}
	@Override
	public int psn_insert(User user)throws Exception{
		return	userMapper.insert(user);
	}
	@Override
	public int psn_update(User user)throws Exception{
		return	userMapper.update(user);
	}
	@Override
	public int psn_delete(User user)throws Exception{
		return	userMapper.delete(user);
	}
	@Override
	public int delUserPic(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.delUserPic(user);
	}
	@Override
	public User selectByPhone(String cellphone) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectByPhone(cellphone);
	}
	@Override
	public User selectUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectUser(user);
	}
	
	/**
	 * 包装用户列表
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UserEntity> getUserEntities(User user) throws Exception{
		List<UserEntity> userEntities = new ArrayList<>();
		List<User> uList =getPagesList(user);
		if(uList.size() != 0){
			for (int i = 0; i < uList.size(); i++) {
				UserEntity entity = new UserEntity(uList.get(i));
				/*//用户类型
				if(uList.get(i).getUserTypeId() != null){
					DataDictionary userType = null;
					userType = dictionaryService.selectById(uList.get(i).getUserTypeId());
					if(userType != null)
						entity.setUserType(userType.getValueName());
				}*/
				//用户状态
				if(uList.get(i).getStateId() != null){
					U_state state = null;
					state = stateService.selectById(uList.get(i).getStateId());
					if(state != null)
						entity.setState(state.getState());
				}
				userEntities.add(entity);
			}
		}
		return userEntities;
	}
}
