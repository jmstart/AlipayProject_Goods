package com.jiaming.cart.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaming.entity.Cartitem;
import com.jiaming.mapper.CartitemMapper;

import povos.CartPovo;

@Service
public class CartService {
	@Autowired
	private CartitemMapper cartitemMapper;

	public List<CartPovo> getCartitemsByUid(String uid) {

		return cartitemMapper.selectCartPovosByUid(uid);
	}

	public void addCartitem(Cartitem cartitem) {

		Cartitem c = cartitemMapper.selectCartitemByUidAndBid(cartitem);
		if (c == null) {
			cartitemMapper.insert(cartitem);

		} else {
			c.setQuantity(c.getQuantity() + cartitem.getQuantity());
			cartitemMapper.updateByPrimaryKey(c);
		}

		// res = select * from cartitem where uid = ? and bid = ?
		// if(res) 之前 有这个书 的对应的 购物车条目 -> update quantity 数量！
		// 否则 insert into 操作。

	}

	public Boolean removeCartByCartitemid(String cartitemid) {
		// TODO Auto-generated method stub
		return cartitemMapper.deleteByPrimaryKey(cartitemid);
	}

	public void removeByBatch(String[] ids) {
		// TODO Auto-generated method stub
		// delete from cartitem where cartitemId in (xxx,xx,xx)
		List<String> list = Arrays.asList(ids);

		cartitemMapper.deleteByBatchInCartitemids(list);
	}

	public Boolean changeQuantity(Cartitem cartitem) {
		// TODO Auto-generated method stub
		return cartitemMapper.updateQuantityByCaritemid(cartitem);
	}

	public List<CartPovo> preOrders(List<String> list) {
		return cartitemMapper.selectCartPovosInCartitems(list);
	}

}
