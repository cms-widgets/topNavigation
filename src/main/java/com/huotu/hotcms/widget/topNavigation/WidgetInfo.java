/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.topNavigation;

import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import me.jiangcai.lib.resource.service.ResourceService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * @author CJ
 */
public class WidgetInfo implements Widget{
    public static final String VALID_STYLE_TEXT_COLOR = "pagingTColor";
    public static final String VALID_STYLE_TEXT_HOVER_COLOR = "pagingHColor";
    public static final String VALID_PAGE_IDS = "pageIds";

    @Override
    public String groupId() {
        return "com.huotu.hotcms.widget.topNavigation";
    }

    @Override
    public String widgetId() {
        return "topNavigation";
    }

    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "顶部导航";
        }
        return "topNavigation";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "这是一个顶部导航组件，你可以对组件进行自定义修改。";
        }
        return "This is a top Navigation,  you can make custom change the component.";
    }

    @Override
    public String dependVersion() {
        return "1.0-SNAPSHOT";
    }

    @Override
    public WidgetStyle[] styles() {
        return new WidgetStyle[]{new CenterWidgetStyle(),new DefaultWidgetStyle()};
    }


    @Override
    public Map<String, Resource> publicResources() {
        Map<String, Resource> map = new HashMap<>();
        map.put("thumbnail/defaultStyleThumbnail.png",new ClassPathResource("thumbnail/defaultStyleThumbnail.png"
                ,getClass().getClassLoader()));
        map.put("img/logo.png",new ClassPathResource("img/logo.png" ,getClass().getClassLoader()));
        map.put("js/topNavigation.js",new ClassPathResource("js/topNavigation.js" ,getClass().getClassLoader()));
        return map;
    }

    @Override
    public Resource widgetDependencyContent(MediaType mediaType) {
        if (mediaType.isCompatibleWith(CSS)){
            return  new ClassPathResource("css/topNavigation.css",getClass().getClassLoader());
        }
        if (mediaType.isCompatibleWith(Javascript)){
            return  new ClassPathResource("js/topNavigation.js",getClass().getClassLoader());
        }
        return null;
    }

    @Override
    public void valid(String styleId, ComponentProperties componentProperties) throws IllegalArgumentException {
        WidgetStyle style = WidgetStyle.styleByID(this,styleId);
        //加入控件独有的属性验证
        String textColor = (String) componentProperties.get(VALID_STYLE_TEXT_COLOR);
        String hoverColor = (String) componentProperties.get(VALID_STYLE_TEXT_HOVER_COLOR);
        List pageIds = (List) componentProperties.get(VALID_PAGE_IDS);

        if (textColor == null ||  hoverColor == null || pageIds== null || textColor.equals("") ||  hoverColor.equals("") || pageIds.size()<=0) {
            throw new IllegalArgumentException("控件属性缺少");
        }

    }

    @Override
    public Class springConfigClass() {
        return null;
    }


    @Override
    public ComponentProperties defaultProperties(ResourceService resourceService) {
        ComponentProperties properties = new ComponentProperties();
        properties.put("pagingTColor","#000000");
        properties.put("pagingHColor","#000000");
        Map<String,Object> map1 = new HashedMap();
        map1.put("name","首页");
        map1.put("linkPath","");
        map1.put("flag",0);
        map1.put("isParent","false");
        map1.put("id",1);

        Map<String,Object> map2 = new HashedMap();
        List<Map<String,Object>> children = new ArrayList<>();
        Map<String,Object> map21 = new HashedMap();
        map21.put("name","公司动态");
        map21.put("linkPath","");
        map21.put("flag",0);
        map21.put("pid",2);

        Map<String,Object> map22 = new HashedMap();
        map22.put("name","行业动态");
        map22.put("linkPath","");
        map22.put("flag",0);
        map22.put("pid",2);
        children.add(map21);
        children.add(map22);

        map2.put("name","动态资讯");
        map2.put("linkPath","");
        map2.put("isParent","true");
        map2.put("children",children);
        map2.put("flag",0);
        map2.put("id",2);

        Map<String,Object> map3 = new HashedMap();
        map3.put("name","关于我们");
        map3.put("linkPath","");
        map3.put("isParent","false");
        map3.put("flag",0);
        map3.put("id",3);

        List<Map<String,Object>> navbarPageInfoModels = new ArrayList<>();
        navbarPageInfoModels.add(map1);
        navbarPageInfoModels.add(map2);
        navbarPageInfoModels.add(map3);

        properties.put(VALID_PAGE_IDS,navbarPageInfoModels);
        properties.put(VALID_STYLE_TEXT_COLOR,"#000000");
        properties.put(VALID_STYLE_TEXT_HOVER_COLOR,"#666666");
        return properties;
    }

}
