package com.xh.util;

import com.google.gson.Gson;
import com.xh.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String code;

    private String msg;

    private Object data;

    public static Result customResultEnum(ResultEnum re) {
        return customResultEnum(re, null);
    }

    public static Result customResultEnum(ResultEnum re, Object data) {
        return new Result(re.getCode(), re.getMsg(), data);
    }

    public String toJson() {
        String json = new Gson().toJson(this);
        json = json.replaceAll("\"null\"", "\"\"");
        return json;
    }

}
