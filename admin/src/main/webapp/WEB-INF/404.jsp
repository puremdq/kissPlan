<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.aojiaoo.core.common.ServerResponse" %>
<%@ page import="com.aojiaoo.core.common.ResponseCode" %>
<%@ page import="com.aojiaoo.utils.WebUtils" %>
<%@ page import="com.aojiaoo.utils.JsonUtil" %>
<%

    ServerResponse serverResponse = ServerResponse.createByResponseCode(ResponseCode.NOT_FOUND);
    WebUtils.writeBody(response, JsonUtil.toJson(serverResponse));
%>