// @/utils/common.js

// 定义订单状态常量
export const ORDER_STATES = {
    CUSTOMER_ORDER: 0, // 客户下单
    SELLER_CANCELLED: 1, // 订单被卖家取消
    ORDER_COMPLETED: 2, // 订单完成
    BUYER_CANCELLED: 3, // 订单被买家取消
    SELLER_CONFIRMED: 4, // 商家确认
    STOCK_READY: 5, // 备货完成
    SHIPPING_STARTED: 6, // 开始发货
  };
  
  // 定义状态文本映射
  const stateTextMap = {
    [ORDER_STATES.CUSTOMER_ORDER]: '客户下单',
    [ORDER_STATES.SELLER_CANCELLED]: '订单被卖家取消',
    [ORDER_STATES.ORDER_COMPLETED]: '订单完成',
    [ORDER_STATES.BUYER_CANCELLED]: '订单被买家取消',
    [ORDER_STATES.SELLER_CONFIRMED]: '商家确认',
    [ORDER_STATES.STOCK_READY]: '备货完成',
    [ORDER_STATES.SHIPPING_STARTED]: '开始发货',
  };
  
  // 定义获取状态文本的方法
  export function getOrderStateText(orderState) {
    return stateTextMap[orderState] || '未知状态';
  }
  