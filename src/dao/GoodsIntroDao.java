package dao;

import java.util.ArrayList;
import vo.GoodsInfo;
import vo.MVO;
import exception.*;

public interface GoodsIntroDao {
	/**
	 * 返回ArrayList<GoodsInfo>对象
	 * @return ArrayList<GoodsInfo>
	 */
	public ArrayList<GoodsInfo> getAllGoods();
	
	/**
	 * 传入goodsName,若goodsName不存在返回null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<GoodsInfo> queryByGoodsName(String goodsName);
	
	/**
	 * 传入goodsSKU,若goodsSKU不存在返回null
	 * @param String
	 * @return boolean
	 */
	public GoodsInfo queryByGoodsSKU(int goodsSKU);
	
	/**
	 * 传入GoodsInfo,若GoodsInfo已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertGoodsInfo(GoodsInfo goodsInfo)throws RecordAlreadyExistException;
}
