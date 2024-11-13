package com.practice.web.servlet.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.web.dto.ProductDto;
import com.practice.web.model.entity.product.Product;
import com.practice.web.model.entity.product.ProductTypeList;
import com.practice.web.model.repository.ProductRepository;
import com.practice.web.model.repository.RepositoryException;
import com.practice.web.service.ProductService;

@WebServlet(urlPatterns = {"/admin/detail-product", "/admin/update-product"})
public class DetailProductServlet extends HttpServlet {
	
	private ProductService productService;
	
    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("id");
        int productId = Integer.parseInt(productIdStr);
        ProductDto product;
		try {
			product = productService.getProductById(productId);
			
			request.setAttribute("product_types", ProductTypeList.getProductTypes());
            request.setAttribute("product_detail", product);
            request.getRequestDispatcher("/WEB-INF/jsp/product/product-detail.jsp").forward(request, response);

		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
        String type = request.getParameter("type");
        long price = Long.valueOf(request.getParameter("price"));
        
        try {
			productService.updateProduct(id, name, type, price);
			
			if (path.equals("/admin/update-product")) {
				response.sendRedirect(request.getContextPath() + "/admin/list-products");
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

}
