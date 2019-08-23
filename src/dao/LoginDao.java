package dao;

import vo.*;
import exception.*;

public interface LoginDao {	
	/**
	 * 传入username参数，返回User对象，若userName不存在返回null
	 * @param String
	 * @return User
	 */
	public User selectUser(String username);
	
	/**
	 * 传入username,password,若userName已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertUser(String username,String password,String authentification)throws RecordAlreadyExistException;
	
}