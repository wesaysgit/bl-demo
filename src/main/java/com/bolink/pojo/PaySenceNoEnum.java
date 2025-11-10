package com.bolink.pojo;

/**枚举类
 * 支付场景
 */
public enum PaySenceNoEnum {
    WXPRESCAN(0),//微信预付主扫
    WXDRESCAN(1),//微信直付主扫
    WXSWEEPCODE(2), //微信被扫
    WXNOSENCE(3), //微信无感
    ALIPRESCAN(4),//阿里预付主扫
    ALIDRESCAN(5),//阿里直付主扫
    ALISWEEPCODE(6),//阿里被扫
    ALINOSENCE(7),//阿里无感
    JHNOSENCE(9),//建行无感支付
    UNIONNOSENCE(10),//银联无感
    MSNOSENCE(11),//民生无感
    WXMOTHCARD(12),//微信公众号月卡续费
    UNIFIEDWXSCAN(13),//通用支付主扫 --WX
    UNIFIEDALISCAN(14),//通用支付主扫 --ALI
    UNIFIEDWXSWEEPCODE(15),//通用支付被扫 --WX
    UNIFIEDALISWEEPCODE(16),//通用支付被扫 --ALI
    UNIFIEDWXPUB(17),//通用支付微信公众号
    JIDOUNOSENCE(18),//极豆无感
    ICBCNOSENCE(19),//工行无感
    CMBNOSENCE(20),//招行无感
    CMBSCANPAY(21),//招行主扫
    YUNQIPAY(22),//云栖支付
    CSDNNOSENCE(23),//招行城市大脑无感
    UNIONPRESCAN(24),//云闪付预付
    UNIONDRESCAN(25),//云闪付直付
    UNIONSWEEPCODE(26),//云闪付被扫
    UNIONMSNOSENCE(27),//银联民生部无感
    CCBSCAN(28),//建行app扫码
    ABCAUTOPAY(29),//农行无感
    LINYIPAY(30),//临沂代扣
    XIAOSHANAUTOPAY(31),//萧山无感
    MEIHANGAUTOPAY(32),//沈阳美行无感
    UNIONAPPPAY(33),//银联app主动缴费
    FENMIAOPAY(34),//分秒无感
    ZHONGMENGNOSENCE(35),//中萌无感
    ICBCV1NOSENCE(36),//工商银行V1无感
    ETC(37),//ETC
    GUIZHOUNOSENCE(38),//贵州无感
    WXV4AUTOPAY(39),//微信4.0无感。只是个支付场景号，实际没配置
    BOCJRNOSENCE(40),//中行金融部(苏州)无感
    CJNSNOSENCE(41),//从江农商
    XUZHOUZHIHUINOSENCE(42),//徐州无感
    YICHANGNOSENCE(43),//宜昌无感
    MOBONOSENCE(44),//墨博无感
    GSNXNOSENCE(45),//甘肃农信
    JTZHTCNOSENCE(47),//交行-智慧停车
    GZYRNOSENCE(46),//贵州盈融
    CIXINOSENCE(48),//慈溪上报
    UNIFIEDUNIONSCAN(50),//通用支付主扫 --UNION
    UNIFIEDUNIONSWEEPCODE(51),//通用支付被扫 --UNION
    ENSHINOSENCE(52),//恩施上报
    KUNSHANZHNOSENCE(54),//昆山智慧上报
    SHIJIAZHUANGJTZHNOSENCE(55),//石家庄交通智慧上报
    SCRCUNOSENCE(56),//四川农信
    WUXINOSENCE(57),//无锡上报
    XINWUNOSENCE(58),//新吴区上报
    WUZHONGNOSENCE(59),//吴中通停通付
    HUISHANNOSENCE(60),//惠山无感支付
    LIANGXINOSENCE(61),//无锡梁溪无感
    WXXSQ(63),//无锡锡山区
    ZHIBONANTONGNOSENCE(64),//智泊南通无感
    WUHANNOSENCE(65),//武汉无感上报
    ZHENJIANGNOSENCE(66),//镇江会员无感

    ;
    public int senceNo;

    PaySenceNoEnum(int senceNo){
        this.senceNo=senceNo;
    }

}
