<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>1
<meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <%@ include file="/common/baseFile.jsp"%>
    <style>
        .button-div{
            width:100%;
            background-color:#e0e0e0;
            margin-top:10px;
            text-align:center;
            padding:15px 0px;
        }
        .main-div{
            text-align:center;
            font-size:13px;
            overflow:hidden;
        }
        .main-div div{
            margin-top:10px;
        }
        .button-div{
            position: absolute;
            bottom:0;
        }
    </style>
</head>
<body class="bg-gray"  style="height:296px;overflow:hidden;" >
<input type="hidden" id="platUser_id" value="${platUserMap.platUser_id }">
<div class="bg-white main-div">
    <div>
        <label>用户编号：</label>
        <input id="number" type="text" name="number" class="normal-input" value="${platUserMap.number }"/>
    </div>
    <div>
        <label>用户名称：</label>
        <input id="realName" type="text" name="realName" class="normal-input" value="${platUserMap.realname }"/>
    </div>
    <div>
        <label>选择权限：</label>
        <select class="normal-input" name="role_id" id="role_id">
            <c:forEach items="${roleList}" var="role">
               <option value="${role.id}" >${role.role_name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label>手机号：</label>
        <input id="phone" type="number" name="phone" class="normal-input" value="${platUserMap.phone }"/>
    </div>
    <div>
        <label>登录密码：</label>
        <input id="login_pwd" type="text" name="login_pwd" class="normal-input" value=""/><%--加盐密码不可逆--%>
    </div>
    <div>
        <label>状态：</label>
        <input style="margin-left: 30px;" name="state" <c:if test="${platUserMap.state==1 }">checked</c:if> type="radio" value="1"/>有效
        <input style="margin-left: 30px;" id="state" name="state" <c:if test="${platUserMap.state==0 }">checked</c:if> type="radio" value="0"/>无效
    </div>
</div>
<div class="button-div">
    <button onclick="saveData();" class="btn btn-info">保存</button>
    <button onclick="cancelSave();" style="margin-left:10px;" class="btn btn-default">取消</button>
</div>
<script type="text/javascript">
    function cancelSave(){
        parent.cancelSave();
    }
    function saveData(){
        var param={
            number:$("#number").val(),
            platUser_id:$("#platUser_id").val(),
            realName:$("#realName").val(),
            role_id:$("select[name='role_id']").find('option:selected').val(),
            phone:$("#phone").val(),
            login_pwd:$("#login_pwd").val(),
            state:$("input[type=radio]:checked").val()
        };
        if (param["number"]== "") {
            layer.tips('请输入编号', '#number');
            return false;
        }
        if (param["realName"]== "") {
            layer.tips('请输入名称', '#realName');
            return false;
        }
        if (param["phone"]== ""||param["phone"]==11) {
            layer.tips('请输入手机号或手机位数不对', '#phone');
            return false;
        }
        /*if (param["login_pwd"]== "") {
            layer.tips('请输入密码', '#login_pwd');
            return false;
        }*/
        if (param["role_id"]== "") {
            layer.tips('请选择权限', '#role_id');
            return false;
        }
        if (param["state"]== "") {
            layer.tips('请选择状态', '#state');
            return false;
        }
        $.post(baseurl+"jurisdiction/addUpPlatUser.htm",param,function(data){
            parent.saveCallBack(data);
        });
    }
</script>
</body>
</html>