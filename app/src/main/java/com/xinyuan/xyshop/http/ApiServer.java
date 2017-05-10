package com.xinyuan.xyshop.http;

import com.lzy.okgo.OkGo;
import com.lzy.okrx.RxAdapter;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.callback.TestConvert;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
import com.xinyuan.xyshop.entity.CatrgoryResponse;
import com.xinyuan.xyshop.entity.GoodCategory;
import com.xinyuan.xyshop.entity.LzyResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by fx on 2017/5/9 0009.
 */

public class ApiServer {


    public static Observable<LzyResponse<List<ApiSpecialItem>>> getApiSpecialList(String header, String param) {
        return OkGo.get(Urls.API_INDEX)
                .getCall(new JsonConvert<LzyResponse<List<ApiSpecialItem>>>() {
                }, RxAdapter.<LzyResponse<List<ApiSpecialItem>>>create());

    }

    public static Observable<CatrgoryResponse<List<GoodCategory>>> getCategoryList(String header, String param) {
        return OkGo.get(Urls.URL_GOODS_CATEGORY)
                .getCall(new TestConvert<CatrgoryResponse<List<GoodCategory>>>() {
                }, RxAdapter.<CatrgoryResponse<List<GoodCategory>>>create());

    }

}
