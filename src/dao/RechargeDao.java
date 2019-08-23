package dao;

import java.util.ArrayList;

import vo.Parameter;
import vo.Recharge;
import exception.*;

public interface RechargeDao {
	/**
	 * 杩斿洖ArrayList<Recharge>瀵硅薄
	 * @return ArrayList<Recharge>
	 */
	public ArrayList<Recharge> getAllRecharge();
	
	/**
	 * 鎻掑叆Recharge,濡傛灉Recharge宸茬粡瀛樺湪鍒欐姏鍑哄紓甯�,鎻掑叆澶辫触杩斿洖false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertRecharge(Recharge recharge);
	
	/**
	 * 鏇存柊Recharge锛岃嫢Recharge涓嶅瓨鍦ㄥ垯鎶涘嚭寮傚父,SQL寮傚父杩斿洖false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
    public boolean updateRecharge(Recharge recharge);
    
    
    
	public boolean deleteParameter(Parameter parameter);

}
