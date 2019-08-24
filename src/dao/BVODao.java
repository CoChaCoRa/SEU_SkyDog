package dao;

import exception.*;
import vo.BVO;

public interface BVODao {
	/**
	 * 传入username返回BVO,不存在则返回null
	 * @param String
	 * @return BVO
	 */
	public BVO selectBVO(String username);
	
	/**
	 * 传入name返回BVO,不存在则返回null
	 * @param String
	 * @return BVO
	 */
	public BVO selectBVOName(String name);
	
	/**
	 * 传入BVO,若username不存在/name已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertBVO(BVO bvo)throws RecordAlreadyExistException,RecordNotFoundException;
	
	/**
	 * 传入BVO,若BVO不存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateBVO(BVO BVO)throws RecordNotFoundException;
	
}
