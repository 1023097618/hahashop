const Mock=require('mockjs')
const baseurl = process.env.VUE_APP_baseurl



Mock.mock(baseurl+"/good/list?pageNum=1&pageSize=8", 'get', require('./json/shop/goodlist.json'));
Mock.mock(baseurl+"/good/list", 'get', require('./json/shop/goodlist.json'));
Mock.mock(baseurl+"/order/buy", 'post', require('./json/shop/buyers.json'));
Mock.mock(baseurl+"/good/detail?id=10086", 'get', require('./json/shop/gooddes.json'));
Mock.mock(baseurl+"/auth/login", 'post', require('./json/login/login.json'));
Mock.mock(baseurl+"/auth/info?token=123", 'get', require('./json/login/info.json'));
Mock.mock(baseurl+"/order/sellerlist?goodId=10086&currentPage=1&pageSize=8", 'get', require('./json/shop/orders1.json'));
Mock.mock(baseurl+"/order/sellerlist?goodId=10087&currentPage=1&pageSize=8", 'get', require('./json/shop/orders2.json'));
Mock.mock(baseurl+"/history/list?pageNum=1&pageSize=8", 'get', require('./json/shop/histories.json'));
Mock.mock(baseurl+"/auth/changePassword", 'post', require('./json/success.json'));
// Mock.mock(baseurl+"/auth/register", 'post', require('./json/success.json'));
Mock.mock(baseurl+"/order/buyerlist?token=123&pageNum=1&pageSize=8", 'get', require('./json/shop/buyerorders.json'));
Mock.mock(baseurl+"/good/category", 'get', require('./json/shop/category.json'));
Mock.mock(baseurl+"/storage/upload", 'post', require('./json/storage/upload.json'));
Mock.mock(baseurl+"/user/list?pageNum=1&pageSize=8", 'get', require('./json/users/getusers.json'));
Mock.mock(baseurl+"/user/orderlist?userId=0&pageNum=1&pageSize=8", 'get', require('./json/users/getuserorders.json'));