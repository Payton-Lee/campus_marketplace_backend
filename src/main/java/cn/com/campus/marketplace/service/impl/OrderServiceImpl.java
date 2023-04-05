package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.*;
import cn.com.campus.marketplace.mapper.OrderItemMapper;
import cn.com.campus.marketplace.mapper.OrderMapper;
import cn.com.campus.marketplace.service.*;
import cn.com.campus.marketplace.utils.CodeGenerateUtils;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private SalesDataService salesDataService;


    @Autowired
    protected void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Page<Order> pageOrderList(Integer current, Integer size) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        Page<Order> page = new Page<>(current, size);
        return orderMapper.findOrder(page, wrapper);
    }

    @Override
    public Boolean createNewOrder(Goods goods, Integer consigneeId, Integer count, Integer userId) {
        OrderItem orderItem = new OrderItem();
        Order order = new Order();
        String orderNumber = CodeGenerateUtils.generateOrderSn(userId + count + 1);
        order.setOrderNumber(orderNumber);
        order.setUserId(userId);
        order.setConsigneeId(consigneeId);
        order.setOrderPrice(goods.getPrice() * count);
        order.setPayStatus(0);
        order.setIsSend(0);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        if (save(order)) {
            orderItem.setOrderId(order.getId());
            orderItem.setCount(count);
            orderItem.setGoodsId(goods.getId());
            return orderItemService.save(orderItem);
        }
        return false;
    }

    @Override
    public List<Order> getOrderListByUserId(Integer userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return list(wrapper);
    }

    @Override
    public Boolean commitOrderBatch(List<Cart> cartList, Integer userId, Integer consigneeId) {
        Order order = new Order();
        order.setOrderNumber(CodeGenerateUtils.generateOrderSn(userId + cartList.size()));
        order.setUserId(userId);
        order.setConsigneeId(consigneeId);
        List<OrderItem> orderItemList = new ArrayList<>();
        double price = 0.0;
        for (Cart cart : cartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsId(cart.getGoodsId());
            orderItem.setCount(cart.getCount());
            Goods goods = goodsService.getById(cart.getGoodsId());
            price += goods.getPrice() * cart.getCount();
            orderItemList.add(orderItem);
        }
        order.setOrderPrice(price);
        order.setPayStatus(0);
        order.setIsSend(0);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        if (save(order)) {
            List<Integer> goodsIdList = orderItemList.stream().map(OrderItem::getGoodsId).collect(Collectors.toList());
            List<Goods> goodsList = goodsService.getGoodsListByIds(goodsIdList);
            List<OrderItem> finalOrderItemList = orderItemList;
            goodsList = goodsList.stream().peek(info -> {
                Integer count = Objects.requireNonNull(finalOrderItemList.stream().filter(orderItem -> info.getId().equals(orderItem.getGoodsId())).findAny().orElse(null)).getCount();
                Integer inStock = info.getInStock() - count;
                info.setInStock(inStock);
                SalesData salesData = new SalesData();
                salesData.setGoodsId(info.getId());
                salesData.setCount(count);
                QueryWrapper<SalesData> salesDataQueryWrapper = new QueryWrapper<>();
                salesDataQueryWrapper.eq("goods_id", info.getId());
                SalesData salesDataInDB = salesDataService.getOne(salesDataQueryWrapper);
                if (Objects.isNull(salesDataInDB)) {
                    salesDataService.save(salesData);
                } else {
                    salesDataInDB.setCount(salesData.getCount() + salesDataInDB.getCount());
                   salesDataService.updateById(salesDataInDB);
                }
            }).collect(Collectors.toList());
            goodsService.updateBatchById(goodsList);
            orderItemList = orderItemList.stream().peek(info -> info.setOrderId(order.getId())).collect(Collectors.toList());
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            cartService.remove(queryWrapper);
            return orderItemService.saveBatch(orderItemList);
        }
        return false;
    }
}
