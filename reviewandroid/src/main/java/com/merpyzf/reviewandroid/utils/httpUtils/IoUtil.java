package com.merpyzf.reviewandroid.utils.httpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 处理Io流的帮助类
 * @author wangke
 */

public class IoUtil {


    /**
     * 把stream转换成字符串
     * @param is
     * @return
     */
    public static String stream2Str(InputStream is){


        InputStreamReader isReader = null;
        BufferedReader bReader = null;
        try {
             isReader = new InputStreamReader(is);
             bReader = new BufferedReader(isReader);


            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bReader.readLine())!=null){

                sb.append(line);
            }

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭资源
            try {
                bReader.close();
                isReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

        }


        return null;

    }


}
