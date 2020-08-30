package com.jiaming.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaming.entity.Order;
import com.jiaming.order.service.OrderService;

import povos.OrderDescPovo;
import povos.OrderPovo;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/return_url.action")
	public String return_url(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		// 获取 订单号
		String oid = request.getParameter("out_trade_no");

		// 修改 对应订单的 状态 1- 》 2
		Order order = new Order();
		order.setOid(oid);
		order.setStatus(2);
		boolean res = orderService.changeStatus(order);
		if (res) {
			model.addAttribute("msg", "支付成功");
			model.addAttribute("code", "success");

		} else {
			model.addAttribute("msg", "支付失败");
			model.addAttribute("code", "error");
		}
		return "jsps/msg";
		// return "redirect:/order/getOrderByOid/"+order.getOid()+".action";

	}

	@RequestMapping("/pay.action")
	public void pay(HttpServletRequest request, HttpServletResponse response, Order order) throws Exception {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		// alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = order.getOid();
		// 付款金额，必填
		String total_amount = order.getTotal().toString();
		// 订单名称，必填
		String subject = "哈信息17级实训--" + order.getOid();
		// 商品描述，可空
		String body = "哈信息17级实训--" + new Date();

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		response.setContentType("text/html;charset=utf-8");
		// 输出
		response.getWriter().println(result);
	}

	@RequestMapping("/prePay.action")
	public String preOrder(Order order, Model model) {

		model.addAttribute("order", order);

		return "jsps/order/pay";
	}

	@RequestMapping("/createOrder.action")
	@ResponseBody
	public ModelAndView createOrder(OrderPovo povo) {
		ModelAndView mav = new ModelAndView("jsps/order/ordersucc");
		// mav.setViewName("jsps/order/ordersucc");

		povo = orderService.createOrder(povo);
		mav.addObject("orderpovo", povo);
		return mav;
	}

	@RequestMapping("/changeStatus.action")
	@ResponseBody
	public ModelAndView changeStatus(Order order) {
		System.out.println(order.getStatus() + "---------------------");
		orderService.changeStatus(order);
		ModelAndView mav = new ModelAndView();
		// 视图转换器 规则 写法
		mav.setViewName("jsps/order/desc");

		List<OrderDescPovo> order_desc_povos = orderService.getOrderByOid(order.getOid());

		if (order_desc_povos != null && order_desc_povos.size() != 0) {

			order = order_desc_povos.get(0).getOrder();
			mav.addObject("order", order);
		}
		mav.addObject("orderdescpovos", order_desc_povos);

		return mav;
		// return "redirect:/order/getOrdersByUid/"+order.getUid()+".action";
	}

	@GetMapping("/getOrderByOid/{oid}.action")
	public String getOrderByOid(@PathVariable String oid, Model model) {

		List<OrderDescPovo> order_desc_povos = orderService.getOrderByOid(oid);
		model.addAttribute("orderdescpovos", order_desc_povos);
		if (order_desc_povos != null && order_desc_povos.size() != 0) {

			Order order = order_desc_povos.get(0).getOrder();
			model.addAttribute("order", order);
		}

		return "jsps/order/desc";
	}

	private void getUrl(Model model, HttpServletRequest request) throws Exception {
		//
		// uri 路径不带 ?后面 这些 个 请求参数 /goods-class56/book/getBooksByExample.action?
		// url 带 后面 请求参数 /goods-class56/book/getBooksByExample.action?cid=xxx&bname=XXX
		String uri = request.getRequestURI();
		Map<String, String[]> map = request.getParameterMap();

		uri = uri + "?xxx=xxx";
		Set<String> set = map.keySet();
		List<String> keys = new ArrayList<String>(set);
		for (String param_name : keys) {
			String param_value = map.get(param_name)[0];

			System.out.println(param_value);

			// param_value = new String(param_value.getBytes("iso8859-1"),"utf-8");
			param_value = new String(param_value.getBytes("iso8859-1"), "utf-8");
			if (!param_name.equals("pageNum") && !param_name.equals("xxx")) {
				/// uri
				uri = uri + "&" + param_name + "=" + param_value;
			}
		}

		System.out.println(uri);

		model.addAttribute("url", uri);

	}

	@RequestMapping("/getOrdersByUid/{uid}.action")
	public String getOrdersByUid(@PathVariable String uid, Model model,
			@RequestParam(defaultValue = "1") Integer pageNum, HttpServletRequest request) throws Exception {
		getUrl(model, request);

		//
		PageHelper.startPage(pageNum, 2);
		List<OrderPovo> orderpovos = orderService.getOrdersByUid(uid);
		PageInfo<OrderPovo> info = new PageInfo<OrderPovo>(orderpovos);
		model.addAttribute("orderpovos", orderpovos);
		model.addAttribute("info", info);

		return "jsps/order/list";
	}
}