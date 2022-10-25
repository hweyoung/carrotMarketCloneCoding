package com.clone.carrot.domain;

import com.clone.carrot.dto.UserRequest;
import lombok.*;

import javax.persistence.*;

@Getter
@Table(name = "user")
@NoArgsConstructor
@Entity
public class User extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String img;

    @Column(nullable = false,name = "manner_temp")
    private double mannerTemp;

    //uid로 생각함
    @Column(nullable = false)
    private String code;

    //ACTIVE, UNACTIVE, DELETE
    @Column(nullable = false)
    private String status;



    @Builder
    public User(String nickname, String phone, String email, String code) {
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.mannerTemp = 36.5;
        this.code = code;
        this.status = "ACTIVE";
    }

    public void update(UserRequest.update update){
        this.nickname = update.getNickname();
        this.email = update.getEmail();
        this.mannerTemp = update.getMannerTemp();
        this.email = update.getEmail();
    }
}
