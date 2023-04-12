package concurrent.mapper;

import concurrent.model.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * ProductMapper
 * 2023/4/11 8:16 下午
 *
 * @author wuao
 */
public interface ProductMapper {
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getRemain(@Param("id") int id);

    @Update("UPDATE product SET remain = #{product.remain}")
    void updateRemain(@Param("product") Product product);
}
