package com.xinyuan.xyshop.http;

import com.lzy.okgo.OkGo;
import com.lzy.okrx.RxAdapter;
import com.xinyuan.xyshop.callback.JsonConvert;
import com.xinyuan.xyshop.entity.ApiSpecialItem;
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


}
