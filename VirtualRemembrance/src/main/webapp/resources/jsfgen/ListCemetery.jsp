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
        <title>Cemetery List</title>
    </head>
    <body>
        <f:view>
            <h1>Cemetery List</h1>
            <h:form>
              <h:commandButton action="#{cemetery.startCreate}" value="Create"/>

              <h:dataTable value='#{cemetery.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="id"/>
                      </f:facet>
                      <h:outputText value="#{item.id}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="id"/>
                      </f:facet>
                      <h:outputText value="#{item.id}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="name"/>
                      </f:facet>
                      <h:outputText value="#{item.name}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="city"/>
                      </f:facet>
                      <h:outputText value="#{item.city}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="country"/>
                      </f:facet>
                      <h:outputText value="#{item.country}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="zipcode"/>
                      </f:facet>
                      <h:outputText value="#{item.zipcode}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="street"/>
                      </f:facet>
                      <h:outputText value="#{item.street}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="latitude"/>
                      </f:facet>
                      <h:outputText value="#{item.latitude}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="longitude"/>
                      </f:facet>
                      <h:outputText value="#{item.longitude}"/>
                  </h:column>
                                                                                     <h:column>
                      <h:commandButton value="View" action="#{cemetery.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{cemetery.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{cemetery.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
