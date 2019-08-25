package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsMenuDao;
import dao.GoodsMenuDaoImpl;
import dao.MVODaoImpl;
import dao.MVOOrderManageDaoImpl;
import dao.RechargeDaoImpl;
import dao.WalletDaoImpl;
import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import vo.GoodsInfo;
import vo.MVO;
import vo.OrderInfo;
import vo.Recharge;
import vo.Wallet;
import java.sql.Date;

import java.lang.*;
import java.util.ArrayList;
import java.util.*;
public class Brand_servlet extends HttpServlet{
	private MVODaoImpl mvod=new MVODaoImpl();
	private ArrayList<GoodsInfo> goodslist=new ArrayList();//��Ʒ�б�
	private ArrayList<Wallet> walletList=new ArrayList();//Ǯ���˻�
	private ArrayList<OrderInfo> orderlist=new ArrayList();//���ж���
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=pareRequestURI(req);
//		MVO mvo=new MVO("a","com_name_eng","voiceBtn","brand_cor","cert_url","username");
//		try {
//			boolean a=mvod.insertMVO(mvo);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(action.equals("/brand_add_save")){
			String com_name_eng=req.getParameter("MOV_CName_E");
			String com_name_chs=req.getParameter("MOV_CName_C");
			String voiceBtn=req.getParameter("MOV_Intro");
			String brand_cor=req.getParameter("MOV_VeriType");
			String cert_url=req.getParameter("MOV_CertiAdd");
			
			//����û���
			HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			
//			System.out.println(voiceBtn);
//			System.out.println(com_name_eng);
			
