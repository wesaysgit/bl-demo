const CryptoJS = require("crypto-js");

//普通加签
function createSign1(bizParams, ukey){
    let signFix = "key=";
    let sortParams = {};
    let paramKeys = Object.keys(bizParams).sort();
    paramKeys.forEach(function(item){
        sortParams[item] = bizParams[item];
    });
    let signParams = JSON.stringify(sortParams);
    signParams += signFix + ukey;
    return CryptoJS.MD5(signParams).toString().toUpperCase();
}

//小程序加签（channel=applets）
function createSign2(bizParams, ukey) {
    let signFix = "key=";
    let paramKeys = Object.keys(bizParams).sort();
    let kvPairs = paramKeys.map(k => `${k}=${bizParams[k]}`);
    let signParams = kvPairs.join("&");
    signParams += `&${signFix}${ukey}`;
    return CryptoJS.MD5(signParams).toString().toUpperCase();
}

// 统一方法，根据 type 调用不同加签方式
function createSign(bizParams, ukey, type = 2) {
    if(type === 1){
        return createSign1(bizParams, ukey);
    } else if(type === 2){
        return createSign2(bizParams, ukey);
    } else {
        throw new Error("未知签名类型 type=" + type);
    }
}

// ===== 示例参数 =====
let data = {
    title: "停车费用支付",
    park_id: "230776",
    amount: "6.00",
    car_number: "贵AG7X65",
    pay_type: 0,
    trade_no: "1755834632500826503567",
    channel: "applets",
    time_temp: 1755823939,
    wx_app_id: "wx0870f79235e513f7"
};

let uKey = "B4279FD34C634941";

// 调用方式
let signType1 = createSign(data, uKey, 1);
let signType2 = createSign(data, uKey, 2);

console.log("签名 type1 = " + signType1);
console.log("签名 type2 = " + signType2);
