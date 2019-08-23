package dao;

import java.util.ArrayList;
import vo.WishList;
import exception.*;

public interface WishListDao {
	
	/**
	 * 传入username,若username不存在则返回null
	 * @param String
	 * @return boolean
	 */
	public ArrayList<WishList> queryByUsername(String username);
	
	/**
	 * 传入WishList,若WishList存在则抛出异常,SQL异常返回false
	 * @param WishList
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertWishList(WishList wishlist)throws RecordAlreadyExistException;
	
	/**
	 * 传入WishList，若WishList不存在则抛出异常,SQL异常返回false
	 * @param WishList
	 * @return boolean
	 * @throws RecordNotFoundException
	 * @throws RecordNotFoundException
	 */
    public boolean deleteWishList(WishList wishlist)throws RecordNotFoundException;
}
