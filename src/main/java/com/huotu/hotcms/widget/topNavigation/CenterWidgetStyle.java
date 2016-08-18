package com.huotu.hotcms.widget.topNavigation;

import com.huotu.hotcms.widget.WidgetStyle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

/**
 * Created by lhx on 2016/8/18.
 */

public class CenterWidgetStyle implements WidgetStyle {

    @Override
    public String id() {
        return "centerTopNavigationStyle";
    }

    @Override
    public String name() {
        return "居中导航条";
    }

    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINESE)) {
            return name();
        }
        return "bootstrap style topNavigation";
    }

    @Override
    public String description() {
        return "基于bootstrap样式的   ";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINESE)) {
            return description();
        }
        return "Based on the bootstrap style by topNavigation";
    }

    @Override
    public Resource thumbnail() {
        return new ClassPathResource("/thumbnail/defaultStyleThumbnail.png", getClass().getClassLoader());
    }

    @Override
    public Resource previewTemplate() {
        return null;
    }

    @Override
    public Resource browseTemplate() {
        return new ClassPathResource("/template/centerStyleBrowseTemplate.html", getClass().getClassLoader());
    }
}
