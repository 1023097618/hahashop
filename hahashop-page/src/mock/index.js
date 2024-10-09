const Mock=require('mockjs')
const baseurl = process.env.VUE_APP_baseurl



Mock.mock(baseurl+"/good/list?pageNum=1&pageSize=8", 'get', require('./json/shop/goodlist.json'));
Mock.mock(baseurl+"/good/list", 'get', require('./json/shop/goodlist.json'));
Mock.mock(baseurl+"/order/buy", 'post', require('./json/shop/buyers.json'));
Mock.mock(baseurl+"/good/detail?id=10086", 'get', require('./json/shop/gooddes.json'));
Mock.mock(baseurl+"/auth/login", 'post', require('./json/login/login.json'));
Mock.mock(baseurl+"/auth/info?token=string", 'get', require('./json/login/info.json'));
Mock.mock(baseurl+"/order/list?goodId=10086", 'get', require('./json/shop/orders1.json'));
Mock.mock(baseurl+"/order/list?goodId=10087", 'get', require('./json/shop/orders2.json'));