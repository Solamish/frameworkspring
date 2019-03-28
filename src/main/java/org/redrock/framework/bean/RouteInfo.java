package org.redrock.framework.bean;

import org.redrock.framework.annotation.HttpMethod;

import java.util.Objects;

/**
 * 路由信息
 */
public class RouteInfo {
    private String uri;

    private String method;

    private String uriParam;

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public String getUriParam() {
        return uriParam;
    }

    public void setUriParam(String uriParam) {
        this.uriParam = uriParam;
    }

    public RouteInfo(HttpMethod uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null | getClass() != o.getClass()) {
            return false;
        }
        RouteInfo routeinfo = (RouteInfo) o;
        return Objects.equals(uri, routeinfo.uri) && method == routeinfo.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, method);
    }

}
