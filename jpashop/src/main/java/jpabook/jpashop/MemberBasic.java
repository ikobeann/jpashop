package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MemberBasic {


    @Id
    @GeneratedValue
    private Long id;
    private String username;

}
