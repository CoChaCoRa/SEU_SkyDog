package dao;

import java.sql.Date;
import java.util.ArrayList;

import vo.Recharge;
import exception.*;

public interface RechargeDao {
	/**
	 * 返回ArrayList<Recharge>对象
	 * @return ArrayList<Recharge>
	 */
	public ArrayList<Recharge> getAllRecharge();
	
	/**
	 * 传入username,op_time,若不存在返回null
	 * @param String
	 * @return boolean
	 */
	public Recharge queryByNameTime(String username,Date time);
	
	/**
	 * 插入Recharge,如果Recharge已经存在则抛出异常,插入失败返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertRecharge(Recharge recharge)throws RecordAlreadyExistException;
	
	/**
	 * Recharge审核通过/不通过，若Recharge不存在则抛出异常,SQL异常返回false  
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 * @throws OutOfLimitException
	 */
    public boolean addressRecharge(Recharge recharge)throws RecordNotFoundException,OutOfLimitException;
    
    
}
