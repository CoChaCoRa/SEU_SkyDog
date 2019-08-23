package dao;

import exception.*;
import vo.MVO;

public interface MVODao {
	/**
	 * ����CName_C����������MVO������MVO�����ڷ���null
	 * @param String
	 * @return MVO
	 */
	public MVO selectMVO(String CName_C);
	
	/**
	 * ����MVO,��CName_C�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException,RecordNotFoundException
	 */
	public boolean insertMVO(MVO mvo)throws RecordAlreadyExistException,RecordNotFoundException;
	
}