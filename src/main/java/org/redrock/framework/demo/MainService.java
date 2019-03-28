package org.redrock.framework.demo;

import org.redrock.framework.bean.ResponseEntity;

/**
 * 测试用service
 */
public class MainService {
    public ResponseEntity yoyoyo() {
        return new ResponseEntity(10000, "hello world!(post)");
    }
}
