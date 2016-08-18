package cn.les.controller;

import cn.les.entity.VePedto;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 严峰 on 2016/7/11 0011.
 */

@RestController
@RequestMapping("/test")
@EnableAutoConfiguration
public class TestController {
    @RequestMapping("/{id}")
    public VePedto view(@PathVariable("id") Integer id) {
        VePedto user = new VePedto();
        user.setVe_atom(id);
        user.setVe_name("zhang");
        return user;
    }
}
