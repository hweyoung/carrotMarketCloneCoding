package com.clone.carrot.controller;

import com.clone.carrot.domain.Product;
import com.clone.carrot.domain.User;
import com.clone.carrot.dto.ErrorResponse;
import com.clone.carrot.dto.JsonResponse;
import com.clone.carrot.dto.ProductRequest;
import com.clone.carrot.dto.ProductResponse;
import com.clone.carrot.service.ProductService;
import com.clone.carrot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/post")
    public ResponseEntity<JsonResponse> createProduct(@RequestBody ProductRequest.create create){
        User user = userService.getUserByCode(create.getUserCode());
        Product product = Product.builder()
                .title(create.getTitle())
                .contents(create.getContents())
                .price(create.getPrice())
                .region(create.getRegion())
                .user(user)
                .status(create.getStatus()).build();
        product=productService.createProduct(product);
        ProductResponse.create productIdx = new ProductResponse.create(product.getProductIdx());
        return ResponseEntity.ok(new JsonResponse(200,true,"createProduct", productIdx));
    }

    @GetMapping("/post")
    public ResponseEntity<JsonResponse> getProduct(@RequestBody ProductRequest.idx idx){
        Product product = productService.getProduct(idx.getProductIdx());
        System.out.println(product.toString());
        ProductResponse.getProduct newProduct = new ProductResponse.getProduct(product);
        return ResponseEntity.ok(new JsonResponse(200,true,"getProduct",newProduct));
    }

    @GetMapping("/postList")
    public ResponseEntity<JsonResponse> getProductList(){
        List<Product> productList = productService.getProductList();
        return ResponseEntity.ok(new JsonResponse(200,true,"getProductList",productList));
    }

    @GetMapping("/user/postList")
    public ResponseEntity<JsonResponse> getProductListByUser(@RequestBody ProductRequest.get getProduct){
        List<Product> productList = productService.getUserProductList(getProduct.getUserCode());
        return ResponseEntity.ok(new JsonResponse(200,true,"getProductListByUser",productList));
    }


    @PutMapping("/post")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductRequest.update update){
        Product product = productService.getProduct(update.getProductIdx());
        if(!update.getUserCode().equals(product.getUser().getCode()))
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"잘못된 유저의 접근"));
        product.update(update);
        product = productService.updateProduct(product);
        ProductResponse.update result = new ProductResponse.update(product.getProductIdx());
        return ResponseEntity.ok(new JsonResponse(200,true,"updateProduct",result));
    }

    @DeleteMapping("/post")
    public ResponseEntity<Object> deleteProduct(@RequestBody ProductRequest.delete delete){
        try{
            productService.deleteProduct(delete);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"잘못된 유저의 접근"));
        }
        return ResponseEntity.ok(new JsonResponse(200,true,"deleteProduct",null));
    }


}
