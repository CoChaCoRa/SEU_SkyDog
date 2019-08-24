package dao;

import java.util.ArrayList;

import vo.DataDict;
import exception.*;

public interface DataIndexDao {
	/**
	 * 返回ArrayList<DataDict>
	 * @return ArrayList<DataDict>
	 */
	public ArrayList<DataDict> getAllData();
	
	/**
	 * 传入参数code，返回DataDict对象，若DataDict不存在返回null
	 * @param String
	 * @return DataDict
	 */
	public DataDict selectDataDict(int code);
	
	/**
	 * 传入DataDict,若DataDict已经存在则抛出异常，SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertDataDict(DataDict datadict)throws RecordAlreadyExistException;
	
	/**
	 * 传入DataDict更新DataDict，不存在则抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
    public boolean updateDataDict(DataDict datadict)throws RecordNotFoundException;
    
    /**
	 * 传入Code删除DataDict,SQL异常返回false
	 * @param String
	 * @return boolean
	 */
    public boolean deleteDataDict(int code);
}
