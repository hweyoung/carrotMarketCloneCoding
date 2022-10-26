package com.clone.carrot.controller;

import com.clone.carrot.config.JwtService;
import com.clone.carrot.domain.User;
import com.clone.carrot.dto.JsonResponse;
import com.clone.carrot.dto.UserRequest;
import com.clone.carrot.dto.UserResponse;
import com.clone.carrot.service.MessageService;
import com.clone.carrot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final MessageService messageService;
    private final UserService userService;
    private final JwtService jwtService;


    //로그인시 핸드폰 인증
    @PostMapping("/user/phone")
    public ResponseEntity<JsonResponse> verificationPhone(@RequestBody UserRequest.login loginUser){
        String random = userService.randomNumber();
        messageService.sendMessage(loginUser.getPhone(), random);
        return ResponseEntity.ok(new JsonResponse(200,true,"verificationPhone",new UserResponse.randomNum(random)));
    }

    //유저에게 보낸 random number 인증
    @PostMapping("/user/checknum")
    public ResponseEntity<JsonResponse> validateRandomNumber(@RequestBody UserRequest.checknum num){
        if(num.getSendMessage().equals(num.getUserMessage())){
            return ResponseEntity.ok(new JsonResponse(200,true,"validateRandomNumber",null));
        }
        else return ResponseEntity.badRequest().body(new JsonResponse(400,false,"bad_request",null));
    }

    @PostMapping("/user/login")
    public ResponseEntity<JsonResponse> loginUser(@RequestBody UserRequest.loginUser loginUser){
        User user = null;
        String token;
        try{
            user = userService.getUserByPhone(loginUser.getPhone());
            token = jwtService.createToken(user.getCode());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new JsonResponse(400,false,"loginUser","redirect JoinUser"));
        }
        return ResponseEntity.ok(new JsonResponse(200,true,"loginUser",token));
    }

    @PostMapping("/user/join")
    public ResponseEntity<JsonResponse> joinUser(@RequestBody UserRequest.joinUser joinUser){
        try{
            userService.getUserByPhone(joinUser.getPhone());
        }catch (IllegalArgumentException e){
            String code = userService.randomNumber();
            System.out.println(code);
            User user = User.builder()
                    .phone(joinUser.getPhone())
                    .email(joinUser.getEmail())
                    .nickname(joinUser.getNickname())
                    .code(code).build();
            System.out.println("user : "+user.toString());
            user = userService.joinUser(user);
            String token = jwtService.createToken(user.getCode());
            return ResponseEntity.ok(new JsonResponse(200,true,"joinUser", token));
        }
            return ResponseEntity.badRequest().body(new JsonResponse(400,false,"loginUser","이미 회원가입된 유저"));
    }

    @GetMapping("/user/{userCode}")
    public ResponseEntity<JsonResponse> getUser(@PathVariable String userCode){
        User user = userService.getUserByCode(userCode);
        if(user==null) return ResponseEntity.badRequest().body(new JsonResponse(400,false,"getUser","등록되지 않은 유저"));
        UserResponse.get response = UserResponse.get.builder()
                .code(user.getCode())
                .email(user.getEmail())
                .mannerTemp(user.getMannerTemp())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .status(user.getStatus())
                .build();
        return ResponseEntity.ok(new JsonResponse(200,true,"getUser",response));
    }

    @PutMapping("/auth/user")
    public ResponseEntity<JsonResponse> updateUser(@RequestBody UserRequest.update update, HttpServletRequest request){
        String userCode = jwtService.resolveToken(request);
        System.out.println("userCode : "+userCode);
        userService.updateUser(userCode,update);
        return ResponseEntity.ok(new JsonResponse(200,true,"updateUser",null));
    }


}
