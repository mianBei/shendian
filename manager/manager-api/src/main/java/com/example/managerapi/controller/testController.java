package com.example.managerapi.controller;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${foo}")
    private String text;
    @GetMapping("/say")
    public String sayHello(){
        return text;
    }
    @RequestMapping(value = "/test.htm",method = RequestMethod.GET)
    public String test(){

        return "test";
    }
    @RequestMapping(value = "/test1.htm",method = RequestMethod.GET)
    public void test1() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

    }
/*    @RequestMapping("/getUser")
    @Cacheable(value="platformUser-key")
    public User getUser() {
        User platformUser=new User("aa@126.com", "aa", "aa123456", "aa","123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return platformUser;
    }*/
}
