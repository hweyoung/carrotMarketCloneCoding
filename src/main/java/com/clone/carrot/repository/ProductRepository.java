package com.clone.carrot.repository;

import com.clone.carrot.domain.Product;
import com.clone.carrot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.user.userIdx=?1")
    List<Product> findAllByUser(Long user);

    Product findProductByProductIdx(Long productIdx);
}
