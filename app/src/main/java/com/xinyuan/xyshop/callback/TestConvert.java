package com.xinyuan.xyshop.callback;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.convert.Converter;
import com.xinyuan.xyshop.entity.BaseData;
import com.xinyuan.xyshop.entity.CatrgoryResponse;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.util.JsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

import okhttp3.Response;


/**
 * Title:
 * Description:
 * Created by Administrator on 2017/5/11.
 * 作者：fx on 2017/5/11 00:01
 */

public class TestConvert<T> implements Converter<T> {
    protected int code;
    List<GoodCategory> goodsCategoryList;

    @Override
    public T convertSuccess(Response response) throws Exception {
        BaseData baseData = JsonUtil.getBaseData(response.body().string());
        if (baseData != null) {
            this.code = baseData.getCode();
            if (baseData.getCode() == 200) {
                XLog.v(baseData.getDatas().toString());
                CatrgoryResponse catrgoryResponse = new CatrgoryResponse();
                catrgoryResponse.setCode(baseData.getCode());

                List<GoodCategory> goodsCategoryList = (List) JsonUtil.toBean(baseData.getDatas(), "categoryList", new TypeToken<List<GoodCategory>>() {
                }.getType());

                catrgoryResponse.setDatas(goodsCategoryList);
                XLog.list(goodsCategoryList);
                return (T) catrgoryResponse;
            } else if (baseData.getCode() == 400) {
                throw new IllegalStateException("用户授权信息无效");
            } else if (baseData.getCode() == 401) {
                throw new IllegalStateException("用户授权信息无效");
            }
        } else {
            return (T) baseData;
        }
        return (T) baseData;
    }


}
