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
import com.huotu.hotcms.widget.entity.PageInfo;
import com.huotu.widget.test.WidgetTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class TestWidgetInfo extends WidgetTest {

    @Override
    protected boolean printPageSource() {
        return true;
    }

    @Override
    protected void editorWork(Widget widget, WebElement editor, Supplier<Map<String, Object>> currentWidgetProperties) {
        try {
            currentWidgetProperties.get();
        } catch (IllegalStateException ignored) {
            assertThat(0).as("save没有属性值，返回异常").isEqualTo(0);
        }
        WebElement treeview = editor.findElement(By.id("navbar-treeview"));
        List<WebElement> ul = treeview.findElements(By.tagName("ul"));
        assertThat(ul).isNotNull();
        List<WebElement> lis = ul.get(0).findElements(By.tagName("li"));
        assertThat(lis.size()).isNotEqualTo(0);
        assertThat(lis.size()).isEqualTo(3);

        WebElement logoDiv = editor.findElement(By.id("logoFile"));
        List<WebElement> input = logoDiv.findElements(By.name("file"));
        assertThat(input).isNotNull();
        assertThat(input.size()).as("图片上传插件").isNotEqualTo(0);

        try {
            currentWidgetProperties.get();
        } catch (IllegalStateException ignored) {
            assertThat(0).as("save没有属性值，返回异常").isEqualTo(0);
        }

    }

    @Override
    protected void browseWork(Widget widget, WidgetStyle style, Function<ComponentProperties, WebElement> uiChanger) {
        ComponentProperties properties = new ComponentProperties();
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

        List<Map<String, Object>> navbarPageInfoModels = new ArrayList<>();
        for (PageInfo pageInfo : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", pageInfo.getTitle());
            map.put("href", pageInfo.getPagePath());
            map.put("pageId", pageInfo.getPageId());
            map.put("parentId", pageInfo.getParent() != null ? pageInfo.getParent().getPageId() : 0);
            navbarPageInfoModels.add(map);
        }
        properties.put(WidgetInfo.VALID_STYLE_TEXT_COLOR, "#eeeeee");
        properties.put(WidgetInfo.VALID_STYLE_TEXT_HOVER_COLOR, "#111111");
        properties.put(WidgetInfo.VALID_LOGO_FILE_URI, "http://placehold.it/106x82?text=logo");
        properties.put(WidgetInfo.VALID_PAGE_IDS, navbarPageInfoModels);
        WebElement webElement = uiChanger.apply(properties);
        List<WebElement> tonavs = webElement.findElements(By.className("topNavigation"));
        assertThat(tonavs.get(0).getText()).isEqualToIgnoringCase("首页");

        List<WebElement> lis = webElement.findElements(By.className("dropdown"));
        assertThat(lis.size()).isEqualTo(3);

    }


}
