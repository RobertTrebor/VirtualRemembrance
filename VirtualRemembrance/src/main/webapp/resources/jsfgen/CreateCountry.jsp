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
        <title>Create Country</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create Country</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="countrycode:"/>
                                                    <h:inputText value="#{country.entity.countrycode}" title="countrycode" />
                                                                                                        <h:outputText value="countrycode:"/>
                                                    <h:inputText value="#{country.entity.countrycode}" title="countrycode" />
                                                                                                        <h:outputText value="countryname:"/>
                                                    <h:inputText value="#{country.entity.countryname}" title="countryname" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{country.create}" value="Save" />
                <h:commandButton action="countryList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