			MVO mvo1=new MVO();
			mvo1.setCName_C(com_name_chs);
			mvo1.setCName_E(com_name_eng);
			mvo1.setIntro(voiceBtn);
			mvo1.setVeriType(brand_cor);
			mvo1.setCertiAdd(cert_url);
			mvo1.setID(username);
//			try {
//				boolean a=mvod.insertMVO(mvo1);
//			} catch (RecordAlreadyExistException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (RecordNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			req.setAttribute("MOV_CName_E", com_name_eng);
			req.setAttribute("MOV_CName_C", com_name_chs);
			req.setAttribute("MOV_Intro", voiceBtn);
			req.setAttribute("MOV_VeriType", brand_cor);
			req.setAttribute("MOV_CertiAdd", cert_url);
			
			
			req.getRequestDispatcher("Brand_addbrand.jsp").forward(req, resp);
			System.out.println("get the brand action");
		}else if(action.equals("/brand_brandInput_open")){
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			goodslist=dao.getAllGoods();
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-brandinput.jsp").forward(req, resp);
		}
		else if(action.equals("/brand_brandInput_add")){
		
			/////��Ʒ¼�롪������
			//������д�����ݿ��Լ�goodslist
			//
			///////��ȡ����
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String Sku=req.getParameter("goodsSKU");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
			/////////////////////
			
			GoodsInfo goods=new GoodsInfo();
			goods.setName(goodsName);
			goods.setPrice(goodsPrice);
			goods.setStock(stock);
			goods.setSKU(Sku);
			goods.setCateg(categ);
			goods.setPic(pic);
			goods.setState(state);
			//��goodslist�����ݿ������goods
			goodslist.add(goods);
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			try {
				dao.insertGoodsInfo(goods);
			} catch (RecordAlreadyExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-brandinput.jsp").forward(req, resp);
		}else if(action.equals("/brand_produceInput_change")){
			//��Ʒ¼�롪����д
		    ///��ȡ����
			String Sku=req.getParameter("goodsSKU");
			GoodsInfo goods=new GoodsInfo();
			//����goodslist�����ݿ�
			//ListIterator<GoodsInfo> it=goodslist.listIterator();
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku){
					goods.setName(goodslist.get(i).getName());
					goods.setPrice(goodslist.get(i).getPrice());
					goods.setStock(goodslist.get(i).getStock());
					goods.setSKU(Sku);
					goods.setCateg(goodslist.get(i).getCateg());
					goods.setPic(goodslist.get(i).getPic());
					goods.setState(goodslist.get(i).getState());
					goodslist.set(i,goods);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			try {
				dao.updateGoodsInfo(goods);
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-brandinput.jsp").forward(req, resp);
			
		}else if(action.equals("/brand_brandInput_delete")){
			//�Բ�����goodlist����ɾ��
		///////��ȡ����
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String Sku=req.getParameter("goodsSKU");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
		
			//��goodslist�����ݿ�ɾ��goods
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku){
					goodslist.remove(i);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			if(find==true){
				try {
					dao.deleteWishList(Sku);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//��Ϊ��������ǰ��
			req.setAttribute("goodslist", goodslist);
			req.getRequestDispatcher("Brand_brandinput.jsp").forward(req, resp);
			
		}else if(action.equals("/brand-produceinput-add")){
			//��Ʒ��ͼ���Ӳ���
			///////��ȡ����
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String Sku=req.getParameter("goodsSKU");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
			/////////////////////
			
			GoodsInfo goods=new GoodsInfo();
			goods.setName(goodsName);
			goods.setPrice(goodsPrice);
			goods.setStock(stock);
			goods.setSKU(Sku);
			goods.setCateg(categ);
			goods.setPic(pic);
			goods.setState(state);
			//��goodslist�����ݿ������goods
			goodslist.add(goods);
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			try {
				dao.insertGoodsInfo(goods);
			} catch (RecordAlreadyExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-productinput-pic.jsp").forward(req, resp);
		}else if(action.equals("/brand_produceInput_change")){
			//��Ʒ��ͼ������д
		    ///��ȡ����
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String Sku=req.getParameter("goodsSKU");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
			/////////////////////
			//����goodslist�����ݿ�
			GoodsInfo goods=new GoodsInfo();
			ListIterator<GoodsInfo> it=goodslist.listIterator();
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku){
					goodsName=goodslist.get(i).getName();
					goodsPrice=goodslist.get(i).getPrice();
					stock=goodslist.get(i).getStock();
					categ=goodslist.get(i).getCateg();
					pic=goodslist.get(i).getPic();
					state=goodslist.get(i).getState();
					
					goods.setName(goodsName);
					goods.setPrice(goodsPrice);
					goods.setStock(stock);
					goods.setSKU(Sku);
					goods.setCateg(categ);
					goods.setPic(pic);
					goods.setState(state);
					goodslist.set(i,goods);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			try {
				dao.updateGoodsInfo(goods);
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-productinput-pic.jsp").forward(req, resp);
			
		}else if(action.equals("/brand_produceInput_delete")){
			//��ͼ����ɾ��
			String Sku=req.getParameter("goodsSKU");
			//��goodslist�����ݿ�ɾ��goods
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku){
					goodslist.remove(i);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			try {
				dao.deleteWishList(Sku);
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-productinput-pic.jsp").forward(req, resp);
		}else if(action.equals("brand_produceInput_store")){
			//**************��Ʒ��ͼ����������************/
			String Sku=req.getParameter("goodsSKU");
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
			
			GoodsInfo goods=new GoodsInfo();
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku&goodslist.get(i).getState()=="untreated"){
					goodslist.get(i).setState("stored");
					goodsName=goodslist.get(i).getName();
					goodsPrice=goodslist.get(i).getPrice();
					stock=goodslist.get(i).getStock();
					categ=goodslist.get(i).getCateg();
					pic=goodslist.get(i).getPic();
					state=goodslist.get(i).getState();
					
					goods.setName(goodsName);
					goods.setPrice(goodsPrice);
					goods.setStock(stock);
					goods.setSKU(Sku);
					goods.setCateg(categ);
					goods.setPic(pic);
					goods.setState(state);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			if(find==true){
				try {
					dao.deleteWishList(Sku);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("��Ʒ��ͼ����.jsp").forward(req, resp);
		}else if(action.equals("/brand_produceInput_ground")){
			//**************��Ʒ��ͼ�����ϼܲ���************/
			String Sku=req.getParameter("goodsSKU");
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
			
			GoodsInfo goods=new GoodsInfo();
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku&goodslist.get(i).getState()=="stored"){
					goodslist.get(i).setState("onshelf");
					goodsName=goodslist.get(i).getName();
					goodsPrice=goodslist.get(i).getPrice();
					stock=goodslist.get(i).getStock();
					categ=goodslist.get(i).getCateg();
					pic=goodslist.get(i).getPic();
					state=goodslist.get(i).getState();
					
					goods.setName(goodsName);
					goods.setPrice(goodsPrice);
					goods.setStock(stock);
					goods.setSKU(Sku);
					goods.setCateg(categ);
					goods.setPic(pic);
					goods.setState(state);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			if(find==true){
				try {
					dao.updateGoodsInfo(goods);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-productinput-pic.jsp").forward(req, resp);
		}else if(action.equals("/brand_produceInput_unground")){
			//**************��Ʒ��ͼ�����¼ܲ���************/
			String Sku=req.getParameter("goodsSKU");
			String goodsName=req.getParameter("goodsName");
			double goodsPrice=(double)req.getAttribute("goodsPrice");
			int stock=(int)req.getAttribute("goodsStock");
			String categ=req.getParameter("goodsCateg");
			String pic=req.getParameter("goodsPic");
			String state=req.getParameter("goodsPic");
			
			GoodsInfo goods=new GoodsInfo();
			boolean find=false;
			for(int i=0;i<goodslist.size()&!find;i++){
				if(goodslist.get(i).getSKU()==Sku&goodslist.get(i).getState()=="onshelf"){
					goodslist.get(i).setState("offshelf");
					goodsName=goodslist.get(i).getName();
					goodsPrice=goodslist.get(i).getPrice();
					stock=goodslist.get(i).getStock();
					categ=goodslist.get(i).getCateg();
					pic=goodslist.get(i).getPic();
					state=goodslist.get(i).getState();
					
					goods.setName(goodsName);
					goods.setPrice(goodsPrice);
					goods.setStock(stock);
					goods.setSKU(Sku);
					goods.setCateg(categ);
					goods.setPic(pic);
					goods.setState(state);
					find=true;
				}
			}
			GoodsMenuDaoImpl dao=new GoodsMenuDaoImpl();
			if(find==true){
				try {
					dao.updateGoodsInfo(goods);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//��Ϊ��������ǰ��
			req.setAttribute("GoodsMenu", goodslist);
			req.getRequestDispatcher("Brand-productinput-pic.jsp").forward(req, resp);
		}else if(action.equals("/wallet_acount_open")){
			Wallet wal=new Wallet();
			HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			WalletDaoImpl dao=new WalletDaoImpl();
			wal=dao.selectWallet(username);
			if(wal!=null){
				req.setAttribute("Wallet", wal);
				req.getRequestDispatcher("Brand-gmcwallerAcount.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("Brand-wallerAcountRegister.jsp").forward(req, resp);
			}
		}
		else if(action.equals("/wallet_acount_add")){
		//*******************�˻��������*************//
			HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			String account=req.getParameter("account");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			Double money=(double)0;
			Wallet wal=new Wallet();
			wal.setAccount(account);
			wal.setEmail(email);
			wal.setMoney(money);
			wal.setPassword(password);
			wal.setUserName(username);
			WalletDaoImpl dao=new WalletDaoImpl();
			try {
				dao.insertWallet(wal);
			} catch (RecordAlreadyExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//�����ݿ������������˻���Ϣ
			//wal=dao.selectWallet(username);
			req.setAttribute("wallet", wal);
			req.getRequestDispatcher("Brand-gmcwallerAcount.jsp").forward(req, resp);;
		}else if(action.equals("/wallet_acount_withdraw")){
			//�˻�����
			HttpSession session = req.getSession();
			String username=(String)session.getAttribute("username");
			String account=req.getParameter("account");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			Double money=(double)req.getAttribute("money");
			Wallet wal=new Wallet();
//			wal.setAccount(account);
//			wal.setMoney(money);
//			wal.setEmail(email);
//			wal.setPassword(password);
//			wal.setUserName(username);
			WalletDaoImpl dao=new WalletDaoImpl();
			wal=dao.selectWallet(username);
			if(wal.getPassword()==password){
				Recharge newrec=new Recharge();
				Date date=new Date(2019, 5, 7);
				newrec.setAmount(money);
				newrec.setTime(date);
				newrec.setUsername(username);
				newrec.setType("charge");
				
				RechargeDaoImpl redao=new RechargeDaoImpl();
				try {
					redao.insertRecharge(newrec);
				} catch (RecordAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			req.setAttribute("Wallet", wal);
			req.getRequestDispatcher("Brand-gmcwallerAcount.jsp").forward(req, resp);
			
		}else if(action.equals("/wallet_acount_show")){
			//��ʾ����������Ŀ
			HttpSession session = req.getSession();
			String username=(String)session.getAttribute("username");
			RechargeDaoImpl dao=new RechargeDaoImpl();
			ArrayList<Recharge> rechagelist=new ArrayList<Recharge>();
			rechagelist=dao.getAllRecharge();
			req.setAttribute("Recharge", rechagelist);//�����������
			req.getRequestDispatcher("Brand-gmcwallerAcountlist").forward(req, resp);
		}
		
		else if(action.equals("/orderlist_open")){
			//*******������������*************//
			HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			MVOOrderManageDaoImpl dao=new MVOOrderManageDaoImpl();
			orderlist=dao.getAllOrders();
			req.setAttribute("orderInfo", orderlist);
			req.getRequestDispatcher("�����������").forward(req, resp);
		}
		else if(action.equals("/orderlist_ship")){
			//������������
			String orderID=(String)req.getAttribute("orderID");
			OrderInfo order=new OrderInfo();
			boolean find=false;
			for(int i=0;i<orderlist.size()&!find;i++){
				if(orderlist.get(i).getOrderID()==orderID
						&orderlist.get(i).getOrderStatus()=="unshiped"){
					orderlist.get(i).setOrderStatus("shiped");
					order.setGoodsName(orderlist.get(i).getGoodsName());
					order.setGoodsNumber(orderlist.get(i).getGoodsNumber());
					order.setGoodsPrice(orderlist.get(i).getGoodsPrice());
					order.setGoodsSKU(orderlist.get(i).getGoodsSKU());
					order.setOrderCreateTime(orderlist.get(i).getOrderCreateTime());
					order.setOrderID(orderID);
					order.setOrderStatus(orderlist.get(i).getOrderStatus());
					order.setOrderTrackingID(orderlist.get(i).getOrderTrackingID());
					order.setUserName(orderlist.get(i).getUserName());
					
					find=true;
				}
			}
			MVOOrderManageDaoImpl dao=new MVOOrderManageDaoImpl();
			if(find==true){
				try {
					dao.updateOrderInfo(order);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			req.setAttribute("orderInfo", orderlist);
			req.getRequestDispatcher("Brand-orderlist.jsp").forward(req, resp);
			
		}else if(action.equals("orderlist_delete")){
			/********************����������ȡ��*********/
			String orderID=(String)req.getAttribute("orderID");
			OrderInfo order=new OrderInfo();
			boolean find=false;
			for(int i=0;i<orderlist.size()&!find;i++){
				if(orderlist.get(i).getOrderID()==orderID
						&orderlist.get(i).getOrderStatus()=="shiped"){
					orderlist.get(i).setOrderStatus("abort");
					order.setGoodsName(orderlist.get(i).getGoodsName());
					order.setGoodsNumber(orderlist.get(i).getGoodsNumber());
					order.setGoodsPrice(orderlist.get(i).getGoodsPrice());
					order.setGoodsSKU(orderlist.get(i).getGoodsSKU());
					order.setOrderCreateTime(orderlist.get(i).getOrderCreateTime());
					order.setOrderID(orderID);
					order.setOrderStatus(orderlist.get(i).getOrderStatus());
					order.setOrderTrackingID(orderlist.get(i).getOrderTrackingID());
					order.setUserName(orderlist.get(i).getUserName());
					
					find=true;
				}
			}
			MVOOrderManageDaoImpl dao=new MVOOrderManageDaoImpl();
			if(find==true){
				try {
					dao.updateOrderInfo(order);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			req.setAttribute("orderInfo", orderlist);
			req.getRequestDispatcher("Brand-orderlist.jsp").forward(req, resp);
			
		}else if(action.equals("/ordertracking")){
			String orderID=req.getParameter("orderID");
			MVOOrderManageDaoImpl dao=new MVOOrderManageDaoImpl();
			OrderInfo order=new OrderInfo();
			order=dao.queryByOrderID(orderID);
			req.setAttribute("OrderInfo", order);
			req.getRequestDispatcher("Brand-ordertracking.jsp").forward(req, resp);
		}
		
	}
	
	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath();
		String requestUri = request.getRequestURI();
		String lasturl = requestUri.replaceFirst(path, "");
		lasturl = lasturl.substring(0, lasturl.lastIndexOf("."));
		return lasturl;
	}
}
