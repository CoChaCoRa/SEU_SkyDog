package dao;

import exception.*;
import vo.Wallet;

public interface WalletDao {
	/**
	 * ����username����������Wallet������MVO�����ڷ���null
	 * @param String
	 * @return BVO
	 */
	public Wallet selectWallet(String username);
	
	/**
	 * ����Wallet,��username�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertWallet(Wallet wallet)throws RecordAlreadyExistException,RecordNotFoundException;
	
	/**
	 * ����Wallet,��account�������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateWallet(Wallet wallet)throws RecordNotFoundException;
}
