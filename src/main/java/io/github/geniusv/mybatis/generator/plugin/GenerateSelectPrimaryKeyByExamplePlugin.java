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
public class GenerateSelectPrimaryKeyByExamplePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement rootElement = document.getRootElement();
        rootElement.addElement(generateSelectPrimaryKeyByExample(introspectedTable));
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private XmlElement generateSelectPrimaryKeyByExample(IntrospectedTable introspectedTable) {
        //      <select id="selectPrimaryKeyByExample" parameterType="dao.model.RoleExample" resultType="java.lang.Long">
        XmlElement select = new XmlElement("select");
        select.addElement(new TextElement("<!-- WARNING - @mbg.generated -->"));
        select.addAttribute(new Attribute("id", "selectPrimaryKeyByExample"));
        select.addAttribute(new Attribute("resultType", introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().toString()));
        select.addAttribute(new Attribute("parameterType", introspectedTable.getExampleType()));

//        select
        select.addElement(new TextElement("select"));

//        <if test = "distinct" >
//        distinct
//        </if>
        XmlElement ifDinstinct = new XmlElement("if");
        ifDinstinct.addAttribute(new Attribute("test", "distinct != null"));
        ifDinstinct.addElement(new TextElement("distinct"));
        select.addElement(ifDinstinct);

//      id from v_role
        select.addElement(new TextElement(introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName() + " from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

//        <if test = "_parameter != null" >
//        <include refid = "Example_Where_Clause" / >
//        </if>
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid",
                introspectedTable.getExampleWhereClauseId()));
        ifElement.addElement(includeElement);
        select.addElement(ifElement);

//        <if test="orderByClause != null">
//        order by ${orderByClause}
//        </if>
        ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "orderByClause != null"));  //$NON-NLS-2$
        ifElement.addElement(new TextElement("order by ${orderByClause}"));
        select.addElement(ifElement);
        return select;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addMethod(generateSelectPrimaryKeyByExampleMethod(introspectedTable));

        //add @Repository
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        interfaze.addAnnotation("@Repository");
        return true;
    }

    private Method generateSelectPrimaryKeyByExampleMethod(IntrospectedTable introspectedTable) {
        // add selectPrimaryKeyByExample
        Method method = new Method("selectPrimaryKeyByExample");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("java.util.List<" + introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType() + ">");
        method.setVisibility(JavaVisibility.DEFAULT);
        method.setReturnType(returnType);
        Parameter parameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example");
        method.addParameter(parameter);
        method.addJavaDocLine("/**");
        method.addJavaDocLine("* @mbg.generated");
        method.addJavaDocLine("*/");
        return method;
    }
}
