package com.jiaming.mapper;

import com.jiaming.entity.Cartitem;

import povos.CartPovo;

import java.util.List;
import java.util.Map;

public interface CartitemMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table cartitem
	 *
	 * @mbggenerated Tue Jun 23 15:02:12 CST 2020
	 */
	boolean deleteByPrimaryKey(String cartitemid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table cartitem
	 *
	 * @mbggenerated Tue Jun 23 15:02:12 CST 2020
	 */
	int insert(Cartitem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table cartitem
	 *
	 * @mbggenerated Tue Jun 23 15:02:12 CST 2020
	 */
	Cartitem selectByPrimaryKey(String cartitemid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table cartitem
	 *
	 * @mbggenerated Tue Jun 23 15:02:12 CST 2020
	 */
	List<Cartitem> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table cartitem
	 *
	 * @mbggenerated Tue Jun 23 15:02:12 CST 2020
	 */
	int updateByPrimaryKey(Cartitem record);

	List<CartPovo> selectCartPovosByUid(String uid);

	Cartitem selectCartitemByUidAndBid(Cartitem cartitem);

	void deleteByBatchInCartitemids(List<String> list);

	boolean updateQuantityByCaritemid(Cartitem cartitem);

	List<CartPovo> selectCartPovosInCartitems(List<String> list);
	
	void deleteByUidAndInBids(Map<String, Object> map);
}