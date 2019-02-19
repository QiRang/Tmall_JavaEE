package cart;
import java.text.DecimalFormat;

import product.Product;

public class CartItem {
	private Product product;  //商品
	private int count;	//商品数量	
	public String getSubtotal(){  //计算方法
		Double _price = Double.valueOf(product.getProductPrice());
		int _count = count;
		//计算价格（价钱*数量）
		double subtotal = _price * _count; 
		DecimalFormat df = new DecimalFormat( "0.00");
		return df.format(subtotal);
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
