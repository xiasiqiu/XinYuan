package com.xinyuan.xyshop.bean;

import java.io.Serializable;

/**
 * Created by f-x on 2017/10/1710:15
 */

public class ExpressItemBean implements Serializable{
    private static final long serialVersionUID = -5601018308654973974L;
    private String AcceptStation;
    private String AcceptTime;



    public String getAcceptStation() {
        return AcceptStation;
    }

    public String getAcceptTime() {
        return AcceptTime;
    }

    @Override
    public String toString() {
        return "ExpressItemBean{" +
                "AcceptStation='" + AcceptStation + '\'' +
                ", AcceptTime='" + AcceptTime + '\'' +
                '}';
    }
}
