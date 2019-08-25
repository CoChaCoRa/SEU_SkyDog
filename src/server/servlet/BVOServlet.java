package server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;

import dao.*;
import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import exception.OutOfLimitException;
import vo.*;

public class BVOServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  BVOServlet(){
		super();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String function = request.getParameter("function");
		System.out.println("function: " + function);

		switch(function){
		case "save":
			save(request, response);
			break;
		case "showInfo":
			showInfo(request, response);
			break;
		case "showGoods":
			showGoods(request, response);
			break;
		case "showOneGood":
			showOneGood(request, response);
			break;
		case "showStores":
			showStores(request, response);
			break;
		case "showOrders":
			showOrders(request, response);
			break;
		case "showWishList":
			showWishList(request, response);
			break;
		case "addStore":
			addStore(request, response);
			showStores(request, response);
			break;
		case "addToWishList":
			addToWishList(request, response);
			showWishList(request, response);
			break;
		case "dropship":
			dropship(request, response);
			break;
		case "deleteFromWishList":
			deleteFromWishList(request, response);
			showWishList(request, response);
			break;
		case "searchOrder":
			searchOrder(request, response);
			break;
		case "pullOrders":
			showOrders(request, response);
			break;
		case "walletSignUp":
			walletSignUp(request, response);
			break;
		case "deposit":
			deposit(request, response);
			showWishList(request, response);
			break;
		case "pay":
			pay(request, response);
			showOrders(request, response);
			break;
		case "showWallet":
			showWallet(request, response);
			break;
		default:
			break;
		}
	}
	
	//保存个人信息
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		System.out.println(username);
		
		//获取request中的参数值
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		System.out.println(name);
		
		
		//此处调用DAO层函数，将新的信添加到bvo表中
		BVO bvo = new BVO();
		bvo.setUserName(username);
		bvo.setName(name);
		bvo.setEmail(email);
		bvo.setPhone(phone);
		
		BVODao bvoInsert = new BVODaoImpl();
		try
		{
			bvoInsert.updateBVO(bvo);
			response.sendRedirect("bvo-index.html");
		}catch(RecordNotFoundException e2){
			System.out.println("record not found!");
		}
		
	}
	
	//显示个人信息
	private void showInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		BVO bvo = new BVO();
		
		//调用DAO层函数，查询username对应的借卖方信息，以一个BVO实例的形式返回
		BVODao bvoSearch = new BVODaoImpl();
		bvo = bvoSearch.selectBVO(username);
		
		request.setAttribute("username", username);
		request.setAttribute("name", bvo.getName());
		request.setAttribute("email", bvo.getEmail());
		request.setAttribute("phone", bvo.getPhone());
		
		request.getRequestDispatcher("bvo-myinfo.html").forward(request, response);;
	}
	
	//显示商品
	private void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//显示全部商品
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		
		GoodsMenuDao goodsDao = new GoodsMenuDaoImpl();
		goodsList = goodsDao.getAllGoods();
		
		//将商品表送入request
	    request.setAttribute("goodsList", goodsList);
		
		request.getRequestDispatcher("bvo-index.html").forward(request, response);;
	}
	
	//显示单独的商品
	private void showOneGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		GoodsInfo good = new GoodsInfo();
		GoodsMenuDao goodDao = new GoodsMenuDaoImpl();
		
		String sku = request.getParameter("sku");
		good = goodDao.queryByGoodsSKU(sku);
		request.setAttribute("good", good);
		
		request.getRequestDispatcher("bvo-goodsdetail.html").forward(request, response);
	}
	
	//显示店铺
	private void showStores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ArrayList<Store> storeList = new ArrayList<Store>();
		ArrayList<Store> final_storeList = new ArrayList<Store>();
		
		StoreDao storeDao = new StoreDaoImpl();
		storeList = storeDao.getAllStores();
		
		for(int i = 0; i < storeList.size(); i++){
			if(storeList.get(i).getSellerID().equals(username)){
				Store store = new Store();
				store = storeList.get(i);
				final_storeList.add(store);
			}
		}
		
		//将storeList送入request
		request.setAttribute("storeList", final_storeList);
		
		request.getRequestDispatcher("bvo-goodsstoreadd.html").forward(request, response);;
	}
	
	//显示订单
	private void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();
		
		BVOOrderListDao orderDao = new BVOOrderListDaoImpl();
		orderList = orderDao.queryByUsername(username);
		
		//送入request
		request.setAttribute("orderList", orderList);
		
		request.getRequestDispatcher("bvo-orderlist.html").forward(request, response);;
	}
	
	//显示心愿单
	private void showWishList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		//根据username查询心愿单并返回内容给页面
		WishListDao wishListDao = new WishListDaoImpl();
		GoodsMenuDao goodsDao = new GoodsMenuDaoImpl();
		ArrayList<WishList> wishList = new ArrayList<WishList>();
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		
		wishList = wishListDao.queryByUsername(username);
		
		for(int i = 0; i < wishList.size(); i++)
		{
			GoodsInfo goods = goodsDao.queryByGoodsSKU(wishList.get(i).getSKU());
			goodsList.add(goods);
		}
		
		//送入request
		request.setAttribute("goodsList", goodsList);
		
		request.getRequestDispatcher("bvo-wishlist.html").forward(request, response);
	}
	
	//添加店铺
	private void addStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		StoreDao storeDao = new StoreDaoImpl();
		Store store = new Store();
		
		String storeName = (String)request.getAttribute("storeName");
		String sellerID = (String)request.getAttribute("sellerID");
		String marketID = (String)request.getAttribute("marketID");
		String TOKEN = (String)request.getAttribute("TOKEN");
		String category = (String)request.getAttribute("category");
		
		store.setStoreName(storeName);
		store.setSellerID(sellerID);
		store.setMarketID(marketID);
		store.setTOKEN(TOKEN);
		store.setCategory(category);
		
		try{
			storeDao.insertStore(store);
		}catch(RecordAlreadyExistException e1){
			System.out.println("record already exist!");
		}
	}
	
	//将商品添加到心愿单
	private void addToWishList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		WishListDao wishListDao = new WishListDaoImpl();
		WishList wishList = new WishList();
		
		String sku = (String)request.getAttribute("sku");
		
		wishList.setUsername(username);
		wishList.setSKU(sku);
		
		try{
			
		wishListDao.insertWishList(wishList);
		
		}catch(RecordAlreadyExistException e1){
			System.out.println("record already exist!");
		}
	}
	
	//下单
	private void dropship(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		GoodsInfo goodInfo = new GoodsInfo();
		OrderInfo orderInfo = new OrderInfo();
		
		GoodsMenuDao goodDao = new GoodsMenuDaoImpl();
		BVOOrderListDao orderDao = new BVOOrderListDaoImpl();
		
		String sku = request.getParameter("sku");
		goodInfo = goodDao.queryByGoodsSKU(sku);
		
		orderInfo.setGoodsName(goodInfo.getName());
		orderInfo.setGoodsPrice(goodInfo.getPrice());
		orderInfo.setGoodsSKU(sku);
		orderInfo.setGoodsNumber(1);
		//orderInfo.setOrderID(orderID);
		orderInfo.setUserName(username);
		orderInfo.setOrderStatus("unpayed");
		//orderInfo.setOrderTrackingID(orderTrackingID);
		
		Date now = new Date(System.currentTimeMillis());
		orderInfo.setOrderCreateTime(now);
		
		try{
			orderDao.insertOrderInfo(orderInfo);
		}catch(OutOfLimitException e1){
			System.out.println("out of limit!");
		}catch(RecordAlreadyExistException e2){
			System.out.println("record already exist!");
		}catch(RecordNotFoundException e3){
			System.out.println("record not found!");
		}
	}
	
	//删除心愿单中的商品
	private void deleteFromWishList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		WishListDao wishListDao = new WishListDaoImpl();
		WishList wishList = new WishList();
				
		String sku = (String)request.getAttribute("sku");
				
		wishList.setUsername(username);
		wishList.setSKU(sku);
				
		try{
			
			wishListDao.deleteWishList(wishList);
			
			response.sendRedirect("bvo-wishlist.html");
			
		}catch(RecordNotFoundException e1){
			System.out.println("record not found!");
		}
	}
	
	//搜索订单（不太清楚搜到的订单显示在哪
	private void searchOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		String goodName = (String)request.getAttribute("goodName");
		BVOOrderListDao orderDao = new BVOOrderListDaoImpl();
		
		ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();
		orderList = orderDao.queryByGoodsName(goodName, username);
		
		request.setAttribute("orderList", orderList);
		
		//request.getRequestDispatcher("bvo-orderlist.html").forward(request, response);
	}
	
	//支付
	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		BVOOrderListDao orderDao = new BVOOrderListDaoImpl();
		
		String orderID = request.getParameter("orderID");
		OrderInfo order = orderDao.queryByOrderID(orderID, username);
		
		try{
			orderDao.payRequest(order);
		}catch(OutOfLimitException e1){
			System.out.println("out of limit!");
		}catch(RecordNotFoundException e2){
			System.out.println("record not found!");
		}
	}
	
	//钱包账户注册
	private void walletSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		Wallet wallet = new Wallet();
		WalletDao walletDao = new WalletDaoImpl();
		
		String email = request.getParameter("email");
		String accountName = request.getParameter("accountName");
		String password = request.getParameter("password");
		
		wallet.setUserName(username);
		wallet.setEmail(email);
		wallet.setAccount(accountName);
		wallet.setPassword(password);
		
		try{
			walletDao.insertWallet(wallet);
			response.sendRedirect("bvo-gmcwallermoney.html");
		}catch(RecordAlreadyExistException e1){
			System.out.println("record already exist!");
		}catch(RecordNotFoundException e2){
			System.out.println("record not found!");
		}
	}
	
	//显示钱包信息
	private void showWallet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		Wallet wallet = new Wallet();
		WalletDao walletDao = new WalletDaoImpl();
		
		wallet = walletDao.selectWallet(username);
		request.setAttribute("wallet", wallet);
		
		request.getRequestDispatcher("bvo-gmcwallermoney.html").forward(request, response);
	}
	
	//充值
	private void deposit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		Wallet wallet = new Wallet();
		WalletDao walletDao = new WalletDaoImpl();
		
		wallet = walletDao.selectWallet(username);
		String password = wallet.getPassword();
		Double old_money = wallet.getMoney();
		Double add_money = Double.valueOf(request.getParameter("add_money"));
		
		if(request.getParameter("password").equals(password)){
			Double new_money = old_money + add_money;
			wallet.setMoney(new_money);
			try{
				walletDao.updateWallet(wallet);
			}catch(RecordNotFoundException e){
				System.out.println("record not found!");
			}
		}else{
			System.out.println("wrong password!");
		}
		
	}
}
