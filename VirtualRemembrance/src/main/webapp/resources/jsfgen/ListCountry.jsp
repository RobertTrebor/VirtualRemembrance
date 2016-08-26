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
        <title>Country List</title>
    </head>
    <body>
        <f:view>
            <h1>Country List</h1>
            <h:form>
              <h:commandButton action="#{country.startCreate}" value="Create"/>

              <h:dataTable value='#{country.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="countrycode"/>
                      </f:facet>
                      <h:outputText value="#{item.countrycode}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="countrycode"/>
                      </f:facet>
                      <h:outputText value="#{item.countrycode}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="countryname"/>
                      </f:facet>
                      <h:outputText value="#{item.countryname}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{country.startView}">
                          <f:param name="id" value="#{item.countrycode}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{country.startEdit}">
                          <f:param name="id" value="#{item.countrycode}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{country.delete}">
                          <f:param name="id" value="#{item.countrycode}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
