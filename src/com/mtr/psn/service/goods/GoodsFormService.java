package com.mtr.psn.service.goods;

import java.util.List;
import com.mtr.psn.model.goods.GoodsForm;
import com.mtr.psn.model.webEntity.GoodsFormEntity;

public interface GoodsFormService {

	public GoodsForm selectById(Long id)throws Exception;
	
	public long selectByName(String Name)throws Exception;

	public int count(GoodsForm goodsForm)throws Exception;

	public List<GoodsForm> getPagesList(GoodsForm goodsForm)throws Exception;

	public List<GoodsForm> selectAll(GoodsForm goodsForm)throws Exception;

	public int psn_insert(GoodsForm goodsForm)throws Exception;

	public int psn_update(GoodsForm goodsForm)throws Exception;

	public int psn_delete(GoodsForm goodsForm)throws Exception;
	
	public List<GoodsFormEntity> getPagesListEntity(GoodsForm goodsForm) throws Exception;
	/**
	 * 根据物资类型查询物品
	 * @param goodsType
	 * @return
	 * @throws Exception
	 */
	public List<GoodsForm> selectByGoodsType(Long goodsType)throws Exception;

}
