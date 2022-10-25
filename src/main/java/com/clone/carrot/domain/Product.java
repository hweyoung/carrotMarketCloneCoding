package com.clone.carrot.domain;

import com.clone.carrot.dto.ProductRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Table(name = "product")
@NoArgsConstructor
@Entity
public class Product extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_idx")
    private Long productIdx;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    //시간이 부족한 관계로 물건의 위치는 String 값으로 저장
    private String region;

    private String title;
    private String contents;

    private int price;

    private String category;

    // Y : 거래 가능, COMP : 거래완료, RES : 예약중, N: 상품 없음
    private String status;

    @Builder
    public Product(User user, String region, String title, String contents, String category,int price, String status) {
        this.user = user;
        this.region = region;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.price = price;
        this.status = status;
    }


    public void update(ProductRequest.update update){
        this.category = update.getCategory();
        this.contents = update.getContents();
        this.price = update.getPrice();
        this.status = update.getStatus();
        this.title = update.getTitle();
        this.region = update.getRegion();
    }

}
