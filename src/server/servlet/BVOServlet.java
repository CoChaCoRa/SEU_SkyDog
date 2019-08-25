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
	
	//���������Ϣ
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		System.out.println(username);
		
		//��ȡrequest�еĲ���ֵ
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		System.out.println(name);
		
		
		//�˴�����DAO�㺯�������µ�����ӵ�bvo����
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
	
	//��ʾ������Ϣ
	private void showInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		BVO bvo = new BVO();
		
		//����DAO�㺯������ѯusername��Ӧ�Ľ�������Ϣ����һ��BVOʵ������ʽ����
		BVODao bvoSearch = new BVODaoImpl();
		bvo = bvoSearch.selectBVO(username);
		
		request.setAttribute("username", username);
		request.setAttribute("name", bvo.getName());
		request.setAttribute("email", bvo.getEmail());
		request.setAttribute("phone", bvo.getPhone());
		
		request.getRequestDispatcher("bvo-myinfo.html").forward(request, response);;
	}
	
	//��ʾ��Ʒ
	private void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//��ʾȫ����Ʒ
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		
		GoodsMenuDao goodsDao = new GoodsMenuDaoImpl();
		goodsList = goodsDao.getAllGoods();
		
		//����Ʒ������request
	    request.setAttribute("goodsList", goodsList);
		
		request.getRequestDispatcher("bvo-index.html").forward(request, response);;
	}
	
	//��ʾ��������Ʒ
	private void showOneGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		GoodsInfo good = new GoodsInfo();
		GoodsMenuDao goodDao = new GoodsMenuDaoImpl();
		
		String sku = request.getParameter("sku");
		good = goodDao.queryByGoodsSKU(sku);
		request.setAttribute("good", good);
		
		request.getRequestDispatcher("bvo-goodsdetail.html").forward(request, response);
	}
	
	//��ʾ����
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
		
		//��storeList����request
		request.setAttribute("storeList", final_storeList);
		
		request.getRequestDispatcher("bvo-goodsstoreadd.html").forward(request, response);;
	}
	
	//��ʾ����
	private void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();
		
		BVOOrderListDao orderDao = new BVOOrderListDaoImpl();
		orderList = orderDao.queryByUsername(username);
		
		//����request
		request.setAttribute("orderList", orderList);
		
		request.getRequestDispatcher("bvo-orderlist.html").forward(request, response);;
	}
	
	//��ʾ��Ը��
	private void showWishList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		//����username��ѯ��Ը�����������ݸ�ҳ��
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
		
		//����request
		request.setAttribute("goodsList", goodsList);
		
		request.getRequestDispatcher("bvo-wishlist.html").forward(request, response);
	}
	
	//��ӵ���
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
	
	//����Ʒ��ӵ���Ը��
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
	
	//�µ�
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
	
	//ɾ����Ը���е���Ʒ
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
	
	//������������̫����ѵ��Ķ�����ʾ����
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
	
	//֧��
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
	
	//Ǯ���˻�ע��
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
	
	//��ʾǮ����Ϣ
	private void showWallet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		Wallet wallet = new Wallet();
		WalletDao walletDao = new WalletDaoImpl();
		
		wallet = walletDao.selectWallet(username);
		request.setAttribute("wallet", wallet);
		
		request.getRequestDispatcher("bvo-gmcwallermoney.html").forward(request, response);
	}
	
	//��ֵ
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
