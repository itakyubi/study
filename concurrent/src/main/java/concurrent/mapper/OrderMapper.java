package concurrent.mapper;

import concurrent.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * OrderMapper
 * 2023/4/11 8:16 下午
 *
 * @author wuao
 */
public interface OrderMapper {

    @Insert("INSERT INTO `order` (`user_name`, `product_name`) VALUES (#{order.userName}, #{order.productName})")
    void createOrder(@Param("order") Order order);
}
