package dao;

import vo.*;
import exception.*;

public interface LoginDao {	
	/**
	 * ����username����������User������userName�����ڷ���null
	 * @param String
	 * @return User
	 */
	public User selectUser(String username);
	
	/**
	 * ����username,password,��userName�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertUser(String username,String password,String authentification)throws RecordAlreadyExistException;
	
}