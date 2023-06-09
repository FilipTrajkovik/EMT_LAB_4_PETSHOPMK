package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Category;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Product;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.ukim.finki.emt.ordermanagement.xport.client.ProductClient;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;



    private static Product newProduct(String productName,
                                      String productDescription,
                                      int sales,
                                      Money productPrice,
                                      Category category) {
        Product p = new Product(ProductId.randomId(ProductId.class), productName, productDescription, sales, productPrice, category);
        return p;
    }


    @Test
    public void testPlaceOrder() {

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(newProduct("Bakers", "Hrana za kuche", 10, Money.valueOf(Currency.MKD, 500), new Category()));
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(newProduct("CatYou", "Igrachka za mache", 15, Money.valueOf(Currency.MKD, 1500), new Category()));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));
        orderForm.setOrderedOn(LocalDateTime.now());

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.orderPrice(),Money.valueOf(Currency.MKD,3500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Product> productList = productClient.findAll();
        Product p1 = productList.get(0);
        Product p2 = productList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(p1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));
        orderForm.setOrderedOn(LocalDateTime.now());

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getProductPrice().multiply(oi1.getQuantity()).add(p2.getProductPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.orderPrice(),outMoney);
    }

}
