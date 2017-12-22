package com.mtr.psn.service.other;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mtr.psn.mapper.other.DataDictionaryMapper;
import com.mtr.psn.model.other.DataDictionary;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Resource
	private DataDictionaryMapper dataDictionaryMapper;

	@Override
	public DataDictionary selectById(Long id)throws Exception{
		return	dataDictionaryMapper.selectById(id);
	}
	@Override
	public List<DataDictionary> selectAll(DataDictionary dataDictionary)throws Exception{
		return	dataDictionaryMapper.selectAll(dataDictionary);
	}
	@Override
	public int psn_insert(DataDictionary dataDictionary)throws Exception{
		return	dataDictionaryMapper.insert(dataDictionary);
	}
	@Override
	public int psn_update(DataDictionary dataDictionary)throws Exception{
		return	dataDictionaryMapper.update(dataDictionary);
	}
	@Override
	public int psn_delete(DataDictionary dataDictionary)throws Exception{
		return	dataDictionaryMapper.delete(dataDictionary);
	}

	@Override
	public List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary) throws Exception {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.getDataDictionaries(dataDictionary);
	}

	/**
	 * 根据类型编码查询数据字典
	 * @param typeCode
	 * @return
	 * @throws Exception
	 */
	public List<DataDictionary> getDataByTypeCode(String typeCode) throws Exception{
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setTypeCode("GOODS_TYPE");
		return selectAll(dataDictionary);
	}

}
