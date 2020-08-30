package povos;

import com.jiaming.entity.Order;
import com.jiaming.entity.Orderitem;

public class OrderDescPovo extends  Orderitem {
	private  Order  order ;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 
	 */
	public OrderDescPovo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param order
	 */
	public OrderDescPovo(Order order) {
		super();
		this.order = order;
	} 
	
	
	
}
