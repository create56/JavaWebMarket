package product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Product;
import util.DBMng;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/product/list")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 상품 목록 페이지에 필요한 상품의 목록을 DB에서 가져와
	 *  request에 담아서 포워당한다
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 목록을 불러온다
		try {
			Connection conn = DBMng.getConnection();
			
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product");
		ResultSet rs = pstmt.executeQuery();
		
		// view 로 전달할 상품의 목록
		List<Product> productList = new ArrayList<>();
		
		while(rs.next()) {
			//n번쨰 상품을 목록에 저장
			
			//n번쨰 상품의 p_id 칼럼의 값을 꺼냄
			String p_id = rs.getString("p_id");
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			String p_name = rs.getString("p_name");
			// 나머지 n번쨰 상품의 p_unitPrice  칼럼의 값을 꺼냄
			int p_unitPrice = rs.getInt("p_unitPrice");
			
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			String p_description = rs.getString("p_description");
			
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			String p_category = rs.getString("p_category");
			
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			
			String p_manufacturer = rs.getString("p_manufacturer");
			
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			int p_unitsInStock = rs.getInt("p_unitsInStock");
			
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			String p_conition = rs.getString("p_conition");
			
			// 나머지 n번쨰 상품의 p_name 칼럼의 값을 꺼냄
			String p_fileName = rs.getString("p_fileName");
			
			Product product = new Product(p_id, p_name, p_unitPrice, p_description, p_category, p_manufacturer,
					p_unitsInStock,p_conition,p_fileName);
					
			productList.add(product);
		
		}
		
		request.setAttribute("productList", productList);
		RequestDispatcher dis = request.getRequestDispatcher("/product/list.jsp");
		dis.forward(request, response);
		
		} catch(SQLException e) {
			e.printStackTrace();
			RequestDispatcher dis = request.getRequestDispatcher("/error/500.jsp");
			dis.forward(request, response);
		} finally {
			DBMng.closeConnection();
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
