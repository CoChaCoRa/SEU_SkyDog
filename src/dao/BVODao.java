package dao;

import exception.*;
import vo.BVO;

public interface BVODao {
	/**
	 * ����username����BVO,�������򷵻�null
	 * @param String
	 * @return BVO
	 */
	public BVO selectBVO(String username);
	
	/**
	 * ����name����BVO,�������򷵻�null
	 * @param String
	 * @return BVO
	 */
	public BVO selectBVOName(String name);
	
	/**
	 * ����BVO,��username������/name�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 * @throws RecordNotFoundException
	 */
	public boolean insertBVO(BVO bvo)throws RecordAlreadyExistException,RecordNotFoundException;
	
	/**
	 * ����BVO,��BVO�������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateBVO(BVO BVO)throws RecordNotFoundException;
	
}
