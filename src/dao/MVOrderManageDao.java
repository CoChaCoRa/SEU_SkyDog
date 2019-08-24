package dao;

import java.util.ArrayList;

import exception.*;
import vo.OrderInfo;

public interface MVOrderManageDao {
	
	/**
	 * ����ArrayList<OrderInfo>����
	 * @return ArrayList<OrderInfo>
	 */
	public ArrayList<OrderInfo> getAllOrders();
	
	/**
	 * ����goodsName,��goodsName�����ڷ���null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<OrderInfo> queryByGoodsName(String goodsName);
	
	/**
	 * ����orderID,��orderID�����ڷ���null
	 * @param String
	 * @return boolean
	 */
	public OrderInfo queryByOrderID(String orderID);
	
	/**
	 * ����OrderInfo,��OrderInfo�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException,RecordNotFoundExceptionRecordAlreadyExistException
	 */
	public boolean insertOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException,RecordAlreadyExistException,OutOfLimitException;
	
	/**
	 * ����OrderInfo,��OrderInfo�������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException;
	
}
