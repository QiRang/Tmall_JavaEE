package cart;
import java.text.DecimalFormat;

import product.Product;

public class CartItem {
	private Product product;  //��Ʒ
	private int count;	//��Ʒ����	
	public String getSubtotal(){  //���㷽��
		Double _price = Double.valueOf(product.getProductPrice());
		int _count = count;
		//����۸񣨼�Ǯ*������
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
