package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import entity.Cart;
import entity.Items;

public class CartServlet extends HttpServlet {

	private String action;	// 表示购物车的动作 add。show。delete
	// 商品业务逻辑类  //根据编号获取一个商品。获取所有的商品。 
	ItemsDAO idao = new ItemsDAO();
	/**
	 * Constructor of the object.
	 */
	public CartServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 根据得到的 action 判断是添加或是显示
		
		if(request.getParameter("action")!=null)
		{
			this.action = request.getParameter("action");
			 //如果是添加商品进购物车
			if(action.equals("add")) {
				if(addToCart(request,response)) {
					request.getRequestDispatcher("/success.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}
			if(action.equals("show")) {
				// 若是 show 则实现直接跳转
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			
			if(action.equals("delete")) {
				if(deleteFromCart(request, response)) {
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
			}
		}
	}

	boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		Items item = idao.getItemsById(Integer.parseInt(id));
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart.removeGoodsInCart(item)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		Items item = idao.getItemsById(Integer.parseInt(id));
		
		// 若是第一次添加商品 就先添加session
		if(request.getSession().getAttribute("cart") == null) {
			Cart cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		// 我们要创造出 购物车类之后才能够添加  
		// 在这里我们还是可以发现  其实在Servlet中进行的大多是一个行为  而实际的操作还是要在具体的类中 如对于购物车的操作就封装在购物车中 
		// 这也就是面对对象的方法  我们将一个对象的属性和对其操作的方法统统都封装到其类中   然后在其他的类中对其进行调用
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart.addGoodsInCart(item, Integer.parseInt(number))) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
