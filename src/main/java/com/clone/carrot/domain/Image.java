package com.clone.carrot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "image")
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_idx")
    private Long image_idx;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private String url;
}
