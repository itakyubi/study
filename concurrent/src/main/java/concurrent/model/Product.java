package concurrent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product
 * 2023/4/11 8:33 下午
 *
 * @author wuao
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private int remain;
}
