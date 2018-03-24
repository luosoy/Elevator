package com.luosoy.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 描述: Bean转换工具类
 * 版权: 税友软件集团股份有限公司
 * 日期: 2018/2/1
 * 作者: jsh
 */
public class BeanUtil {


    /**
     * @Description map转换成javaBean,map中key要与目标javaBean中属性的名字严格对应(大小写敏感)
     * @param map
     * @param clazz
     * @Return T
     * @Author jsh
     * @Date 2018/2/2
     */
    public static<T> T mapToBean(Map<String,Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (map == null) {
            return null;
        }
        T obj = clazz.newInstance();
        BeanUtils.populate(obj,map);
        return obj;
    }
    /**
     * @Description map转换成model对象,map中key与目标model中属性名对应,忽略大小写,有@Column注解,则以@Column中name值作为映射
     * @param map
     * @param clazz
     * @Return T
     * @Author jsh
     * @Date 2018/2/2
     */
    public static<T> T mapToModel(Map<String,Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        if (map == null) {
            return null;
        }
        T obj = clazz.newInstance();

        Map<String, Object> tempMap = new LinkedCaseInsensitiveMap<Object>();
        tempMap.putAll(map);
        for(Field f:clazz.getDeclaredFields()){
            Object value;
            Column c = f.getAnnotation(Column.class);
            if(c!=null){
                value = tempMap.get(c.name());
            }else{
                value = tempMap.get(f.getName());
            }
            if(value != null){
                BeanUtils.setProperty(obj,f.getName(),value);
            }
        }
        return obj;
    }

    /**
     * @Description map list转换成javaBean list,map中key要与目标javaBean中属性的名字严格对应(大小写敏感)
     * @param mapList
     * @param clazz
     * @Return java.util.List<T>
     * @Author jsh
     * @Date 2018/2/2
     */
    public static<T> List<T> mapListToBeanList(List<LinkedHashMap<String, Object>> mapList, Class<T> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {

        if(CollectionUtils.isEmpty(mapList)){
            return null;
        }
        List<T> resList = new ArrayList<T>();
        for(Map<String,Object> map : mapList){
            resList.add(mapToBean(map,clazz));
        }
        return resList;
    }
    /**
     * @Description map list转换成model list,map中key与目标model中属性名对应,忽略大小写,若属性上有@Column注解,则以@Column中name值作为映射
     * @param mapList
     * @param clazz
     * @Return java.util.List<T>
     * @Author jsh
     * @Date 2018/2/2
     */
    public static<T> List<T> mapListToModelList(List<LinkedHashMap<String, Object>> mapList, Class<T> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if(CollectionUtils.isEmpty(mapList)){
            return null;
        }
        List<T> resList = new ArrayList<T>();
        for(Map<String,Object> map : mapList){
            resList.add(mapToModel(map,clazz));
        }
        return resList;
    }
}
