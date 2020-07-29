package com.xinyuan.xyshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by f-x on 2017/10/1710:16
 */

public class ExpressBean implements Serializable {
    private static final long serialVersionUID = 6639604944260463872L;
    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String EBusinessID;
    private boolean Success;

    private List<ExpressItemBean> Traces;


    public String getLogisticCode() {
        return LogisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public String getState() {
        return State;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public List<ExpressItemBean> getTraces() {
        return Traces;
    }

    @Override
    public String toString() {
        return "ExpressBean{" +
                "LogisticCode='" + LogisticCode + '\'' +
                ", ShipperCode='" + ShipperCode + '\'' +
                ", State='" + State + '\'' +
                ", EBusinessID='" + EBusinessID + '\'' +
                ", Success=" + Success +
                ", Traces=" + Traces +
                '}';
    }
}
