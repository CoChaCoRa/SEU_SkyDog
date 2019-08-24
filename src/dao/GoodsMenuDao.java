package dao;

import java.util.ArrayList;
import vo.GoodsInfo;
import vo.OrderInfo;
import vo.WishList;
import exception.*;

public interface GoodsMenuDao {
	/**
	 * 返回ArrayList<GoodsInfo>
	 * @return ArrayList<GoodsInfo>
	 */
	public ArrayList<GoodsInfo> getAllGoods();
	
	/**
	 * 传入goodsName,若goodsName不存在则返回null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<GoodsInfo> queryByGoodsName(String goodsName);
	
	/**
	 * 传入goodsSKU,若goodsSKU不存在则返回null
	 * @param String
	 * @return boolean
	 */
	public GoodsInfo queryByGoodsSKU(String goodsSKU);
	
	/**
	 * 传入GoodsInfo,若GoodsInfo已经存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertGoodsInfo(GoodsInfo goodsInfo)throws RecordAlreadyExistException;
	
	/**
	 * 传入GoodsInfo,若GoodsInfo不存在抛出异常,SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
	public boolean updateGoodsInfo(GoodsInfo goodsInfo)throws RecordNotFoundException;
	
	/**
	 * 传入goodsSKU，若goodsSKU不存在则抛出异常,SQL异常返回false
	 * @param goodsSKU
	 * @return boolean
	 * @throws RecordNotFoundException
	 * @throws RecordNotFoundException
	 */
    public boolean deleteWishList(String goodsSKU)throws RecordNotFoundException;
}
