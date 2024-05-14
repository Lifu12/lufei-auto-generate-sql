package com.lufei.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.lufei.dto.UserDTO;
import com.lufei.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 模拟用户管理
 * @author kun.li
 */
@RestController
@RequestMapping("users")
public class UserController {


    @PostMapping("login")
    public ResponseResult login(@RequestBody UserDTO userDTO){
        return ResponseResult.success(IdUtil.fastSimpleUUID());
    }

    @GetMapping("info")
    public ResponseResult info(){
        return ResponseResult.success(MapUtil.builder().put("username","KunKun").put("roles", Arrays.asList("admin")).build());
    }
}

