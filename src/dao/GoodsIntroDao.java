package dao;

import java.util.ArrayList;
import vo.GoodsInfo;
import vo.MVO;
import exception.*;

public interface GoodsIntroDao {
	/**
	 * ����ArrayList<GoodsInfo>����
	 * @return ArrayList<GoodsInfo>
	 */
	public ArrayList<GoodsInfo> getAllGoods();
	
	/**
	 * ����goodsName,��goodsName�����ڷ���null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<GoodsInfo> queryByGoodsName(String goodsName);
	
	/**
	 * ����goodsSKU,��goodsSKU�����ڷ���null
	 * @param String
	 * @return boolean
	 */
	public GoodsInfo queryByGoodsSKU(int goodsSKU);
	
	/**
	 * ����GoodsInfo,��GoodsInfo�Ѿ������׳��쳣,SQL�쳣����false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertGoodsInfo(GoodsInfo goodsInfo)throws RecordAlreadyExistException;
}
