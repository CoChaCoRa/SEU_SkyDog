package dao;

import java.util.ArrayList;

import exception.OutOfLimitException;
import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import vo.OrderInfo;

public interface MVOOrderListDao {
	/**
	 * ����username,��username�����ڷ���null   (MVO���ܲ鿴�Լ���)
	 * @param String
	 * @return boolean
	 */
	public ArrayList<OrderInfo> queryByUsername(String username);
	
	/**
	 * ����goodsName,username,�������ڷ���null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<OrderInfo> queryByGoodsName(String goodsName,String username);
	
	/**
	 * ����orderID,username,�������ڷ���null
	 * @param String
	 * @return boolean
	 */
	public OrderInfo queryByOrderID(String orderID,String username);
	
	/**
	 * ����OrderInfo,��OrderInfo�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException,RecordNotFoundExceptionRecordAlreadyExistException
	 */
	public boolean insertOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException,RecordAlreadyExistException,OutOfLimitException;
	
	/**
	 * ����GoodsInfo,��GoodsInfo�������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateOrderInfo(OrderInfo orderInfo)throws RecordNotFoundException;
	
	/**
	 * ����GoodsInfo,��GoodsInfo�������׳��쳣,SQL�쳣����false (���Ǯ������ٿۿ��update,ͬʱ���Ķ���״̬)
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 * @throws OutOfLimitException
	 */
	public boolean payRequest(OrderInfo orderInfo)throws RecordNotFoundException,OutOfLimitException;
}
