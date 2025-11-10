vertical-align:middle;
        display:table-cell;
        }
        .subwrap_fankui p {
        width:100%;
        }
        </style>
<script language="javascript">
    var isErrorPage=true;
    var errcode="";
    var popError="";
    function Back(State) {
    if(State==null){
    State=-1;
    }

    var isFromMenu = "" + "" ;

    var exTxcode = "" + "530550" ;

    if("E31020,E31021,E31030,E31031,E31040,E31041,E31042,E31050,E31051,E31052".indexOf(exTxcode)!=-1){
    top.submitTX('100112','MENU_ID=0175,');
    return;
    }

    if("300100,G10000,G10010,G20200,G20100,350401,350100,350800,G10000,350601,380000,490100,490307,490500,490200,B41101,NJ1900,SSI914,930030,930033,SSI924,300100,301001,H30400,490600,G30300,T31000,SSI930,SSI940,SSI960,SSI970,Q11000,NJ1800,NJ1000,900105".indexOf(exTxcode)!=-1&&BROWSER.isFirefox()){
    top.submitTX('100112','MENU_ID=0016,');
    return;
    }

    if(BROWSER.isFirefox()){
    try{
    if(top.sysCurrentMenuId!='undefined'&&top.sysCurrentMenuId!=''&&top.submitTX!='undefined'){
    top.submitTX(top.sysCurrentMenuId);
    return;
    }
    }catch(e){}
    }


    var exList = "G10010,G10000,G20200,G20100,390101,390200,490100,490300,490500,490200,B60100,900105,300100,380000,B41101,NJ0100,NJ0200,NJ0300,NJ0400,NJ0500,NJ0600";

    if(isFromMenu == "1" && exList.indexOf(exTxcode)!=-1){
    State = -3;

    for(var i = 0; i< 3; i++){
    history.go(-1);
    }

    return;
    }

    if(typeof parent!="undefined"&& parent!=null) {
    if ("520108"!="530550"){
    if(typeof checkObj("Menu3BackFlag") != "undefined"){
    var k1=0;
    var k2=0;
    k1=parent.parent.getPubValue2P("historyLength");
    k2=history.length;

    //State=k1-k2-2;
    //alert('State:'+State);

    for(var i =0; i< k2 +3 -k1; i++ ){
    if(history.length>0)
    history.go(-1);
    }
    return;
    }
    }
    }


    history.go(State);
    }
    function checkObj(arg) {
    var r_obj = null;
    if(typeof(arg) == 'object') {
    return arg;
    }
    r_obj = parent.document.getElementById(arg);
    if (r_obj == null) {
    r_obj = parent.document.getElementsByName(arg)[0];
    }
    return r_obj;
    }

    function Load() {
    //progressSH(0);
    try{
    if(typeof parent!="undefined"&& parent!=null) {
    if(typeof parent.document.forms[0]!="undefined"&& parent.document.forms[0]!=null) {

    if(typeof checkObj("QUERYFLAG370001") !="undefined"){


    checkObj("check2").className="btn";
    checkObj("check2").disabled = false;

    checkObj("check3").className="button_lessThan12";
    checkObj("check3").disabled = false;
    }

    if(typeof checkObj("PLflag")!="undefined") {
    var pljf=checkObj("PLflag").value;
    if(pljf=="500507"){
    window.parent.splitstr(window.name);
    } else {
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    if(pljf=="500506"){
    window.parent.defaultimg();
    window.parent.document.forms[0].sflag.value="1";
    } else {
    window.parent.repeatSubmit();
    }
    }
    }

    if(typeof checkObj("ONTIME_PRICE")!="undefined") {
    var price=checkObj("ONTIME_PRICE").value;
    if(price=="470006"){
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    }
    }
    if(typeof checkObj("BIDIRECTION_PRICE")!="undefined") {
    var price=checkObj("BIDIRECTION_PRICE").value;
    if(price=="473110"){
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    }
    }

    //财务分析错误处理
    if(typeof checkObj("caiWuFlag")!="undefined") {
    var price=checkObj("caiWuFlag").value;
    if(price=="1"){
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    }
    }
    //财务分析循环发错误处理
    if(typeof checkObj("caiWuLoopFlag")!="undefined") {
    var price=checkObj("caiWuLoopFlag").value;
    if(price=="1"){
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    }
    }

    if(typeof checkObj("TABLEHEAD")!="undefined") {
    var tablehead = checkObj("TABLEHEAD").value;
    setTimeout("parent.setButtonStyle();",1000);
    }


    //E30608 信用卡余额查询出现错误所需要作出的父页面处理
    if(typeof window.parent.document.forms[0].XIN!="undefined") {

    var XIN=checkObj("XIN").value;
    var sflag=window.parent.document.forms[0].sflag.value;
    if(XIN=="E30608"){
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    checkObj('img'+sflag).src="../V5/images5/icon_plus.gif";
    checkObj('acc'+sflag).style.display="none";

    window.parent.document.forms[0].sflag.value="1";
    window.parent.document.forms[0].picClickFlag.value="1";
    if (checkObj("result")!=null) {
    setChooseTableStyle2(checkObj("result"));
    }
    } else {
    //do something.....
    }
    }

    if(typeof parent.document.forms[0]['qd1']!="undefined") {

    if(typeof window.parent.document.forms[0].timeOut!="undefined"&&("2D0015"=="530550"||"2D0115"=="530550")){
    var b = window.parent.checkTimeout();
    var PER_SEQUREY_TIME = window.parent.document.getElementById("PER_SEQUREY_TIME").value;
    var tran_state="";
    if(b && (tran_state=="2")){
    window.parent.document.getElementById("wait").style.display = "";
    setTimeout("window.parent.document.forms[0].submit()",PER_SEQUREY_TIME*1000);//没超时重新发送取原流水号
    }else{
    window.parent.document.getElementById("wait").style.display = "none";
    //window.parent.document.getElementById("result").height="0%";
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    window.parent.disableYNParent("qd1","1");
    }
    }
    }

    if(typeof parent.document.forms[0]['qd1']!="undefined") {
    var classname=parent.document.forms[0]['qd1'].className;
    var indexflag=classname.indexOf("_dis");
    if(indexflag!=-1){
    classname=classname.substr(0,indexflag);
    }
    parent.document.forms[0]['qd1'].disabled=false;
    parent.document.forms[0]['qd1'].className=classname;
    if("SF0402"=="530550"){
    for(var j=0; j < window.parent.document.getElementsByName('qd1').length; j++){
    parent.document.getElementsByName('qd1')[j].disabled=false;
    parent.document.getElementsByName('qd1')[j].className="btn";
    }
    }
    } else if(typeof window.parent.document.forms[0].sflag!="undefined") {

    sflag=window.parent.document.forms[0].sflag.value;
    if (sflag!="1") {
    if(typeof window.parent.document.forms[0].timeOut!="undefined"&&"310904"=="530550"){

    //var timeOutValue=window.parent.document.getElementById("timeOut").value;
    //var timeOutSec=window.parent.document.getElementById("timeOutSec").value;
    //var timeCount = timeOutSec*1000;//判断获取状态是否超过20秒时间
    //var sendTime = window.parent.document.getElementById("timeOut");
    //var date1 = new Date();
    //if(date1 - Date.parse(new Date(timeOutValue)) > timeCount){
    //alert("获取流水号超时，请稍后再试!");
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    window.parent.frames['result'].document.getElementById('img'+sflag).className = "zhcx_show";
    window.parent.frames['result'].document.getElementById('acc'+sflag).style.display="none";

    window.parent.document.getElementById("timeOut").value="";//重置超时时间；
    window.parent.frames['result'].Load();
    window.parent.document.forms[0].sflag.value="1";
    return;
    //}
    //window.parent.document.forms[0].sflag.value = "1";
    //window.parent.frames['result'].document.getElementById('img'+sflag).src="../V5/images5/icon_plus.gif";
    //window.parent.frames['result'].document.getElementById('acc'+sflag).style.display="none";
    //setTimeout("window.parent.frames['result'].document.getElementById('img"+sflag+"').src='../V5/images5/icon_plus.gif';window.parent.frames['result'].document.getElementById('acc"+sflag+"').style.display='none';parent.window.frames['result'].getData(window.parent.document.forms[0].timeOutAccount.value);",3000);
    //parent.window.frames['result'].getData(window.parent.document.forms[0].timeOutAccount.value);
    //return;
    }
    if(typeof window.parent.frames["result"].document.getElementById("sflagcxk310103")!="undefined"){
    try{
    var sflagcxk310103=window.parent.frames["result"].document.getElementById("sflagcxk310103").value;
    window.parent.frames["result"].document.getElementById("1acc"+sflagcxk310103).style.display="";
    }catch(e){}
    }
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');

    if(sflag=="2") {

    window.parent.frames['result'].Load();
    var multi=window.parent.document.forms[0].sflagmulti.value;
    if(multi!="1"){
    window.parent.frames['result'].document.getElementById(multi).style.display="none";
    }
    } else {

    if(typeof window.parent.document.forms[0].picClickFlag!="undefined") {

    window.parent.frames['result'].document.getElementById('img'+sflag).className = "zhcx_show";
    window.parent.frames['result'].document.getElementById('acc'+sflag).style.display="none";

    window.parent.frames['result'].Load();
    } else {

    //window.parent.frames['result'].document.all['img'+sflag].src="../V5/images5/icon_plus.gif";
    //window.parent.frames['result'].Load();
    }
    }

    window.parent.document.forms[0].sflag.value="1";
    }
    }
    var txcode_N = '530550';
    if(txcode_N == 'N31001' ||txcode_N == 'N31003'||txcode_N == 'N31005'||txcode_N == 'N31007'){
    window.parent.document.getElementById("sub_status").style.display="none";
    window.parent.document.getElementById("result").style.display="block";
    }
    if(txcode_N == 'N31008'){
    alert('信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。\n');
    window.parent.document.getElementById("sub_status").style.display="none";
    return;
    }
    }

    if(typeof window.parent.btnReturn!="undefined"){
    error.style.display="none";
    }
    }
    }
    catch(ee){
    //alert("ee");
    }
    }
    //end of function Load()

    function popparent(){
    parent.parent.m1('m1_0006');
    parent.parent.m2('m1_0006','m2_0186');
    top.submitTX('431901','');
    }
    function toChangeCert(guideUrl){
    var newwin=window.open(guideUrl,"","top=0,left=0,height=768,width=1024,scrollbars=yes,status=no,resizable=yes");
    }


    var autheURL = '';
    function turnAuthePage(){
    window.location=autheURL;
    }

