package org.redrock.framework.bean;

import sun.dc.pr.PRError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 框架的上下文
 */
public class SpringContext {

    private HttpServletRequest request;

    private HttpServletResponse response;

    /**
     * 参数map
     */
    private Map<String, String> paramMap;

    private RouteInfo routeInfo;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public SpringContext(HttpServletRequest request, HttpServletResponse response, RouteInfo routeInfo) {
        this.request = request;
        this.response = response;
        this.routeInfo = routeInfo;
        paramMap = new HashMap<>();
    }

    public void putParam(String key, String value) {
        this.paramMap.put(key, value);
    }

    public String getParam(String key) {
        return this.paramMap.get(key);
    }
}
