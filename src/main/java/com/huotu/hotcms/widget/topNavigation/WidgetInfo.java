/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.topNavigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.huotu.hotcms.service.model.NavbarPageInfoModel;
import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import com.huotu.hotcms.widget.entity.PageInfo;
import me.jiangcai.lib.resource.service.ResourceService;
import org.apache.http.entity.ContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * @author CJ
 */
public class WidgetInfo implements Widget{
    public static final String VALID_STYLE_TEXT_COLOR = "pagingTColor";
    public static final String VALID_STYLE_TEXT_HOVER_COLOR = "pagingHColor";
    public static final String VALID_PAGEIDS = "pageIds";
    public static final String VALIE_LOGO_FILE_URI = "logoFileUri";

    /*
     * 指定风格的模板类型 如：html,text等
     */
    public static final String VALID_STYLE_TEMPLATE = "styleTemplate";

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
        if (locale.equals(Locale.CHINESE)) {
            return "A custom Widget";
        }
        return "topNavigation";
    }

    @Override
    public String description() {
        return "这是一个 A custom Widget，你可以对组件进行自定义修改。";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINESE)) {
            return description();
        }
        return "This is a topNavigation,  you can make custom change the component.";
    }

    @Override
    public int dependBuild() {
        return 0;
    }

    @Override
    public WidgetStyle[] styles() {
        return new WidgetStyle[]{new DefaultWidgetStyle()};
    }


    @Override
    public Resource widgetJs() {
        return new ClassPathResource("js/topNavigation.js", getClass().getClassLoader());
    }


    @Override
    public Map<String, Resource> publicResources() {
        Map<String, Resource> map = new HashMap<>();
        map.put("thumbnail/defaultStyleThumbnail.png",new ClassPathResource("thumbnail/defaultStyleThumbnail.png"
                ,getClass().getClassLoader()));
        map.put("img/logo.png",new ClassPathResource("img/logo.png" ,getClass().getClassLoader()));
        return map;
    }

    @Override
    public Resource widgetDependencyContent(ContentType contentType) {
        if (contentType.getMimeType().equalsIgnoreCase("text/css")){
            return  new ClassPathResource("css/topNavigation.css",getClass().getClassLoader());
        }
        return null;
    }

    @Override
    public void valid(String styleId, ComponentProperties componentProperties) throws IllegalArgumentException {
        WidgetStyle[] widgetStyles = styles();
        boolean flag = false;
        if (widgetStyles == null || widgetStyles.length < 1) {
            throw new IllegalArgumentException();
        }
        for (WidgetStyle ws : widgetStyles) {
            if ((flag = ws.id().equals(styleId))) {
                break;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException();
        }
        //加入控件独有的属性验证
        String textColor = (String) componentProperties.get(VALID_STYLE_TEXT_COLOR);
        String hoverColor = (String) componentProperties.get(VALID_STYLE_TEXT_HOVER_COLOR);
        String logoFileUri = (String) componentProperties.get(VALIE_LOGO_FILE_URI);
        List<NavbarPageInfoModel> pageIds = (List) componentProperties.get(VALID_PAGEIDS);

        if (logoFileUri == null ||textColor == null ||  hoverColor == null || pageIds== null ||logoFileUri.equals("")
                || textColor.equals("") ||  hoverColor.equals("") || pageIds.size()<=0) {
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
        properties.put("logoFileUri","http://placehold.it/106x82?text=Logo");
        PageInfo pageInfo1 = new PageInfo();
        pageInfo1.setTitle("首页");
        pageInfo1.setPagePath("");
        pageInfo1.setPageId(1L);

        PageInfo pageInfo2 = new PageInfo();
        pageInfo2.setTitle("新闻");
        pageInfo2.setPagePath("xw");
        pageInfo2.setPageId(2L);

        PageInfo gjxw = new PageInfo();
        gjxw.setTitle("国际新闻");
        gjxw.setPagePath("gjxw");
        gjxw.setPageId(22L);
        gjxw.setParent(pageInfo2);

        PageInfo gnxw = new PageInfo();
        gnxw.setTitle("国内新闻");
        gnxw.setParent(pageInfo2);
        gnxw.setPageId(23L);
        gnxw.setPagePath("gnxw");

        PageInfo zjxw = new PageInfo();
        zjxw.setTitle("浙江新闻");
        zjxw.setParent(gnxw);
        zjxw.setPageId(231L);
        zjxw.setPagePath("zjxw");

        PageInfo pageInfo3 = new PageInfo();
        pageInfo3.setTitle("关于我们");
        pageInfo3.setPagePath("guwm");
        pageInfo3.setPageId(3L);

        List<PageInfo> list = new ArrayList<>();
        list.add(pageInfo1);
        list.add(gjxw);
        list.add(pageInfo2);
        list.add(pageInfo3);
        list.add(gnxw);
        list.add(zjxw);

        List<NavbarPageInfoModel> navbarPageInfoModels = new ArrayList<>();
        for (PageInfo pageInfo : list) {
            NavbarPageInfoModel navbarPageInfoModel = new NavbarPageInfoModel();
            navbarPageInfoModel.setText(pageInfo.getTitle());
            navbarPageInfoModel.setHref(pageInfo.getPagePath());
            navbarPageInfoModel.setPageId(pageInfo.getPageId());
            navbarPageInfoModel.setParentId(pageInfo.getParent() != null ? pageInfo.getParent().getPageId() : 0);
            navbarPageInfoModels.add(navbarPageInfoModel);
        }
        properties.put("pageIds",navbarPageInfoModels);
        return properties;
    }

}
