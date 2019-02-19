package product;

import java.text.DecimalFormat;

/*
 Java�����ṩ�˰��ֻ������͡������������ͣ��ĸ������ͣ����������ͣ���һ���ַ����ͣ�����һ�ֲ����͡�

byte��

byte ����������8λ���з��ŵģ��Զ����Ʋ����ʾ��������
��Сֵ�� -128��-2^7����
���ֵ�� 127��2^7-1����
Ĭ��ֵ�� 0��
byte �������ڴ��������н�Լ�ռ䣬��Ҫ������������Ϊ byte ����ռ�õĿռ�ֻ�� int ���͵��ķ�֮һ��
���ӣ�byte a = 100��byte b = -50��
short��

short ���������� 16 λ���з��ŵ��Զ����Ʋ����ʾ������
��Сֵ�� -32768��-2^15����
���ֵ�� 32767��2^15 - 1����
Short ��������Ҳ������ byte ������ʡ�ռ䡣һ��short������int�ͱ�����ռ�ռ�Ķ���֮һ��
Ĭ��ֵ�� 0��
���ӣ�short s = 1000��short r = -20000��
int��

int ����������32λ���з��ŵ��Զ����Ʋ����ʾ��������
��Сֵ�� -2,147,483,648��-2^31����
���ֵ�� 2,147,483,647��2^31 - 1����
һ������ͱ���Ĭ��Ϊ int ���ͣ�
Ĭ��ֵ�� 0 ��
���ӣ�int a = 100000, int b = -200000��
long��

long ���������� 64 λ���з��ŵ��Զ����Ʋ����ʾ��������
��Сֵ�� -9,223,372,036,854,775,808��-2^63����
���ֵ�� 9,223,372,036,854,775,807��2^63 -1����
����������Ҫʹ������Ҫ�Ƚϴ�������ϵͳ�ϣ�
Ĭ��ֵ�� 0L��
���ӣ� long a = 100000L��Long b = -200000L��
"L"�����ϲ��ִ�Сд��������д��"l"����������"1"�����������׷ֱ硣������ô�д��
float��

float ���������ǵ����ȡ�32λ������IEEE 754��׼�ĸ�������
float �ڴ�����͸��������ʱ��ɽ�ʡ�ڴ�ռ䣻
Ĭ��ֵ�� 0.0f��
����������������ʾ��ȷ��ֵ������ң�
���ӣ�float f1 = 234.5f��
double��

double ����������˫���ȡ�64 λ������IEEE 754��׼�ĸ�������
��������Ĭ������Ϊdouble���ͣ�
double����ͬ�����ܱ�ʾ��ȷ��ֵ������ң�
Ĭ��ֵ�� 0.0d��
���ӣ�double d1 = 123.4��
boolean��

boolean�������ͱ�ʾһλ����Ϣ��
ֻ������ȡֵ��true �� false��
��������ֻ��Ϊһ�ֱ�־����¼ true/false �����
Ĭ��ֵ�� false��
���ӣ�boolean one = true��
char��

char������һ����һ�� 16 λ Unicode �ַ���
��Сֵ�� \u0000����Ϊ0����
���ֵ�� \uffff����Ϊ65,535����
char �������Ϳ��Դ����κ��ַ���
���ӣ�char letter = 'A';�� 
*/
public class Product {
	private int productId = 0;
	private String productName = "";
	//private double productPrice = 0.0;
	private String productPrice = "0.00";
	private String productImg = "";
	private String productCategoryId = "";
	private String productStoreId = "";
	private String stock ="0";

	public String getStock() {
		if(this.stock == null || this.stock == ""){
			this.stock = "0";
		}
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
//	public double getProductPrice() {
//		return productPrice;
//	}

	public void setProductPrice1(String productPrice) {
		this.productPrice = productPrice + "";
	}
	public String getProductPrice(){
		return productPrice;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductStoreId() {
		return productStoreId;
	}
	public void setProductStoreId(String productStoreId) {
		this.productStoreId = productStoreId;
	}
	public void setProductPrice(String productPrice) {
//		DecimalFormat df = new DecimalFormat( "0.00");
//		this.productPrice = df.format(productPrice);
		this.productPrice = productPrice;
		}
}
