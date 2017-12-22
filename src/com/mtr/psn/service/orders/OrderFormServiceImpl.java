package com.mtr.psn.service.orders;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.orders.OrderFormMapper;
import com.mtr.psn.model.orders.OrderForm;
import com.mtr.psn.model.orders.QueryOrder;

@Service
public class OrderFormServiceImpl implements OrderFormService {
	@Resource
	private OrderFormMapper orderFormMapper;

	@Override
	public OrderForm selectById(Long id)throws Exception{
		return	orderFormMapper.selectById(id);
	}
	@Override
	public List<OrderForm> selectAll(OrderForm orderForm)throws Exception{
		return	orderFormMapper.selectAll(orderForm);
	}
	@Override
	public int psn_insert(OrderForm orderForm)throws Exception{
		return	orderFormMapper.insert(orderForm);
	}
	@Override
	public int psn_update(OrderForm orderForm)throws Exception{
		return	orderFormMapper.update(orderForm);
	}
	@Override
	public int psn_delete(OrderForm orderForm)throws Exception{
		return	orderFormMapper.delete(orderForm);
	}
	@Override
	public void deleteAll()throws Exception{
		 orderFormMapper.deleteAll();
	}
	@Override
	public int CountDayByRider(QueryOrder queryOrder)throws Exception{
		return	orderFormMapper.CountDayByRider(queryOrder);
	}
	@Override
	public int CountNorNumByRider(QueryOrder queryOrder)throws Exception{
		return	orderFormMapper.CountNorNumByRider(queryOrder);
	}
	@Override
	public List<OrderForm> selectOrderFormList(QueryOrder queryOrder)throws Exception{
		return	orderFormMapper.selectOrderFormList(queryOrder);
	}
}
