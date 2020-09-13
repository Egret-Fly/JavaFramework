package com.itheima.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private String Driver;
    private String Url;
    private String Username;
    private String Password;

    private Map<String,Mapper> mappers=new HashMap<String,Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {

        this.mappers.putAll(mappers);

    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDriver() {
        return Driver;
    }

    public String getUrl() {
        return Url;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
