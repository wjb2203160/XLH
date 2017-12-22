package com.mtr.psn.mapper.goods;

import java.util.List;
import com.mtr.psn.model.goods.GoodsForm;

public interface GoodsFormMapper {

	public GoodsForm selectById(Long id)throws Exception;
	
	public long selectByName(String Name)throws Exception;

	public int count(GoodsForm goodsForm)throws Exception;

	public List<GoodsForm> getPagesList(GoodsForm goodsForm)throws Exception;

	public List<GoodsForm> selectAll(GoodsForm goodsForm)throws Exception;

	public int insert(GoodsForm goodsForm)throws Exception;

	public int update(GoodsForm goodsForm)throws Exception;

	public int delete(GoodsForm goodsForm)throws Exception;
	
	public List<GoodsForm> selectByGoodsType(Long goodsType)throws Exception;

}