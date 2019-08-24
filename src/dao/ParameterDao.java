package dao;

import java.util.ArrayList;
import vo.Parameter;
import exception.*;

public interface ParameterDao {
	/**
	 * 返回ArrayList<Parameter>
	 * @return ArrayList<Parameter>
	 */
	public ArrayList<Parameter> getAllParameter();
	
	/**
	 * 传入key,若key不存在返回null
	 * @param String
	 * @return boolean
	 */
	public Parameter queryByKey(String key);
	
	/**
	 * 传入Parameter,若Parameter存在则抛出异常，SQL异常返回false
	 * @param String
	 * @return boolean
	 * @throws RecordAlreadyExistException
	 */
	public boolean insertParameter(Parameter parameter)throws RecordAlreadyExistException;
	
	/**
	 * 传入Parameter若Parameter不存在则抛出异常,SQL错误返回false
	 * @param String
	 * @return boolean
	 * @throws RecordNotFoundException
	 */
    public boolean updateParameter(Parameter parameter)throws RecordNotFoundException;
    
    /**
	 * 传入key，若key不存在则抛出异常,SQL错误返回false
	 * @param String
	 * @return boolean
	 */
    public boolean deleteParameter(String key);
}
