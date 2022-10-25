package com.clone.carrot.service;

import com.clone.carrot.domain.Product;
import com.clone.carrot.domain.User;
import com.clone.carrot.dto.ProductRequest;
import com.clone.carrot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;


    public Product createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public Product getProduct(Long productIdx) {
        Product product = productRepository.findProductByProductIdx(productIdx);
        return product;
    }

    public Product updateProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Transactional
    public void deleteProduct(ProductRequest.delete delete) {
        Product product = getProduct(delete.getProductId());
        if(!delete.getUserCode().equals(product.getUser().getCode()))
            throw new IllegalArgumentException("잘못된 유저의 접근");
        productRepository.delete(product);
    }

    public List<Product> getProductList() {
        List<Product> productList =  productRepository.findAll();
        return productList;
    }

    public List<Product> getUserProductList(String userCode){
        User user = userService.getUserByCode(userCode);
        List<Product> productList = productRepository.findAllByUser(user.getUserIdx());
        return productList;
    }
}
