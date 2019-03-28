package org.redrock.framework.demo;

import org.redrock.framework.annotation.Autowired;
import org.redrock.framework.annotation.Controller;
import org.redrock.framework.annotation.HttpMethod;
import org.redrock.framework.annotation.RequestMapper;
import org.redrock.framework.bean.ResponseEntity;
import org.redrock.framework.bean.SpringContext;

/**
 * 测试用controller
 */
@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapper(value = "/hello")
    public ResponseEntity hello(SpringContext context) {
        return new ResponseEntity(10000, "hello " + context.getParam("name"));
    }

    @RequestMapper(value = "/world")
    public ResponseEntity world(SpringContext context) {
        return new ResponseEntity(10000, "hello world!");
    }

    @RequestMapper(value = "/world", method = HttpMethod.POST)
    public ResponseEntity postWorld(SpringContext context) {
        return mainService.yoyoyo();
    }
}
