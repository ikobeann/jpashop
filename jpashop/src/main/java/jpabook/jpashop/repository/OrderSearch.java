package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus; // 주문상태

}
