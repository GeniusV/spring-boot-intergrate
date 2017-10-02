package io.github.geniusv.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 10/2/17.
 */
public class RedisCachePlugin extends PluginAdapter{
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement root = document.getRootElement();
        root.addElement(getCacheElement(introspectedTable));
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    public XmlElement getCacheElement(IntrospectedTable introspectedTable) {
        //         <cache type="org.mybatis.caches.redis.RedisCache" />
        XmlElement cacheElement = new XmlElement("cache");
        cacheElement.addAttribute(new Attribute("type", "org.mybatis.caches.redis.RedisCache"));
        cacheElement.addElement(new TextElement("<!-- WARNING - @mbg.generated -->"));
        return cacheElement;
    }
}