</script>
        </head>
<body onload="Load();resizeParentsIfrm();" onkeydown="return false;KeyDown(event);">
<div id="ccbPage" class="clearfix">




    <script>
        var isCurrIE6=(navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1);
        if("310902".indexOf("530550")!=-1) {
        if(isCurrIE6)
        document.write('<table width="100%"><tr><td><div id="error_main" class="border_box mb_0" style="margin-top:5px;" align="center">');
        else document.write('<div id="error_main" class="border_box mb_0" style="margin-top:5px;padding-right:1px;padding-top:1px;" align="center">');
        }else{
        var error_main=null;
        try{
        error_main=parent.document.getElementById('errorpageflag105');
        }
        catch(E){}
        if(error_main != null && typeof error_main != undefined){
        if(isCurrIE6)
        document.write('<table width="100%"><tr><td><div id="error_main" class="border_box mb_0" style="margin-top:30px;" align="center">');
        else document.write('<div id="error_main" class="border_box mb_0" style="margin-top:30px;padding-right:1px;padding-top:1px;" align="center">');
        }else {
        if(isCurrIE6)
        document.write('<table width="100%"><tr><td><div id="error_main" class="border_box mb_0" style="margin-top:15px;" align="center">');
        else document.write('<div id="error_main" class="border_box mb_0" style="margin-top:15px;padding-right:1px;padding-top:1px;" align="center">');
        }
        }

    </script>
    <SCRIPT LANGUAGE='JavaScript'> errcode='0130Z110C100'</SCRIPT>

    <div style="display:none">
        <div class='text_error'></div>
    </div>
    <div class='failed_span' style="width:100%;position:relative;display:table;border:0px" align='left'>
        <div class='subwrap_fankui'><div class='content_fankui_message'>
            <div class='text_big text_bold' style='line-height:20px;margin:0 0 1px 125px;'>
                <p class="fs_16 fw_bold">
                <script language="javascript">
                    try{
                    if(parent.location.href.indexOf("TXCODE=310900")>0||parent.location.href.indexOf("TXCODE=310700")>0){
                    document.write("子账户更新失败:");
                    }else{
                    document.write("尊敬的客户：");
                    }
                    }catch(e){ }
                </script>
                <p/>
                未能处理您的请求。如有疑问，请咨询在线客服或致电95533。
                <script language="javascript">
                    if(window.location.href.indexOf("popError=1")!=-1){
                    popError=popError+"子账户更新失败:";
                    popError=popError+"0130Z110C100"+"\r\n";

                    popError=popError+"未能处理您的请求。如有疑问，请咨询在线客服或致电95533。";

                    alert(popError);
                    parent.disableYN();
                    }
                </script>

            </div>
            <div class='text_big' style="margin:2px 12px 0 125px;">
                <table width="100%">
                    <tr style="line-height:20px"><td style="line-height:20px">参考代码：0130Z110C100</td><td style="text-align:right;">
                        <script language="javascript">
                            var errorcode="0130Z110C100";
                            if(typeof errorcode!="undefined"&&typeof errorcode!=""&&errorcode.length>=4&&errorcode.substring(0,1)=="8") {
                            //alert(errorcode.substring(1,4));
                            switch(errorcode.substring(1,4))
                            {
                            case "110":
                            document.write("中国建设银行北京市分行");
                            break
                            case "120":
                            document.write("中国建设银行天津市分行");
                            break
                            case "130":
                            document.write("中国建设银行河北省分行");
                            break
                            case "140":
                            document.write("中国建设银行山西省分行");
                            break
                            case "150":
                            document.write("中国建设银行内蒙古区分行");
                            break
                            case "210":
                            document.write("中国建设银行辽宁省分行");
                            break
                            case "212":
                            document.write("中国建设银行大连市分行");
                            break
                            case "220":
                            document.write("中国建设银行吉林省分行");
                            break
                            case "230":
                            document.write("中国建设银行黑龙江省分行");
                            break
                            case "310":
                            document.write("中国建设银行上海市分行");
                            break
                            case "320":
                            document.write("中国建设银行江苏省分行");
                            break
                            case "322":
                            document.write("中国建设银行苏州市分行");
                            break
                            case "330":
                            document.write("中国建设银行浙江省分行");
                            break
                            case "331":
                            document.write("中国建设银行宁波市分行");
                            break
                            case "340":
                            document.write("中国建设银行安徽省分行");
                            break
                            case "350":
                            document.write("中国建设银行福建省分行");
                            break
                            case "351":
                            document.write("中国建设银行厦门市分行");
                            break
                            case "360":
                            document.write("中国建设银行江西省分行");
                            break
                            case "370":
                            document.write("中国建设银行山东省分行");
                            break
                            case "371":
                            document.write("中国建设银行青岛市分行");
                            break
                            case "410":
                            document.write("中国建设银行河南省分行");
                            break
                            case "420":
                            document.write("中国建设银行湖北省分行");
                            break
                            case "422":
                            document.write("中国建设银行三峡分行");
                            break
                            case "430":
                            document.write("中国建设银行湖南省分行");
                            break
                            case "440":
                            document.write("中国建设银行广东省分行");
                            break
                            case "442":
                            document.write("中国建设银行深圳市分行");
                            break
                            case "450":
                            document.write("中国建设银行广西区分行");
                            break
                            case "460":
                            document.write("中国建设银行海南省分行");
                            break
                            case "500":
                            document.write("中国建设银行重庆市分行");
                            break
                            case "510":
                            document.write("中国建设银行四川省分行");
                            break
                            case "520":
                            document.write("中国建设银行贵州省分行");
                            break
                            case "530":
                            document.write("中国建设银行云南省分行");
                            break
                            case "540":
                            document.write("中国建设银行西藏区分行");
                            break
                            case "610":
                            document.write("中国建设银行陕西省分行");
                            break
                            case "620":
                            document.write("中国建设银行甘肃省分行");
                            break
                            case "630":
                            document.write("中国建设银行青海省分行");
                            break
                            case "640":
                            document.write("中国建设银行宁夏区分行");
                            break
                            case "650":
                            document.write("中国建设银行新疆区分行");
                            break
                            default:
                            document.write("中国建设银行");
                            }
                            }else{
                            document.write("中国建设银行");
                            }
                        </script>
                    </td></tr>
                </table>
            </div>
        </div>
        </div>
    </div>

