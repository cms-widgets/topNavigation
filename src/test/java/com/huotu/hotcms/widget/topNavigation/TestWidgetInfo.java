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
import com.huotu.widget.test.Editor;
import com.huotu.widget.test.WidgetTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
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
    protected void editorWork(Widget widget, Editor editor, Supplier<Map<String, Object>> currentWidgetProperties) {
        WebElement treeview = editor.getWebElement().findElement(By.id("treeView")).findElement(By.className("ztree"));
        List<WebElement> lis = treeview.findElements(By.tagName("li"));
        assertThat(lis.size()).isNotEqualTo(0);
        assertThat(lis.size()).isEqualTo(3);

        try {
            Map map = currentWidgetProperties.get();
            assertThat(map.containsKey(WidgetInfo.VALID_STYLE_TEXT_COLOR)).isTrue();
            assertThat(map.containsKey(WidgetInfo.VALID_STYLE_TEXT_HOVER_COLOR)).isTrue();
            assertThat(map.get(WidgetInfo.VALID_STYLE_TEXT_COLOR))
                    .isEqualTo(widget.defaultProperties(resourceService).get(WidgetInfo.VALID_STYLE_TEXT_COLOR));
            assertThat(map.get(WidgetInfo.VALID_STYLE_TEXT_HOVER_COLOR))
                    .isEqualTo(widget.defaultProperties(resourceService).get(WidgetInfo.VALID_STYLE_TEXT_HOVER_COLOR));

            map = currentWidgetProperties.get();
            ComponentProperties properties = new ComponentProperties();
            properties.putAll(map);
            widget.valid(widget.styles()[0].id(),properties);
        } catch (IllegalStateException ignored) {
            assertThat(0).as("save没有属性值，返回异常").isEqualTo(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void browseWork(Widget widget, WidgetStyle style, Function<ComponentProperties, WebElement> uiChanger) throws IOException {
        ComponentProperties properties = widget.defaultProperties(resourceService);
        WebElement webElement = uiChanger.apply(properties);
        List<WebElement> tonavs = webElement.findElements(By.className("topNavigation"));
        assertThat(tonavs.get(0).getText()).isEqualToIgnoringCase("首页");
        List<WebElement> lis = webElement.findElements(By.className("dropdown"));
        assertThat(lis.size()).isEqualTo(3);
    }

    @Override
    protected void editorBrowseWork(Widget widget, Function<ComponentProperties, WebElement> uiChanger
            , Supplier<Map<String, Object>> currentWidgetProperties) throws IOException {
        ComponentProperties properties = widget.defaultProperties(resourceService);
        WebElement webElement = uiChanger.apply(properties);
        assertThat(webElement.findElement(By.name(WidgetInfo.VALID_STYLE_TEXT_COLOR)).getAttribute("value"))
                .isEqualTo(properties.get(WidgetInfo.VALID_STYLE_TEXT_COLOR));
        assertThat(webElement.findElement(By.name(WidgetInfo.VALID_STYLE_TEXT_HOVER_COLOR)).getAttribute("value"))
                .isEqualTo(properties.get(WidgetInfo.VALID_STYLE_TEXT_HOVER_COLOR));
    }


}
