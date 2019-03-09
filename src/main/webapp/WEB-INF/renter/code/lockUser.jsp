<%@ page pageEncoding="utf8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    </object>
    <meta http-equiv="Content-Type" />
    <title>查看门锁用户</title>
    <style type="text/css">
        .active>a{
            border-top: 2px solid #cc0000 !important;
            color: #cc0000 !important;
            font-weight: bold;
        }

        a:link {
            color: #444547;
            text-decoration: none;
        }

        a:visited {
            color: #444547;
            text-decoration: none;
        }

        a:hover {
            color: #c00;
            text-decoration: underline;
        }
        <!--
        body, div, ul, li {
            padding: 0;
        }

        body {
            font: 12px "宋体";
            text-align: left;
        }

        a:link {
            color: #00F;
            text-decoration: none;
        }

        a:visited {
            color: #00F;
            text-decoration: none;
        }

        a:hover {
            color: #c00;
            text-decoration: underline;
        }

        ul {
            list-style: none;
        }

        .main {
            clear: both;
            padding: 8px;
            text-align: left;
        }

        .menu0 li {
            display: block;
            float: left;
            padding: 4px 0;
            width: 100px;
            text-align: center;
            cursor: pointer;
            background: orange;
        }

        .menu0 li.hover {
            background: #ff0;
        }

        #menu1 li {
            float: left;
            display: block;
            cursor: pointer;
            width: 72px;
            text-align: center;
            line-height: 21px;
            height: 21px;
        }

        #menu1 li.hover {
            background: #fff;
            border-left: 1px solid #333;
            border-top: 1px solid #333;
            border-right: 1px solid #333;
        }

        #main1 ul {
            display: none;
        }

        #main1 ul.block {
            display: block;
        }
        -->
    </style>
</head>
<body>

    <div class="main" id="main0">

        <form id="myForm" method="post" >
            <table id="contentTable"
                   class="table table-striped table-bordered table-condensed" style="width: 970px;">
                <thead>
                <tr>
                    <th width="30" >类别</th>
                    <th width="30">姓名</th>
                    <th width="20">手机</th>
                    <th width="30">开始时间</th>
                    <th width="10">结束时间</th>
                    <th width="10">创建时间</th>
                    <th>下发状态</th>
                </tr>
                </thead>
                <tbody>


                <c:forEach var="l" items="${lockuserlist}">
                    <c:if test="${l.usertype==1 }">
                        <tr>
                            <td class="type">密码</td>
                            <td class="linkNameTxt">${l.username}</td>
                            <td class="linkNameTxt">${l.phonenumber}</td>
                            <td class="linkNameTxt"><fmt:formatDate value="${l.validfrom }" type="date" pattern="yyyy-MM-dd"/></td>
                            <td class="linkNameTxt">
                                <fmt:formatDate value="${l.validthrough}" type="date" pattern="yyyy-MM-dd"/>
                                <c:if test="${l.validthrough<now}">(已过期)</c:if>
                            </td>
                            <td class="linkNameTxt"><fmt:formatDate value="${l.createtime }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td class="linkNameTxt">
                                <c:if test="${l.status==0}">正在下发</c:if><c:if test="${l.status==1}">已下发</c:if>
                                <c:if test="${l.status==7}">正在更新有效期</c:if><c:if test="${l.status==8}">正在删除</c:if>
                            </td>

                        </tr>
                    </c:if>
                </c:forEach>

                <c:forEach var="l" items="${lockuserlist}">
                    <c:if test="${l.usertype==3 }">
                        <tr>
                            <td class="type">MF卡</td>
                            <td class="linkNameTxt">${l.username}</td>
                            <td class="linkNameTxt">${l.phonenumber}</td>
                            <td class="linkNameTxt"><fmt:formatDate value="${l.validfrom }" type="date" pattern="yyyy-MM-dd"/></td>
                            <td class="linkNameTxt">
                                <fmt:formatDate value="${l.validthrough}" type="date" pattern="yyyy-MM-dd"/>
                                <c:if test="${l.validthrough<now}">(已过期)</c:if>
                            </td>
                            <td class="linkNameTxt"><fmt:formatDate value="${l.createtime }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td class="linkNameTxt">
                                <c:if test="${l.status==0}">正在下发</c:if><c:if test="${l.status==1}">已下发</c:if>
                                <c:if test="${l.status==7}">正在更新有效期</c:if><c:if test="${l.status==8}">正在删除</c:if>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                <c:forEach var="l" items="${lockuserlist}">
                    <c:if test="${l.usertype==4 }">
                        <tr>
                            <td class="type">ID卡</td>
                            <td class="linkNameTxt">${l.username}</td>
                            <td class="linkNameTxt">${l.phonenumber}</td>
                            <td class="linkNameTxt"><fmt:formatDate value="${l.validfrom }" type="date" pattern="yyyy-MM-dd"/></td>
                            <td class="linkNameTxt">
                                <fmt:formatDate value="${l.validthrough}" type="date" pattern="yyyy-MM-dd"/>
                                <c:if test="${l.validthrough<now}">(已过期)</c:if>
                            </td>
                            <td class="linkNameTxt"><fmt:formatDate value="${l.createtime }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td class="linkNameTxt">
                                <c:if test="${l.status==0}">正在下发</c:if><c:if test="${l.status==1}">已下发</c:if>
                                <c:if test="${l.status==7}">正在更新有效期</c:if><c:if test="${l.status==8}">正在删除</c:if>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
            <br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="hidden" name="op" class="op" />

        </form>
    </div>
<div style="display: none;">
    <input type="text" class="infoStr">
</div>
<br />

</body>
</html>