package dao;

import java.util.ArrayList;
import vo.DataDict;
import exception.*;

public interface DataIndexDao {
	/**
	 * 返回ArrayList<DataDict>对象
	 * @return ArrayList<DataDict>
	 */
	public ArrayList<DataDict> getAllData();
	
	/**
	 * 插入DataDict,如果DataDict已经存在则抛出异常,插入失败返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertDataDict(DataDict datadict);
	
	/**
	 * 更新DataDict，若DataDict不存在则抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
    public boolean updateDataDict(DataDict datadict);
    
    /**
	 * 删除DataDict，若DataDict不存在则抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
    public boolean deleteDataDict(DataDict datadict);
}
