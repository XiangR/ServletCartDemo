package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// 购物车类
public class Cart {

	// 购买商品的集合
	private HashMap<Items, Integer> goods;
	
	// 购买商品的总金额
	private double totalPrice;
	
	// 构造方法
	public Cart() {
		goods = new HashMap<Items, Integer>();
		totalPrice = 0.0;
	}
	
	// 添加商品进购物车
	public boolean addGoodsInCart(Items item, int number) {
		
		if(goods.containsKey(item)) {
			goods.put(item, goods.get(item) + number );
		} else {
			goods.put(item, number);
		}
		
		calTotalPrice();
		return true;
		
	}
	
	// 从购物车删除商品
	public boolean removeGoodsInCart(Items item) {
		goods.remove(item);
		calTotalPrice();
		return true;
	}
	
	// 计算购物车总价格
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

		// 插入检测
		Items i1 = new Items(1, "沃特篮球鞋", "温州", 200, 500, "001.jpg");
		Items i2 = new Items(2, "李宁运动鞋", "广州", 300, 500, "002.jpg");
		Items i3 = new Items(2, "李宁运动鞋", "广州", 300, 500, "002.jpg");
		Items i4 = new Items(1, "沃特篮球鞋", "温州", 200, 500, "001.jpg");
		
		Cart c1 = new Cart();
		c1.addGoodsInCart(i1, 2);
		c1.addGoodsInCart(i2, 2);
		c1.addGoodsInCart(i3, 2);
		c1.addGoodsInCart(i4, 2);
		// 变量购物商品的集合 让这个Map变成一个 Set
		Set<Map.Entry<Items, Integer>> items = c1.getGoods().entrySet();
		for(Map.Entry<Items, Integer> item : items) {
			System.out.println(item);
		}
		System.out.println("购物车的总价格：" + c1.getTotalPrice());
	}
	
}
