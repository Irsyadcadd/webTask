package com.practice.web.servlet.product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.web.dto.ProductDto;
import com.practice.web.model.entity.product.Product;
import com.practice.web.model.repository.ProductRepository;
import com.practice.web.model.repository.RepositoryException;
import com.practice.web.service.ProductService;

@WebServlet(urlPatterns = {"/admin/list-products", "/admin/delete", "/admin/download-csv"})
public class ListProductsServlet extends HttpServlet {
	
	private ProductService productService;
	
    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/admin/list-products")) {
			try {
				List<ProductDto> listProduct = productService.getAllProducts();
				
				req.setAttribute("data_products", listProduct);
				RequestDispatcher dispatchView = req.getRequestDispatcher("/WEB-INF/jsp/product/products-list.jsp");
				dispatchView.forward(req, resp);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		} else if (path.equals("/admin/delete")) {
			String idParam = req.getParameter("id");
			try {
                int productId = Integer.valueOf(idParam);

                productService.deleteProduct(productId);

                resp.sendRedirect(req.getContextPath() + "/admin/list-products");

            } catch (RepositoryException e) {
                e.printStackTrace();
            }
		} else if (path.equals("/admin/download-csv")) {
			resp.setContentType("text/csv");
	        resp.setHeader("Content-Disposition", "attachment; filename=\"product_list.csv\"");

	        try {
	            List<ProductDto> productList = productService.getAllProducts();
	            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));
	            writer.write("ID;Product Name;Type;Price\n");
	            for (ProductDto product : productList) {
	                writer.write(product.getId() + ";" +
	                             product.getName() + ";" +
	                             product.getType() + ";" +
	                             product.getPrice() + "\n");
	            }

	            writer.flush();
	        } catch (RepositoryException e) {
	            e.printStackTrace();
	        }
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
}
