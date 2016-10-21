package com.huotu.hotcms.widget.topNavigation;

import com.huotu.hotcms.widget.WidgetStyle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

/**
 * Created by lhx on 2016/8/18.
 */

public class MallTopWidgetStyle implements WidgetStyle {

    @Override
    public String id() {
        return "MallTopWidgetStyle";
    }

    @Override
    public String name() {
        return "商城顶部菜单条";
    }

    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return name();
        }
        return "bootstrap style shopping Mall Top menu";
    }

    @Override
    public String description() {
        return "基于bootstrap样式的商城顶部菜单条";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return description();
        }
        return "Based on the bootstrap style by shopping Mall Top menu";
    }

    @Override
    public Resource thumbnail() {
        return new ClassPathResource("/thumbnail/mallTopWidgetThumbnail.png", getClass().getClassLoader());
    }

    @Override
    public Resource previewTemplate() {
        return null;
    }

    @Override
    public Resource browseTemplate() {
        return new ClassPathResource("/template/mallTopStyleBrowseTemplate.html", getClass().getClassLoader());
    }
}
