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
        <title>Edit Grave</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit Grave</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{grave.entity.id}" title="id" />
                                                                                <h:outputText value="id:"/>
                                                                        <h:inputText value="#{grave.entity.id}" title="id" />
                                                                                <h:outputText value="firstname:"/>
                                                                        <h:inputText value="#{grave.entity.firstname}" title="firstname" />
                                                                                <h:outputText value="lastname:"/>
                                                                        <h:inputText value="#{grave.entity.lastname}" title="lastname" />
                                                                                <h:outputText value="sex:"/>
                                                                        <h:inputText value="#{grave.entity.sex}" title="sex" />
                                                                                <h:outputText value="datebirth:"/>
                                                                        <h:inputText value="#{grave.entity.datebirth}" title="datebirth" />
                                                                                <h:outputText value="datedeath:"/>
                                                                        <h:inputText value="#{grave.entity.datedeath}" title="datedeath" />
                                                                                <h:outputText value="cemetery:"/>
                                                                        <h:selectOneMenu value="#{grave.entity.cemetery}"  title="cemetery">
                                <f:selectItems  value="#{cemetery.allEntitiesAsSelectedItems}"/>
                            </h:selectOneMenu>
                                                                                <h:outputText value="graveLoc:"/>
                                                                        <h:inputText value="#{grave.entity.graveLoc}" title="graveLoc" />
                                                                                <h:outputText value="latitude:"/>
                                                                        <h:inputText value="#{grave.entity.latitude}" title="latitude" />
                                                                                <h:outputText value="longitude:"/>
                                                                        <h:inputText value="#{grave.entity.longitude}" title="longitude" />
                                                                                <h:outputText value="vitaPath:"/>
                                                                        <h:inputText value="#{grave.entity.vitaPath}" title="vitaPath" />
                                                                                <h:outputText value="tombstonePath:"/>
                                                                        <h:inputText value="#{grave.entity.tombstonePath}" title="tombstonePath" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{grave.save}" value="Save"/>
                <h:commandButton action="graveList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
