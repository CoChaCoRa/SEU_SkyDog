package dao;

import exception.*;
import vo.MVO;

public interface MVODao {
	/**
	 * 传入CName_C参数，返回MVO对象，若MVO不存在返回null
	 * @param String
	 * @return MVO
	 */
	public MVO selectMVO(String username);
	
	/**
	 * 传入CName_C参数，返回MVO对象，若MVO不存在返回null
	 * @param String
	 * @return MVO
	 */
	public MVO selectMVOName(String CName_C);
	
	/**
	 * 传入MVO,若CName_C已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertMVO(MVO mvo)throws RecordAlreadyExistException,RecordNotFoundException;
	
}
