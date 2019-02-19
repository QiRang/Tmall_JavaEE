package cart;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	/*
	 * map创建：
	 * 1.创建Map存放条目，以商品id做键<商品id,条目>
	 * 2.创建LinkedHashMap，为了保证顺序
	 */
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
	public Cart(){
		
	}
	
	/**
	 * 合计  （所有条目价钱小计之和）
	 * 使用BigDecimal类型：解决二进制运算误差问题
	 * @return
	 */
	public double getTotal() {
		//使用BigDecimal类型
		//BigDecimal total = BigDecimal.valueOf(0);
		double total = 0;
		//遍历每个条目
		for (CartItem cartItem : map.values()) {
			//得到每个条目价钱（得到BigDecimal类型）
			//BigDecimal subTatal = BigDecimal.valueOf(Double.valueOf(cartItem.getSubtotal()));
			//对每个条目进行加法求和
			double subTotal = Double.valueOf(cartItem.getSubtotal());
			total += subTotal;
		}
		//return total.doubleValue();	//转为double类型
		return total;
	}
	
	
	//添加条目
	public void add(CartItem cartItem){
		/*
		 * 1.判断map中是否含有被添加条目
		 * 2.有，进行条目合并（合并数量）
		 * 	   条目合并：
		 * 		1.拿到原条目
		 * 		2.条目数量合并 （原数量 + 新数量）
		 * 		3.将合并后的条目放进map
		 * 3.没有，直接添加
		 */
		if(map.containsKey(cartItem.getProduct().getProductId())){
			//1.拿到原条目
			CartItem _cartItem = map.get(cartItem.getProduct().getProductId());
			//2.条目数量合并 （原数量 + 新数量）
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			//3.将合并后的条目放进map
			map.put(cartItem.getProduct().getProductId(), _cartItem);
			System.out.println(_cartItem.getCount()+"_cartItem.getCount()");
			System.out.println("合并存放"+ cartItem.getProduct().getProductId() + cartItem);
		}else{
			//直接存放
			map.put(cartItem.getProduct().getProductId(),cartItem);
			System.out.println("直接存放"+ cartItem.getProduct().getProductId() + cartItem);
		}
	}
	
	//删除指定条目
	public void delete(int productId){
		map.remove(productId);
	}
	
	/**
	 * 清空条目
	 */
	public void clear(){
		map.clear(); 
	}
	
	/**
	 * 我的购物车（获取所有条目）
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}

}
