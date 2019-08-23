package dao;

import exception.*;
import vo.Wallet;

public interface WalletDao {
	/**
	 * 传入account参数，返回Wallet对象，若MVO不存在返回null
	 * @param String
	 * @return BVO
	 */
	public Wallet selectWallet(String account);
	
	/**
	 * 传入Wallet,若account已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertWallet(Wallet wallet)throws RecordAlreadyExistException,RecordNotFoundException;
	
	/**
	 * 传入Wallet,若account不存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateWallet(Wallet wallet)throws RecordNotFoundException;
}
