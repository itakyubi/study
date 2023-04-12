package concurrent.service;

import concurrent.mapper.OrderMapper;
import concurrent.mapper.ProductMapper;
import concurrent.model.Order;
import concurrent.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ProductService
 * 2023/4/11 8:16 下午
 *
 * @author wuao
 */

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    private Lock lock = new ReentrantLock(true);

    @Transactional
    public void sell() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":抢到锁啦，进入方法");
            Product product = productMapper.getRemain(1);
            int remain = product.getRemain();
            System.out.println(Thread.currentThread().getName() + ":当前库存=" + remain);
            if (remain > 0) {
                Product newProduct = new Product(1, product.getName(), remain - 1);
                productMapper.updateRemain(newProduct);

                Order order = new Order();
                order.setUserName(Thread.currentThread().getName());
                order.setProductName(newProduct.getName());
                orderMapper.createOrder(order);
                System.out.println(Thread.currentThread().getName() + ":减库存，创建订单完成");
            } else {
                System.out.println(Thread.currentThread().getName() + ":没有库存了");
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + ":释放锁");
            lock.unlock();
        }
    }
}
