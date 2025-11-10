// H5支付函数
function wxH5Pay(payParams) {
    return new Promise((resolve, reject) => {
        // 检查是否在微信浏览器中
        const isWechat = /micromessenger/i.test(navigator.userAgent);
        if (!isWechat) {
            reject(new Error('请在微信中打开'));
            return;
        }

        // 调用微信H5支付
        if (typeof WeixinJSBridge !== 'undefined') {
            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                appId: payParams.appId,
                timeStamp: payParams.timeStamp,
                nonceStr: payParams.nonceStr,
                package: payParams.package,
                signType: payParams.signType,
                paySign: payParams.paySign
            }, function(res) {
                if (res.err_msg === 'get_brand_wcpay_request:ok') {
                    resolve({
                        success: true,
                        message: '支付成功'
                    });
                } else if (res.err_msg === 'get_brand_wcpay_request:cancel') {
                    reject({
                        success: false,
                        message: '用户取消支付'
                    });
                } else {
                    reject({
                        success: false,
                        message: '支付失败'
                    });
                }
            });
        } else {
            reject(new Error('微信支付接口未加载'));
        }
    });
}

// 使用示例
function handleH5Payment() {
    const payParams = {
        "timeStamp": "1755848427",
        "package": "prepay_id=wx221540270195009abcd45e4f52dc9a0001",
        "paySign": "vgOQkkJcFMWprep0i/GJmZCmiQ7oogRtUlgaLlrbi9vOTdgJ5C9tN3bSVj8lmumnYdo/lozLqNz9BFZrPNxzJV8UyHziQ9IQHWR3nS0M/fgdKWgeW002B3+xpPF1l01nEjg/kEYuJGtoOGl0jwTWTSR+SWZU6DuXHAsYK0uAcC+4rm7aLThsh8NJhnNO1M+8fmHyVz6cDH5it1ro7qxKOy13bKd9nLHsIFAjhAztt8Uw/TwHOq5eZmFRJPb48Z6ZenZZJ5TCyBaHtWnroz6Ew2KflmJEDjlbytJZ2+Ko/zz160Mfjfdm8MMhxaVcDxdePTjU8XLpoN9pGbGUkuzGpg==",
        "appId": "wx0870f79235e513f7",
        "signType": "RSA",
        "nonceStr": "8a638685950b40b2af9ea845834b63e4"
    };

    wxH5Pay(payParams)
        .then(result => {
            alert('支付成功！');
            // 跳转到成功页面
            window.location.href = '/payment-success.html';
        })
        .catch(error => {
            alert(error.message);
        });
}