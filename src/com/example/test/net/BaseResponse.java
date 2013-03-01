package com.example.test.net;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.test.util.Logger;

public abstract class BaseResponse<T> {
	
	public interface ResponseLinstener<T> {
		public void response(T t);
	}

	private Map<String, String> params = new HashMap<String, String>();
	private JSONObject result;
	private ResponseLinstener<T> listener;
	
	protected void addParam(String key, String value) {
		params.put(key, value);
	}
	
	public void setResponseListener(ResponseLinstener<T> listener) {
		this.listener = listener;
	}

	void testForResult(JSONObject json) {
		result = json;
	}
	
	public void execute() {
		if (result == null) {
			// GO TO Server
		
		}
		try {
			T resultObj = parse(result);
			onResult(resultObj);
		} catch (Exception e) {
			Logger.e(e, "", "");
		}
	}
	
	protected abstract T parse(JSONObject json) throws JSONException;
	
	private void onResult(T t) {
		if (listener != null) {
			listener.response(t);
		}
	}
}
