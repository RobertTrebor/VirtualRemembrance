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
        <title>Grave List</title>
    </head>
    <body>
        <f:view>
            <h1>Grave List</h1>
            <h:form>
              <h:commandButton action="#{grave.startCreate}" value="Create"/>

              <h:dataTable value='#{grave.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
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
                      <h:outputText value="firstname"/>
                      </f:facet>
                      <h:outputText value="#{item.firstname}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="lastname"/>
                      </f:facet>
                      <h:outputText value="#{item.lastname}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="sex"/>
                      </f:facet>
                      <h:outputText value="#{item.sex}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="datebirth"/>
                      </f:facet>
                      <h:outputText value="#{item.datebirth}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="datedeath"/>
                      </f:facet>
                      <h:outputText value="#{item.datedeath}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="cemetery"/>
                      </f:facet>
                      <h:outputText value="#{item.cemetery}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="graveLoc"/>
                      </f:facet>
                      <h:outputText value="#{item.graveLoc}"/>
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
                      <f:facet name="header">
                      <h:outputText value="vitaPath"/>
                      </f:facet>
                      <h:outputText value="#{item.vitaPath}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="tombstonePath"/>
                      </f:facet>
                      <h:outputText value="#{item.tombstonePath}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{grave.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{grave.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{grave.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
