package com.mtr.psn.mapper.orders;

import java.util.List;
import com.mtr.psn.model.orders.OrderForm;
import com.mtr.psn.model.orders.QueryOrder;

public interface OrderFormMapper {

	public OrderForm selectById(Long id)throws Exception;

	public List<OrderForm> selectAll(OrderForm orderForm)throws Exception;
	
	public List<OrderForm> selectOrderFormList(QueryOrder queryOrder)throws Exception;

	public int CountDayByRider(QueryOrder queryOrder)throws Exception;
	
	public int CountNorNumByRider(QueryOrder queryOrder)throws Exception;
	
	public int insert(OrderForm orderForm)throws Exception;

	public int update(OrderForm orderForm)throws Exception;

	public int delete(OrderForm orderForm)throws Exception;
	
	public void deleteAll()throws Exception;
	
}
