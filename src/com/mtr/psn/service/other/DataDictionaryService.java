package com.mtr.psn.service.other;

import java.util.List;
import com.mtr.psn.model.other.DataDictionary;

public interface DataDictionaryService {

	public DataDictionary selectById(Long id)throws Exception;

	public List<DataDictionary> selectAll(DataDictionary dataDictionary)throws Exception;

	public int psn_insert(DataDictionary dataDictionary)throws Exception;

	public int psn_update(DataDictionary dataDictionary)throws Exception;

	public int psn_delete(DataDictionary dataDictionary)throws Exception;
	
	/**
	 * 根据类型编码查询数据字典
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public List<DataDictionary> getDataByTypeCode(String typeCode) throws Exception;

	public List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary)throws Exception;
}
