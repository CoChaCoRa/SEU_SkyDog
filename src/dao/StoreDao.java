package dao;

import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import vo.Store;

public interface StoreDao {
	/**
	 * ����ArrayList<Store>����
	 * @return ArrayList<Store>
	 */
	public ArrayList<Store> getAllStores();
	
	/**
	 * ����storeName,��storeName�����ڷ���null
	 * @param String
	 * @return boolean
	 */
	public Store queryByStoreName(String storeName);
	
	/**
	 * ����Store,��Store�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertStore(Store store)throws RecordAlreadyExistException;
}