</div>
<script language="javascript">
    if(isCurrIE6){
    document.write("</td><td style='width:2px'></td></tr></table>");
        }
        </script>




<div id="errIncludePage">

</div>
<script language="javascript">
var isPopLayer = false;
parent.$("iframe").each(function(){
if(this.contentWindow.document===document.body.ownerDocument) {
if($(this).attr("id").indexOf("layui-layer-iframe") != -1){
isPopLayer = true;
}
}
});
if(isPopLayer){
$('#errIncludePage').hide();
}

</script>

        <!-- step end -->


<div class="pbd_table_form_btns_div">
<script language="javascript">
    var blnIsQueryDeatil="0";
    var vipTxcode = "530550";
    //新vip对账单，隐藏 返回按钮
    if(vipTxcode == '370014'){
    //window.parent.document.getElementById("wait").style.display="none";
    //blnIsQueryDeatil="4";
    window.parent.document.getElementById("SHOW_VIP_INTEGRATE_BILL").style.width=0+"px";
    window.parent.document.getElementById("SHOW_VIP_INTEGRATE_BILL").style.height=0+"px";
    var tmpstr='信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。';
    var str = "<div class='failed_span' align='left'  style='width:100%;position:relative;display:table;border:0px;'><div class='subwrap_fankui'><div class='content_fankui_message'><div style='text-align:left;padding-left:98px;line-height:22px;'>"+tmpstr+"</div></div></div></div>";
    window.parent.openDialog3(str);
    //alert(tmpstr);
    }
    if(vipTxcode == '370015'){
    window.parent.document.getElementById("wait").style.display="none";
    window.parent.changeBtn("2");//恢复按钮
    }
    //高端版没有社保账号查询社保基本信息，隐藏 返回按钮
    if(vipTxcode == 'SSI921' || vipTxcode == '370000' || vipTxcode == '470015' || vipTxcode == '470014'){

    }

    if (vipTxcode == "SSI931" || vipTxcode == "SSI962" || vipTxcode == "SSI943" || vipTxcode == "SSI973" || vipTxcode == "SSI951" || vipTxcode == "SSI981") {

    blnIsQueryDeatil="4";
    }
    if (vipTxcode == "522301" ||vipTxcode =='522100'||vipTxcode =='52Y100'||vipTxcode =='52Y301') {

    blnIsQueryDeatil="4";
    }

    if("430200".indexOf(vipTxcode)!=-1||"431017".indexOf(vipTxcode)!=-1){

    blnIsQueryDeatil="4";
    }

    if (vipTxcode == "500520" || vipTxcode == "500525" || vipTxcode == "500531"|| vipTxcode == "NJ1106"|| vipTxcode == "700002"||vipTxcode=="522120" ) {

    blnIsQueryDeatil="4";
    }

    function deleteCookie (name) {
    var exp = new Date();
    exp.setTime (exp.getTime() - 1);
    var cval = getCookie (name);
    window.document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString()+";path=/";
    }

    function getCookie (name) {
    var arg = name + "=";
    var alen = arg.length;
    var clen = window.document.cookie.length;
    var i = 0;
    while (i < clen) {
    var j = i + alen;
    if (window.document.cookie.substring(i, j) == arg)
    return getCookieVal (j);
    i = window.document.cookie.indexOf(" ", i) + 1;
    if (i == 0) break;
    }
    return null;
    }
    function getCookieVal (offset) {
    var endstr = window.document.cookie.indexOf (";", offset);
    if (endstr == -1) endstr = window.document.cookie.length;
    return unescape(window.document.cookie.substring(offset, endstr));
    }

    var xinbanFlag = "0";
    if(vipTxcode=="N00001" && xinbanFlag == "1" ){
    deleteCookie("p1Cookie");
    blnIsQueryDeatil ="6";
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick=\"javascript:window.location.href='https://ibsbjstar.ccb.com.cn/app/V5/CN/STY1/login.jsp';\">&nbsp;");
    }


    if(vipTxcode=="530200"){
    blnIsQueryDeatil ="6";
    //套餐新增错误返回 或 高端版年费缴付
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick=\"javascript:window.history.go(-2);\">&nbsp;");
    }
    //账户明细异步查询下载报错时处理：
    if(vipTxcode == "310206"){
    var tmpstr='信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。'+'【】';
    alert(tmpstr);
    }
    if(vipTxcode == "310207"){
    window.parent.document.getElementById('mask').style.display="none";
    window.parent.document.getElementById('asynProcess1').style.display="none";
    var tmpstr='信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。'+'【】';
    alert(tmpstr);
    window.parent.document.getElementById("qd1").disabled = false;
    window.parent.document.getElementById("qd1").className="btn";
    }
    if(vipTxcode == '540309'){
    //window.parent.document.getElementById("wait").style.display="none";
    //blnIsQueryDeatil="4";
    window.parent.document.getElementById("show").style.width=0+"px";
    window.parent.document.getElementById("show").style.height=0+"px";
    var tmpstr='信息提示：0130Z110C100\n未能处理您的请求。如有疑问，请咨询在线客服或致电95533。';
    var str = "<div style='text-align:left;'><div style='height:60px;padding:5px 20px 0px 30px;float:left;text-align:right; line-height:60px;'><img src='/P1StaRes/V6/STY1/CN/images5/i3.jpg'></div><div style='text-align:left;padding-top:23px;line-height:22px; height:60px;'>"+tmpstr+"</div></div>";
    window.parent.openDialog1(str);
    //alert(tmpstr);
    }


    if("520513".indexOf(vipTxcode)>-1
    ||(vipTxcode=="100101" && getCookie("CCBParam")!=null && getCookie("CCBParam")!=undefined &&(getCookie("CCBParam").indexOf("TXTYPE=EPAY")>-1 || getCookie("CCBParam").indexOf("TXTYPE%3DEPAY")>-1))){
    blnIsQueryDeatil ="6";
    document.writeln("<input name='Submit' type='button' class='btn' value='关闭' onclick=\"parent.window.close()\">&nbsp;");
    }


    if (vipTxcode == "418211") {
    blnIsQueryDeatil ="6";
    document.writeln("<input name='Submit' type='button' class='btn' value='关闭' onclick=\"parent.window.close()\">&nbsp;");
    }

    try{

    if(typeof parent!="undefined" && typeof parent!="") {
    if(typeof parent.document.forms[0]!="undefined" && typeof parent.document.forms[0]!="") {
    if(typeof parent.document.forms[0]['qd1']!="undefined") {
    if(typeof window.parent.document.forms[0].QUERY_ACC_DETAIL_FLAG!="undefined"&&typeof window.parent.document.forms[0].QUERY_ACC_DETAIL_FLAG!="") {
    blnIsQueryDeatil="1";
    if(window.parent.document.forms[0].QUERY_ACC_DETAIL_FLAG.value="2D0114"){
    window.parent.document.getElementById("wait").style.display = "none";
    }
    }
    if(typeof window.parent.document.forms[0].QUERY_LOG_DETAIL_FLAG!="undefined"&&typeof window.parent.document.forms[0].QUERY_LOG_DETAIL_FLAG!="") {
    blnIsQueryDeatil="2";
    }
    }
    }
    }

    if (vipTxcode == "SF0101" || vipTxcode == "SF0601" || vipTxcode == "522100" || vipTxcode == "522200" || vipTxcode == "470065" || vipTxcode == "470021" || vipTxcode == "470014" || vipTxcode == "470015" || vipTxcode == "470072" || vipTxcode == "470086" || vipTxcode == "470087" || vipTxcode == "470088" || vipTxcode == "470089" || vipTxcode == "470078" || vipTxcode == "410520" || vipTxcode == "410510" || vipTxcode == "430632"|| vipTxcode == "430454"|| vipTxcode == "430452"|| vipTxcode == "430451"|| vipTxcode == "430636"|| vipTxcode == "434402"|| vipTxcode == "434403"|| vipTxcode == "434404"|| vipTxcode == "434405") {

    blnIsQueryDeatil="4";
    }
    if(vipTxcode == "471101" || vipTxcode == "471105" || vipTxcode == "471205" || vipTxcode == "471200" || vipTxcode == "471201" || vipTxcode == "471202" || vipTxcode == "471203" || vipTxcode == "471204" || vipTxcode == "471309" || vipTxcode == "471300" || vipTxcode == "471302" || vipTxcode == "471303" || vipTxcode == "471304" || vipTxcode == "471305" || vipTxcode == "471301" || vipTxcode == "471400" || vipTxcode == "471410" || vipTxcode == "471413" || vipTxcode == "471511" || vipTxcode == "471512" || vipTxcode == "471710" || vipTxcode == "471711" || vipTxcode == "471712" || vipTxcode == "471810" || vipTxcode == "471811" || vipTxcode == "471812" || vipTxcode == "471910" || vipTxcode == "471911" || vipTxcode == "471000" || vipTxcode == "471600" || vipTxcode == "471700" || vipTxcode == "471900" || vipTxcode == "472000"){

    blnIsQueryDeatil="4";
    }
    if (vipTxcode == "310902") {

    blnIsQueryDeatil="4";
    if("0130Z110TH5H"=='0130Z110C100'){
    window.parent.document.getElementById("area_title").style.display="none";
    window.parent.document.getElementById("abc").style.display="none";
    window.parent.document.getElementById("content_title").style.display="none";
    window.parent.document.getElementById("table_step_2").style.display="none";
    }
    }
    if (vipTxcode == "522102") {

    if("0130Z1108JY1"=='0130Z110C100'){
    blnIsQueryDeatil="4";
    }
    }
    if("E30101,E30310,E30801,E31025,N20000".indexOf(vipTxcode)!=-1){

    blnIsQueryDeatil="4";
    }

    if("450603".indexOf(vipTxcode)!=-1){

    blnIsQueryDeatil="4";
    }
    if("497067,497068,497069".indexOf(vipTxcode)!=-1){

    blnIsQueryDeatil="4";
    }
    //个贷额度查询框架页需要隐藏返回和关闭按钮。更改blnIsQueryDeatil=4。
    if((typeof (window.parent.document.getElementById("GDSTATEMENT")!= "undefined"))) {
    var obj=window.parent.document.getElementById("GDSTATEMENT").value;
    if(obj=="D60701"){
    if(document.location.href.indexOf('TXCODE=D60702') != -1)
    blnIsQueryDeatil = '5';
    else
    blnIsQueryDeatil="4";
    }
    }
    var historyLen=history.length;

    if (blnIsQueryDeatil=="0") {
    if(historyLen>0) {
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick=\"Back(-1)\">&nbsp;");
    } else {
    document.writeln("<input name='Submit' type='button' class='btn' value='关闭' onclick=\"parent.window.close()\">&nbsp;");
    }
    }
    if (blnIsQueryDeatil=="1") {

    window.parent.disableYNParent("qd1","1");
    hiddenShow("buttons","0");
    }
    if (blnIsQueryDeatil=="2") {

    window.parent.disableYNParent("qd1","1");
    }
    if (blnIsQueryDeatil=="4") {
    document.writeln("&nbsp;");
    }
    if (blnIsQueryDeatil=="5") {
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick=\"javascript:parent.window.history.go(-2);\">&nbsp;");
    }
    }catch(eee){
    //add by donggy 高端版屏闭支票通的返回关闭按钮
    var b = false;

    var hisLen=history.length;
    if (blnIsQueryDeatil=="0") {
    //modified by longt 证券卡余额查询两种入口错误返回页面处理 20110113
    if('530550' == '430207'){
    if(navigator.userAgent.indexOf('MSIE') != -1){
    //IE下弹出窗口查询证券卡余额,点击返回关闭窗口
    if(hisLen == 0){
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick='window.close()'>&nbsp;");
    }
    //IE下跳转页面查询证券卡余额,点击"返回"返回
    else{
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick='Back(-1)'>&nbsp;");
    }
    }
    else{
    //FireFox下弹出窗口查询证券卡余额,点击返回关闭窗口
    if(hisLen == 1){
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick='window.close()'>&nbsp;");
    }
    //FireFox下跳转页面查询证券卡余额,点击"返回"返回
    else{
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick='Back(-1)'>&nbsp;");
    }
    }
    }else {

    if(hisLen>0) {
    if('530550' == '350201' && b){
    }else if('530550' == '310900'||'530550' == '310700'||'530550' == 'E30604'||'530550' == 'E30605'||'530550' == 'E30103'||'530550' == 'E30202'){
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick='parent.window.close()'>&nbsp;");
    }else if("520511,520513,520514,E35002,E35003,520520,118200,118211,118300".indexOf("530550")!=-1 && autheURL!=''){
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick=\"javascript:turnAuthePage();\"/>&nbsp;");
    }else{
    var isLayerFrm = false;

    //if("520186,520187,410328,410321,410329".indexOf('530550')==-1){ //银保，理财网关支持返回按钮 20160311 caoxc
    try{ //为防止支付网关 js报错导致无法显示返回按钮，增加null判断并  2016-04-25
    if(parent.$("iframe")){
    parent.$("iframe").each(function(){
    if(this.contentWindow.document===document.body.ownerDocument) {
    if($(this).attr("id").indexOf("layui-layer-iframe") != -1){
    isLayerFrm = true;
    }
    }
    });
    }
    }catch(e){}
    //}
    if(isLayerFrm){
    document.writeln("&nbsp;");
    }else{
    document.writeln("<input name='Submit' type='button' class='btn' value='返回' onclick=\"Back(-1)\">&nbsp;");
    }
    }
    } else {
    if('530550' == '350201' && b){
    }else{
    document.writeln("<input name='Submit' type='button' class='btn' value='关闭' onclick=\"parent.window.close()\">&nbsp;");
    }
    }
    }
    }
    if (blnIsQueryDeatil=="4") {
    document.writeln("&nbsp;");
    }
    }
