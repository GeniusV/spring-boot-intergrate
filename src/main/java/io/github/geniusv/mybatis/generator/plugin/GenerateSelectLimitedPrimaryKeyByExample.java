package io.github.geniusv.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
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
public class GenerateSelectLimitedPrimaryKeyByExample extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement root = document.getRootElement();
        root.addElement(generateSelectLimitedPrimaryKeyByExample(introspectedTable));
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addMethod(generateSelectLimitedPrimaryKeyByExampleMethod(introspectedTable));
        if (!interfaze.getAnnotations().contains("@Repository")) {
            interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
            interfaze.addAnnotation("@Repository");
        }
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    public Method generateSelectLimitedPrimaryKeyByExampleMethod(IntrospectedTable introspectedTable) {
        Method selectPrimaryKeyLimitedByExample = new Method();
        selectPrimaryKeyLimitedByExample.setName("selectPrimaryKeyLimitedByExample");
        selectPrimaryKeyLimitedByExample.setVisibility(JavaVisibility.DEFAULT);
        selectPrimaryKeyLimitedByExample.setReturnType(new FullyQualifiedJavaType("java.util.List<" + introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType() + ">"));
        Parameter offset = new Parameter(new FullyQualifiedJavaType("Long"), "offset", "@Param(\"offset\")");
        Parameter num = new Parameter(new FullyQualifiedJavaType("Long"), "num", "@Param(\"num\")");
        Parameter example = new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example", "@Param(\"example\")");
        selectPrimaryKeyLimitedByExample.addParameter(offset);
        selectPrimaryKeyLimitedByExample.addParameter(num);
        selectPrimaryKeyLimitedByExample.addParameter(example);
        selectPrimaryKeyLimitedByExample.addJavaDocLine("/**");
        selectPrimaryKeyLimitedByExample.addJavaDocLine("* @mbg.generated");
        selectPrimaryKeyLimitedByExample.addJavaDocLine("*/");
        return selectPrimaryKeyLimitedByExample;
    }

    public XmlElement generateSelectLimitedPrimaryKeyByExample(IntrospectedTable introspectedTable) {
//          <select id="selectPrimaryKeyLimitedByExample" parameterType="map" resultType="java.lang.Long">
        XmlElement select = new XmlElement("select");
        select.addElement(new TextElement("<!-- WARNING - @mbg.generated -->"));
        select.addAttribute(new Attribute("id", "selectPrimaryKeyLimitedByExample"));
        select.addAttribute(new Attribute("parameterType", "map"));
        select.addAttribute(new Attribute("resultType", introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().toString()));

        //        select
        select.addElement(new TextElement("select"));

//          <if test="example.distinct != null">
//          distinct
//           </if>
        XmlElement ifDinstinct = new XmlElement("if");
        ifDinstinct.addAttribute(new Attribute("test", "example.distinct != null"));
        ifDinstinct.addElement(new TextElement("distinct"));
        select.addElement(ifDinstinct);

//      id from v_role
        select.addElement(new TextElement(introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName() + " from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

//    <if test="_parameter != null">
//      <include refid="Update_By_Example_Where_Clause" />
//    </if>
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid",
                introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
        ifElement.addElement(includeElement);
        select.addElement(ifElement);

//    <if test="example.orderByClause != null">
//                order by ${example.orderByClause}
//    </if>
        ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "example.orderByClause != null"));  //$NON-NLS-2$
        ifElement.addElement(new TextElement("order by ${example.orderByClause}"));
        select.addElement(ifElement);

//    <if test="offset != null and num != null">
//                limit #{offset}, #{num}
//    </if>
        ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "offset != null and num != null"));  //$NON-NLS-2$
        ifElement.addElement(new TextElement("limit #{offset}, #{num}"));
        select.addElement(ifElement);

        return select;
    }
}
