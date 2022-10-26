package com.clone.carrot.controller;

import com.clone.carrot.config.JwtService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final UserService userService;
    private final ProductService productService;
    private final JwtService jwtTokenProvider;

    @PostMapping("/auth/post")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest.create create, HttpServletRequest request){
        String userCode = jwtTokenProvider.resolveToken(request);
        User user = userService.getUserByCode(userCode);
//        if(!user.getCode().equals(userCode))
//            return ResponseEntity.badRequest().body(new ErrorResponse(400,"updateProduct","잘못된 유저의 접근"));
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

    @GetMapping("/post/{postIdx}")
    public ResponseEntity<JsonResponse> getProduct(@PathVariable Long postIdx){
        Product product = productService.getProduct(postIdx);
        System.out.println(product.toString());
        ProductResponse.getProduct newProduct = new ProductResponse.getProduct(product);
        return ResponseEntity.ok(new JsonResponse(200,true,"getProduct",newProduct));
    }

    @GetMapping("/postList")
    public ResponseEntity<JsonResponse> getProductList(){
        List<Product> productList = productService.getProductList();
        return ResponseEntity.ok(new JsonResponse(200,true,"getProductList",productList));
    }

    @GetMapping("/auth/postList")
    public ResponseEntity<JsonResponse> getProductListByUser(HttpServletRequest request){
        String userCode = jwtTokenProvider.resolveToken(request);
        List<Product> productList = productService.getUserProductList(userCode);
        return ResponseEntity.ok(new JsonResponse(200,true,"getProductListByUser",productList));
    }


    @PutMapping("/auth/post")
    public ResponseEntity<Object> updateProduct(HttpServletRequest request,@RequestBody ProductRequest.update update){
        String userCode = jwtTokenProvider.resolveToken(request);
        Product product = productService.getProduct(update.getProductIdx());
        if(!userCode.equals(product.getUser().getCode()))
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"updateProduct","잘못된 유저의 접근"));
        product.update(update);
        product = productService.updateProduct(product);
        ProductResponse.update result = new ProductResponse.update(product.getProductIdx());
        return ResponseEntity.ok(new JsonResponse(200,true,"updateProduct",result));
    }

    @DeleteMapping("/auth/post")
    public ResponseEntity<Object> deleteProduct(HttpServletRequest request,@RequestBody ProductRequest.delete delete){
        try{
            String userCode = jwtTokenProvider.resolveToken(request);
            productService.deleteProduct(userCode,delete.getProductId());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"deleteProduct","잘못된 유저의 접근"));
        }
        return ResponseEntity.ok(new JsonResponse(200,true,"deleteProduct",null));
    }


}
