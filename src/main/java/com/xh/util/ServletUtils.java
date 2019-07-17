package com.xh.util;

import com.xh.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
public class ServletUtils {

    public static void printResultJsonToPage(ServletResponse response, ResultEnum resultEnum) {
        PrintWriter out = null;
        try {
            // 设置字符集为'UTF-8'
            // 设置服务器端的编码
            // 默认是ISO-8859-1；该方法必须在response.getWriter()之前进行设置
            response.setCharacterEncoding("UTF-8");
            // 设置格式为text/json
            // 等同于response.setHeader("contentType", "text/html;charset=utf-8”);它其实会覆盖response.setCharacterEncoding("utf-8”)
            response.setContentType("application/json;charset=UTF-8");
            out = response.getWriter();
            out.write(Result.customResultEnum(resultEnum).toJson());
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

}
