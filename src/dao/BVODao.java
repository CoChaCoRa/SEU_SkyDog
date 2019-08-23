package dao;

import exception.*;
import vo.BVO;

public interface BVODao {
	/**
	 * 传入name参数，返回MVO对象，若MVO不存在返回null
	 * @param String
	 * @return BVO
	 */
	public BVO selectBVO(String name);
	
	/**
	 * 传入BVO,若name已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertBVO(BVO bvo)throws RecordAlreadyExistException,RecordNotFoundException;
	
}
