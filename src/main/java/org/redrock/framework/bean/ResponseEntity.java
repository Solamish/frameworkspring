package org.redrock.framework.bean;

import lombok.Data;

import java.security.PublicKey;

/**
 * 返回的数据类
 */
@Data
public class ResponseEntity<T> {
    int code;
    String info;
    T data;

    public ResponseEntity(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public ResponseEntity(int code, String info, T t) {
        this.code = code;
        this.info = info;
        this.data = t;

    }
}
