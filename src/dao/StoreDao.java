package dao;

import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import vo.Store;

public interface StoreDao {
	/**
	 * 返回ArrayList<Store>对象
	 * @return ArrayList<Store>
	 */
	public ArrayList<Store> getAllStores();
	
	/**
	 * 传入storeName,若storeName不存在返回null
	 * @param String
	 * @return boolean
	 */
	public Store queryByStoreName(String storeName);
	
	/**
	 * 传入Store,若Store已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertStore(Store store)throws RecordAlreadyExistException;
}
