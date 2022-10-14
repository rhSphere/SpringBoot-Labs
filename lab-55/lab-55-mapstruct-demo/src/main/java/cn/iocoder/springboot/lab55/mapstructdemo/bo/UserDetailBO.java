package cn.iocoder.springboot.lab55.mapstructdemo.bo;

public class UserDetailBO {

    private Integer userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    public Integer getUserId() {
        return userId;
    }

    public UserDetailBO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

}
