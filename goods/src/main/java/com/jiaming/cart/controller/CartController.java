package com.jiaming.cart.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiaming.cart.service.CartService;
import com.jiaming.entity.Cartitem;
import com.jiaming.entity.User;

import povos.CartPovo;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@RequestMapping("/getCartitemsByUid/{uid}.action")
	public String getCartitemsByUid(Model model, @PathVariable String uid) {

		List<CartPovo> povos = cartService.getCartitemsByUid(uid);

		model.addAttribute("cartitempovos", povos);

		return "jsps/cart/list";
	}

	@PostMapping("/addCartitem.action")
	public String addCartitem(Cartitem cartitem) {

		cartService.addCartitem(cartitem);

		return "redirect:/cart/getCartitemsByUid/" + cartitem.getUid() + ".action";
	}

	@RequestMapping("/removeCartByCartitemid.action")
	@ResponseBody
	public Boolean removeCartByCartitemid(String cartitemid) {

		return cartService.removeCartByCartitemid(cartitemid);
	}

	@GetMapping("/removeByBatch.action")
	public String removeByBatch(String[] ids, HttpSession session) {

		String uid = ((User) session.getAttribute("user")).getUid();

		cartService.removeByBatch(ids);

		return "redirect:/cart/getCartitemsByUid/" + uid + ".action";
	}

	@PostMapping("/changeQuantity.action")
	@ResponseBody
	public Boolean changeQuantity(Cartitem cartitem) {

		return cartService.changeQuantity(cartitem);

	}

	@GetMapping("/preOrders.action")
	public String preOrders(String[] ids, Model model) {

		List<String> list = Arrays.asList(ids);

		List<CartPovo> povos = cartService.preOrders(list);

		model.addAttribute("povos", povos);
		return "jsps/cart/showitem";
	}

}
