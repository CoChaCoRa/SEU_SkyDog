package dao;

import exception.*;
import vo.BVO;

public interface BVODao {
	/**
	 * ����name����������MVO������MVO�����ڷ���null
	 * @param String
	 * @return BVO
	 */
	public BVO selectBVO(String name);
	
	/**
	 * ����BVO,��name�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertBVO(BVO bvo)throws RecordAlreadyExistException,RecordNotFoundException;
	
}
