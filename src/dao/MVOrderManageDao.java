package dao;

import java.util.ArrayList;

import exception.*;
import vo.OrderInfo;

public interface MVOrderManageDao {
	
	/**
	 * 返回ArrayList<OrderInfo>对象
	 * @return ArrayList<OrderInfo>
	 */
	public ArrayList<OrderInfo> getAllOrders();
	
	/**
	 * 传入goodsName,若goodsName不存在返回null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<OrderInfo> queryByGoodsName(String goodsName);
	
	/**
	 * 传入orderID,若orderID不存在返回null
	 * @param String
	 * @return boolean
	 */
	public OrderInfo queryByOrderID(String orderID);
	
	/**
	 * 传入OrderInfo,若OrderInfo已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException,RecordNotFoundExceptionRecordAlreadyExistException
	 */
	public boolean insertOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException,RecordAlreadyExistException,OutOfLimitException;
	
	/**
	 * 传入OrderInfo,若OrderInfo不存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException;
	
}
