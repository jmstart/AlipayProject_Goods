package com.jiaming.order.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaming.entity.Order;
import com.jiaming.entity.Orderitem;
import com.jiaming.mapper.CartitemMapper;
import com.jiaming.mapper.OrderMapper;
import com.jiaming.mapper.OrderitemMapper;

import povos.OrderDescPovo;
import povos.OrderPovo;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderitemMapper orderitemMapper;

	@Autowired
	private CartitemMapper cartitemMapper;

	public List<OrderPovo> getOrdersByUid(String uid) {
		// select * from `order` where uid = ?
		List<OrderPovo> orderpovos = orderMapper.selectOrdersByUid(uid);
		for (OrderPovo orderPovo : orderpovos) {
			// select * from orderitem where oid = ?
			orderPovo.setOrderitems(orderitemMapper.selectOrderitemsByOid(orderPovo.getOid()));
		}

		return orderpovos;
	}

	public List<OrderDescPovo> getOrderByOid(String oid) {

		// select * form `order` , orderitem where `order`.oid= orderitem.oid and
		// `order`.oid = ?

		return orderMapper.selectOrderAndOrderItemsByOid(oid);
	}

	public boolean changeStatus(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderByOidForStatus(order);

	}

	public OrderPovo createOrder(OrderPovo povo) {
		// insert order
		povo.setStatus(1);
		povo.setOrdertime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		orderMapper.insert(povo);
		List<String> bids = new ArrayList<String>();

		// 批量 insert orderitems.
		List<Orderitem> orderitems = povo.getOrderitems();
		for (Orderitem orderitem : orderitems) {
			bids.add(orderitem.getBid());
			orderitem.setOid(povo.getOid());
		}

		orderitemMapper.batchInsert(orderitems);

		// 批量删除 购物车条目

		// delete from cartitem where uid =? and bid in (......)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", povo.getUid());
		map.put("bids", bids);

		cartitemMapper.deleteByUidAndInBids(map);

		return povo;
	}

	public List<OrderPovo> showOrder() {
		// TODO Auto-generated method stub
		List<OrderPovo> orderpovos = orderMapper.selectAll();

		return orderpovos;
	}

}
