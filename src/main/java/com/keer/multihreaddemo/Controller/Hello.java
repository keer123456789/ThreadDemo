package com.keer.multihreaddemo.Controller;

import com.keer.multihreaddemo.Service.IThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: multihreaddemo
 * @BelongsPackage: com.keer.multihreaddemo.Controller
 * @Author: keer
 * @CreateTime: 2019-07-14 22:57
 * @Description: 请求接收，用于测试线程
 */
@RestController
public class Hello {
    private static Logger logger = LoggerFactory.getLogger(Hello.class);
    @Autowired
    IThreadService threadService;

    @GetMapping("/test/{total}")
    public String test(@PathVariable int total) {
        logger.info("###开启" + total + "个线程###");
        logger.info("###开始执行请求###");
        for (int i = 0; i < total; i++) {
            threadService.executeAsync(""+i);
        }

        return "success";
    }
}