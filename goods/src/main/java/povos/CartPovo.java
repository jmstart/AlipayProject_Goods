package povos;

import java.math.BigDecimal;

import com.jiaming.entity.Book;
import com.jiaming.entity.Cartitem;

public class CartPovo extends Book {

	private Cartitem cartitem;

	public Cartitem getCartitem() {
		return cartitem;
	}

	public void setCartitem(Cartitem cartitem) {
		this.cartitem = cartitem;
	}

	public BigDecimal getSubTotal() {
		// 构造器 使用 字符串 初始化 不会 缺失精度
		BigDecimal quantity = new BigDecimal(cartitem.getQuantity().toString());

		return quantity.multiply(this.getCurrprice());

	}

}
