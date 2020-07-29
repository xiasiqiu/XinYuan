package com.xinyuan.xyshop.model;

import com.xinyuan.xyshop.bean.BankBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fx on 2017/9/18.
 * 用户设置安全数据
 */

public class UserSecurityModel implements Serializable {
    private static final long serialVersionUID = -5460532347888842975L;
    private String userWX;
    private String userEmail;
    private String userPhone;
    private String userSina;
    private List<BankBean> bankList;
    private String userQQ;
    private String userPass;


    public String getUserPass() {
        return userPass;
    }

    public String getUserWX() {
        return userWX;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserSina() {
        return userSina;
    }

    public List<BankBean> getBankList() {
        return bankList;
    }

    public String getUserQQ() {
        return userQQ;
    }
}
