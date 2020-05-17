package com.ithuangyonghua.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {

    public static <T> T copyParamToBean(T bean,Map value){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt,int defaultValue){
        if(strInt==""||strInt==null){
            return defaultValue;
        }
        try{
            return Integer.valueOf(strInt);
        }catch(Exception e){
            e.printStackTrace();
        }
        return defaultValue;
    }
    public static String parseDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATAFORMAT);
        return simpleDateFormat.format(date);
    }
}
