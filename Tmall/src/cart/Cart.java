package cart;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	/*
	 * map������
	 * 1.����Map�����Ŀ������Ʒid����<��Ʒid,��Ŀ>
	 * 2.����LinkedHashMap��Ϊ�˱�֤˳��
	 */
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
	public Cart(){
		
	}
	
	/**
	 * �ϼ�  ��������Ŀ��ǮС��֮�ͣ�
	 * ʹ��BigDecimal���ͣ���������������������
	 * @return
	 */
	public double getTotal() {
		//ʹ��BigDecimal����
		//BigDecimal total = BigDecimal.valueOf(0);
		double total = 0;
		//����ÿ����Ŀ
		for (CartItem cartItem : map.values()) {
			//�õ�ÿ����Ŀ��Ǯ���õ�BigDecimal���ͣ�
			//BigDecimal subTatal = BigDecimal.valueOf(Double.valueOf(cartItem.getSubtotal()));
			//��ÿ����Ŀ���мӷ����
			double subTotal = Double.valueOf(cartItem.getSubtotal());
			total += subTotal;
		}
		//return total.doubleValue();	//תΪdouble����
		return total;
	}
	
	
	//�����Ŀ
	public void add(CartItem cartItem){
		/*
		 * 1.�ж�map���Ƿ��б������Ŀ
		 * 2.�У�������Ŀ�ϲ����ϲ�������
		 * 	   ��Ŀ�ϲ���
		 * 		1.�õ�ԭ��Ŀ
		 * 		2.��Ŀ�����ϲ� ��ԭ���� + ��������
		 * 		3.���ϲ������Ŀ�Ž�map
		 * 3.û�У�ֱ�����
		 */
		if(map.containsKey(cartItem.getProduct().getProductId())){
			//1.�õ�ԭ��Ŀ
			CartItem _cartItem = map.get(cartItem.getProduct().getProductId());
			//2.��Ŀ�����ϲ� ��ԭ���� + ��������
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			//3.���ϲ������Ŀ�Ž�map
			map.put(cartItem.getProduct().getProductId(), _cartItem);
			System.out.println(_cartItem.getCount()+"_cartItem.getCount()");
			System.out.println("�ϲ����"+ cartItem.getProduct().getProductId() + cartItem);
		}else{
			//ֱ�Ӵ��
			map.put(cartItem.getProduct().getProductId(),cartItem);
			System.out.println("ֱ�Ӵ��"+ cartItem.getProduct().getProductId() + cartItem);
		}
	}
	
	//ɾ��ָ����Ŀ
	public void delete(int productId){
		map.remove(productId);
	}
	
	/**
	 * �����Ŀ
	 */
	public void clear(){
		map.clear(); 
	}
	
	/**
	 * �ҵĹ��ﳵ����ȡ������Ŀ��
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}

}
