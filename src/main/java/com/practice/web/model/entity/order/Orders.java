package com.practice.web.model.entity.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.practice.web.model.entity.product.Product;
import com.practice.web.model.entity.user.User;


@Entity
@Table(name = "orders")
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;
	
	@Column(name = "total_price")
    private long totalPrice;
    

    public Orders() {
        items = new ArrayList<>();
        totalPrice = 0;
    }
    
    public Orders(int id, User user, List<OrderItem> items, long totalPrice) {
		super();
		this.id = id;
		this.user = user;
		this.items = items;
		this.totalPrice = totalPrice;
	}



    public void addItem(Product product, int quantity) {
        for (OrderItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                item.updateTotalPrice();
                totalPrice += product.getPrice() * quantity;
                return;
            }
        }
        OrderItem newItem = new OrderItem(product, quantity);
        items.add(newItem);
        totalPrice += product.getPrice() * quantity;
    }

    public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItem> getItems() {
        return items;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}