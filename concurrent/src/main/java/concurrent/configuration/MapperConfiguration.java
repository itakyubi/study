package concurrent.configuration;

import concurrent.mapper.OrderMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MapperConfiguration
 * 2023/4/11 8:15 下午
 *
 * @author wuao
 */

@Configuration
@MapperScan(basePackageClasses = OrderMapper.class)
public class MapperConfiguration {
}
