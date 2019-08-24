package dao;

import java.util.ArrayList;

import exception.OutOfLimitException;
import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import vo.OrderInfo;

public interface MVOOrderListDao {
	/**
	 * 传入username,若username不存在返回null   (MVO仅能查看自己的)
	 * @param String
	 * @return boolean
	 */
	public ArrayList<OrderInfo> queryByUsername(String username);
	
	/**
	 * 传入goodsName,username,若不存在返回null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<OrderInfo> queryByGoodsName(String goodsName,String username);
	
	/**
	 * 传入orderID,username,若不存在返回null
	 * @param String
	 * @return boolean
	 */
	public OrderInfo queryByOrderID(String orderID,String username);
	
	/**
	 * 传入OrderInfo,若OrderInfo已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException,RecordNotFoundExceptionRecordAlreadyExistException
	 */
	public boolean insertOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException,RecordAlreadyExistException,OutOfLimitException;
	
	/**
	 * 传入GoodsInfo,若GoodsInfo不存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException;
	
	/**
	 * 传入GoodsInfo,若GoodsInfo不存在抛出异常,SQL异常返回false (检查钱包余额再扣款的update,同时更改订单状态)
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 * @throws OutOfLimitException
	 */
	public boolean payRequest(OrderInfo orderInfo)throws RecordNotFoundException,OutOfLimitException;
}
