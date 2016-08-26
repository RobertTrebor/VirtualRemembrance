<!--
  Created by IntelliJ IDEA.
  User: robert
  Date: 26.08.16
  Time: 13:13
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Create Cemetery</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create Cemetery</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{cemetery.entity.id}" title="id" />
                                                                                                        <h:outputText value="id:"/>
                                                    <h:inputText value="#{cemetery.entity.id}" title="id" />
                                                                                                        <h:outputText value="name:"/>
                                                    <h:inputText value="#{cemetery.entity.name}" title="name" />
                                                                                                        <h:outputText value="city:"/>
                                                    <h:inputText value="#{cemetery.entity.city}" title="city" />
                                                                                                        <h:outputText value="country:"/>
                                                    <h:inputText value="#{cemetery.entity.country}" title="country" />
                                                                                                        <h:outputText value="zipcode:"/>
                                                    <h:inputText value="#{cemetery.entity.zipcode}" title="zipcode" />
                                                                                                        <h:outputText value="street:"/>
                                                    <h:inputText value="#{cemetery.entity.street}" title="street" />
                                                                                                        <h:outputText value="latitude:"/>
                                                    <h:inputText value="#{cemetery.entity.latitude}" title="latitude" />
                                                                                                        <h:outputText value="longitude:"/>
                                                    <h:inputText value="#{cemetery.entity.longitude}" title="longitude" />
                                                                                                                </h:panelGrid>

                <h:commandButton action="#{cemetery.create}" value="Save" />
                <h:commandButton action="cemeteryList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