</script>
</div>
        </div>
<table id="result">
<tr id="err">
    <td></td>
</tr>
</table>
        </div>
<script src="/P1StaRes/V6/STY1/CN/js/udc_footer.js" language="javascript"></script>
        </body>
<script language="javascript">

var url=document.location.href;
if((url.indexOf("TXCODE=470013")>0||url.indexOf("TXCODE=470004")>0||url.indexOf("TXCODE=470020")>0)&&errcode=="C041"){
//window.parent.document.body.innerHTML=document.body.innerHTML;
}

if(''=='M1'||'530550' == '100501'){
var allinput=window.document.getElementsByTagName('input');
for(var i=0;i<allinput.length;i++)
{
if(allinput[i].className=='btn')
allinput[i].className='button';
else if(allinput[i].className=='btn_dis')
allinputdis[i].className='button_dis';
}
}

</script>
        </html>






<script language="javascript">


var UDC_SRVASSIST_TXDAT={"PAGEID":"PAGE:CCVEP:530550_龙支付", "TXCODE":"530550", "PAGE_TITLE":"龙支付", "PAGE_VISIT_TIME":"20250621213813727", "ERR_CODE":"0130Z110C100", "ERR_MSG":"未能处理您的请求。如有疑问，请咨询在线客服或致电95533。"};
var DAT_TXENV={"USERID":"", "BRANCHID":"520000000", "TXCODE":"530550", "DEVID":""};</script>


<script>
function getParmJiaMiUrl(url){

return url;
}
function getParmJiaMiUrl_encode(url){

return url;
}
function SM4EncryptUrl(url){
try{

}catch(e){}
return url;
}
</script>



<script>
$(function(){
try {
if(typeof top.DAT_USERBASE === 'undefined') return;


} catch (error) {

}
})
</script>










<script src="/P1StaRes/V6/STY1/CN/js/SPZ/bundle.js?ver=20250621"></script>


<link type="text/css" rel="stylesheet" href="/P1StaRes/V6/STY1/CN/css/SPZ/keyboard.css?ver=20250621" />

<script src="/P1StaRes/V6/STY1/CN/js/SPZ/keyboard.js?ver=20250621"></script>

<script>
$(function(){
try {
top.window.fzDomainUrl = 'https://rccis.ccb.com';
top.window.fzDomainVer = '20250621';
} catch (error) {}
})
</script>