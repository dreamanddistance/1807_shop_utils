package com.qf.entity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.qf.domain.GoodsInfoDomain;

/**
 * 购物车对象
 * @author admin
 *
 */
public class ShopCart {

	
	// 这个list用来装商品
	private List<GoodsInfoDomain> list = new ArrayList<GoodsInfoDomain>();
	
	public void add(GoodsInfoDomain goodsInfo){
		
		// 1.先配先判断商品是否已经存在
		for (GoodsInfoDomain goodsInfoDomain : list) {
			if(goodsInfoDomain.getId().equals(goodsInfo.getId())){
				// 如果能这里面，说明该商品已经存在
				goodsInfoDomain.setCount(goodsInfoDomain.getCount()+goodsInfo.getCount());
				return ;
			}
		}
		
		list.add(goodsInfo);
	}
	
	public void delete(Integer id){
		for (GoodsInfoDomain goodsInfo : getList()) {
			if(id.equals(goodsInfo.getId())){
				getList().remove(goodsInfo);
				break;
			}
		}
	}

	public static ShopCart getShopCartIns(HttpSession session) {
		
		// 1.先从session中后去购物车对象
		ShopCart shopCart = (ShopCart)session.getAttribute("shopCart");
		
		// 2.判断用户是否有购物车对象
		if(shopCart == null){
			// 用户没有购物车对象，就新创建一个购物重对象
			shopCart = new ShopCart();
			
			// 新创建的购物车对象要放到session中
			session.setAttribute("shopCart", shopCart);
		}
		return shopCart;
	}

	public List<GoodsInfoDomain> getList() {
		return list;
	}

	public void setList(List<GoodsInfoDomain> list) {
		this.list = list;
	}
	
	
	public Double getPrice(){
		Double price = 0.0;
		for (GoodsInfoDomain goodsInfoDomain : list) {
			price += goodsInfoDomain.getGoods_price_off()*goodsInfoDomain.getCount();
		}
		return price;
	}

	public void updateCount(Integer id, Integer count) {
		for (GoodsInfoDomain goodsInfoDomain : list) {
			if(id.equals(goodsInfoDomain.getId())){
				goodsInfoDomain.setCount(count);
			}
		}
	}
}
