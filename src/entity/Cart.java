package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// ���ﳵ��
public class Cart {

	// ������Ʒ�ļ���
	private HashMap<Items, Integer> goods;
	
	// ������Ʒ���ܽ��
	private double totalPrice;
	
	// ���췽��
	public Cart() {
		goods = new HashMap<Items, Integer>();
		totalPrice = 0.0;
	}
	
	// �����Ʒ�����ﳵ
	public boolean addGoodsInCart(Items item, int number) {
		
		if(goods.containsKey(item)) {
			goods.put(item, goods.get(item) + number );
		} else {
			goods.put(item, number);
		}
		
		calTotalPrice();
		return true;
		
	}
	
	// �ӹ��ﳵɾ����Ʒ
	public boolean removeGoodsInCart(Items item) {
		goods.remove(item);
		calTotalPrice();
		return true;
	}
	
	// ���㹺�ﳵ�ܼ۸�
	public double calTotalPrice(){
		double sum = 0.0;
		
		Set<Items> keys = goods.keySet();
		
		Iterator<Items> it = keys.iterator();
		while(it.hasNext()) {
			Items i = it.next();
			sum += i.getPrice() * goods.get(i);
		}
		this.setTotalPrice(sum);
		return this.getTotalPrice();
	}
	
	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	// for Test
	
	public static void main(String[] args) {

		// ������
		Items i1 = new Items(1, "��������Ь", "����", 200, 500, "001.jpg");
		Items i2 = new Items(2, "�����˶�Ь", "����", 300, 500, "002.jpg");
		Items i3 = new Items(2, "�����˶�Ь", "����", 300, 500, "002.jpg");
		Items i4 = new Items(1, "��������Ь", "����", 200, 500, "001.jpg");
		
		Cart c1 = new Cart();
		c1.addGoodsInCart(i1, 2);
		c1.addGoodsInCart(i2, 2);
		c1.addGoodsInCart(i3, 2);
		c1.addGoodsInCart(i4, 2);
		// ����������Ʒ�ļ��� �����Map���һ�� Set
		Set<Map.Entry<Items, Integer>> items = c1.getGoods().entrySet();
		for(Map.Entry<Items, Integer> item : items) {
			System.out.println(item);
		}
		System.out.println("���ﳵ���ܼ۸�" + c1.getTotalPrice());
	}
	
}
