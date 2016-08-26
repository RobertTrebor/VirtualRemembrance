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
        <title>Cemetery View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Cemetery View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{cemetery.entity.id}" title="id" />
                                                                                                       <h:outputText value="id:"/>
                                                   <h:outputText value="#{cemetery.entity.id}" title="id" />
                                                                                                       <h:outputText value="name:"/>
                                                   <h:outputText value="#{cemetery.entity.name}" title="name" />
                                                                                                       <h:outputText value="city:"/>
                                                   <h:outputText value="#{cemetery.entity.city}" title="city" />
                                                                                                       <h:outputText value="country:"/>
                                                   <h:outputText value="#{cemetery.entity.country}" title="country" />
                                                                                                       <h:outputText value="zipcode:"/>
                                                   <h:outputText value="#{cemetery.entity.zipcode}" title="zipcode" />
                                                                                                       <h:outputText value="street:"/>
                                                   <h:outputText value="#{cemetery.entity.street}" title="street" />
                                                                                                       <h:outputText value="latitude:"/>
                                                   <h:outputText value="#{cemetery.entity.latitude}" title="latitude" />
                                                                                                       <h:outputText value="longitude:"/>
                                                   <h:outputText value="#{cemetery.entity.longitude}" title="longitude" />
                                                                                
                                                    </h:panelGrid>

                <h:commandButton action="editCemetery" value="Edit" />
                <br>
                <h:commandButton action="cemeteryList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
