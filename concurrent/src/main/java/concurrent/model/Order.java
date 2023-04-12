package concurrent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order
 * 2023/4/11 8:40 下午
 *
 * @author wuao
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private String userName;
    private String productName;
}
