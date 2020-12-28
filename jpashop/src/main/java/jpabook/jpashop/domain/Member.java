package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //order 필더에 있는 member에 의해서 맵핑된 거울이야. 읽기 전용이야.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
