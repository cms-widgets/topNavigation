/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.topNavigation;

import com.huotu.hotcms.service.service.MallService;
import com.huotu.hotcms.widget.*;
import me.jiangcai.lib.resource.service.ResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.*;


/**
 * @author CJ
 */
public class WidgetInfo implements Widget, PreProcessWidget {
    public static final String VALID_STYLE_TEXT_COLOR = "pagingTColor";
    public static final String VALID_STYLE_TEXT_HOVER_COLOR = "pagingHColor";
    public static final String VALID_PAGE_IDS = "pageIds";
    private static final Log log = LogFactory.getLog(WidgetInfo.class);

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
        return "1.1.0";
    }

    @Override
    public WidgetStyle[] styles() {
        return new WidgetStyle[]{new CenterWidgetStyle(), new DefaultWidgetStyle(), new MallTopWidgetStyle()};
    }


    @Override
    public Map<String, Resource> publicResources() {
        Map<String, Resource> map = new HashMap<>();
        map.put("thumbnail/defaultStyleThumbnail.png", new ClassPathResource("thumbnail/defaultStyleThumbnail.png"
                , getClass().getClassLoader()));
        map.put("thumbnail/centerStyle.png", new ClassPathResource("thumbnail/centerStyle.png"
                , getClass().getClassLoader()));
        map.put("thumbnail/mallTopWidgetThumbnail.png", new ClassPathResource("thumbnail/mallTopWidgetThumbnail.png"
                , getClass().getClassLoader()));
        map.put("img/logo.png", new ClassPathResource("img/logo.png", getClass().getClassLoader()));
        map.put("js/topNavigation.js", new ClassPathResource("js/topNavigation.js", getClass().getClassLoader()));
        return map;
    }

    @Override
    public Resource widgetDependencyContent(MediaType mediaType) {
        if (mediaType.isCompatibleWith(CSS)) {
            return new ClassPathResource("css/topNavigation.css", getClass().getClassLoader());
        }
        if (mediaType.isCompatibleWith(Javascript)) {
            return new ClassPathResource("js/topNavigation.js", getClass().getClassLoader());
        }
        return null;
    }

    @Override
    public void valid(String styleId, ComponentProperties componentProperties) throws IllegalArgumentException {
        WidgetStyle style = WidgetStyle.styleByID(this, styleId);
        //加入控件独有的属性验证
        String textColor = (String) componentProperties.get(VALID_STYLE_TEXT_COLOR);
        String hoverColor = (String) componentProperties.get(VALID_STYLE_TEXT_HOVER_COLOR);
        List pageIds = (List) componentProperties.get(VALID_PAGE_IDS);

        if (textColor == null || hoverColor == null || pageIds == null || textColor.equals("") || hoverColor.equals("") || pageIds.size() <= 0) {
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
        properties.put("pagingTColor", "#000000");
        properties.put("pagingHColor", "#000000");
        properties.put("layout", "topnavbar-full");
        properties.put("position", "");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "首页");
        map1.put("linkPath", "");
        map1.put("visibleValue", "");
        map1.put("flag", 0);
        map1.put("isParent", "false");
        map1.put("visible", "");
        map1.put("visibleName", "");
        map1.put("id", 1);

        Map<String, Object> map2 = new HashMap<>();
        List<Map<String, Object>> children = new ArrayList<>();
        Map<String, Object> map21 = new HashMap<>();
        map21.put("name", "公司动态");
        map21.put("linkPath", "");
        map21.put("visibleValue", "");
        map21.put("flag", 0);
        map21.put("visible", "true");
        map21.put("visibleName", "'hello lihuaixin'");
        map21.put("pid", 2);

        Map<String, Object> map22 = new HashMap<>();
        map22.put("name", "行业动态");
        map22.put("linkPath", "");
        map22.put("visibleValue", "");
        map22.put("visible", "!true");
        map22.put("visibleName", "");
        map22.put("flag", 0);
        map22.put("pid", 2);
        children.add(map21);
        children.add(map21);
        children.add(map22);

        map2.put("name", "动态资讯");
        map2.put("linkPath", "");
        map2.put("visibleValue", "");
        map2.put("isParent", "true");
        map2.put("visible", "");
        map2.put("visibleName", "");
        map2.put("children", children);
        map2.put("flag", 0);
        map2.put("id", 2);

        HashMap map3 = new HashMap();
        map3.put("name", "关于我们");
        map3.put("linkPath", "");
        map3.put("visibleValue", "");
        map3.put("isParent", "false");
        map3.put("visible", "true");
        map3.put("visibleName", "");
        map3.put("flag", 0);
        map3.put("id", 3);

        List<Map<String, Object>> navbarPageInfoModels = new ArrayList<>();
        navbarPageInfoModels.add(map1);
        navbarPageInfoModels.add(map2);
        navbarPageInfoModels.add(map3);

        properties.put(VALID_PAGE_IDS, navbarPageInfoModels);
        properties.put(VALID_STYLE_TEXT_COLOR, "#000000");
        properties.put(VALID_STYLE_TEXT_HOVER_COLOR, "#666666");
        properties.put("contentCenter", "1200px");
        properties.put("topnavbarBody", "1020px");
        properties.put("topnavbarHeight", "80px");
        return properties;
    }


    @Override
    public void prepareContext(WidgetStyle style, ComponentProperties properties, Map<String, Object> variables, Map<String, String> parameters) {
        MallService mallService = getCMSServiceFromCMSContext(MallService.class);
        try {
            variables.put("mallDomain", "http://" + mallService.getMallDomain(CMSContext.RequestContext().getSite().getOwner()));
        } catch (IOException e) {
            log.error("通讯异常", e);
            variables.put("mallDomain", "#");
        }
        variables.put("customerId", CMSContext.RequestContext().getSite().getOwner() != null
                ? CMSContext.RequestContext().getSite().getOwner().getCustomerId() : "");
        List<Map<String, Object>> list = (List<Map<String, Object>>) properties.get(VALID_PAGE_IDS);
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("login", this);

        for (Map<String, Object> map : list) {
            String visible = (String) map.get("visible");
            if (visible != null && !visible.equals("")) {
                Expression exp = parser.parseExpression(visible);
                Object value = exp.getValue(context);
                map.replace("visibleValue", value);
            }
            String visibleName = (String) map.get("visibleName");
            if (visibleName != null && !visibleName.equals("")) {
                Expression exp = parser.parseExpression(visibleName);
                Object value = exp.getValue(context);
                map.replace("name", value);
            }
            String isParent = map.get("isParent").toString();
            if (isParent != null && isParent.equals("true")) {
                List<Map<String, Object>> children = (List<Map<String, Object>>) map.get("children");
                for (Map<String, Object> child : children) {
                    String childVisible = (String) child.get("visible");
                    if (childVisible != null && !childVisible.equals("")) {
                        Expression exp = parser.parseExpression(childVisible);
                        Object value = exp.getValue(context);
                        child.replace("visibleValue", value);
                    }
                    String childVisibleName = (String) child.get("visibleName");
                    if (childVisibleName != null && !childVisibleName.equals("")) {
                        Expression exp = parser.parseExpression(childVisibleName);
                        Object value = exp.getValue(context);
                        child.replace("name", value);
                    }
                }
            }
        }
    }

    public boolean isLogin() {
        MallService mallService = getCMSServiceFromCMSContext(MallService.class);
        return mallService.isLogin(CMSContext.RequestContext().getRequest(), CMSContext.RequestContext().getSite().getOwner());
    }

    public String loginUserName() {
        MallService mallService = getCMSServiceFromCMSContext(MallService.class);
        try {
            return mallService.getLoginUserName(CMSContext.RequestContext().getRequest(), CMSContext.RequestContext().getSite().getOwner());
        } catch (IOException e) {
        }
        return null;
    }
}
