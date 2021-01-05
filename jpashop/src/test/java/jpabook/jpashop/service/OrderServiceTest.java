package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Item book = createBook("JPA 활용1", 20000, 10);

        //when
        int orderCount = 3;
        long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);


        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(20000*orderCount, getOrder.getTotalPrice());
        assertEquals(7, book.getStockQuantity());

    }



    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("JPA 활용1", 20000, 10);
        int orderCount = 11;
        //when
        
        //then

        NotEnoughStockException exception = assertThrows(NotEnoughStockException.class, () -> {
            //state
            orderService.order(member.getId(), book.getId(), orderCount);
                    });
        assertEquals(exception.getMessage(), "Need more stock");


    }
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("시골JPA", 10000, 10);
        int orderCount = 2;
        long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrdr = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrdr.getStatus());
        assertEquals(10, item.getStockQuantity());
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("부천시","괴안동","123"));
        em.persist(member);
        return member;
    }
}