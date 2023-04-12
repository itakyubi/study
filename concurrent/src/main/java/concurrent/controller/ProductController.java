package concurrent.controller;

import concurrent.mapper.ProductMapper;
import concurrent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * ProductController
 * 2023/4/11 8:12 下午
 *
 * @author wuao
 */

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/sell")
    public void sell() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    productService.sell();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.countDown();
        }
    }

    @GetMapping("/test")
    public void test(){
        System.out.println(productMapper.getRemain(1).getRemain());
    }


}
