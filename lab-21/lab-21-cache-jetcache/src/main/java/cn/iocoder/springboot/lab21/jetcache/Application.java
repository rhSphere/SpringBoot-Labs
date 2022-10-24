package cn.iocoder.springboot.lab21.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.iocoder.springboot.lab21.jetcache.mapper")
@EnableMethodCache(basePackages = "cn.iocoder.springboot")
@EnableCreateCacheAnnotation
public class Application {
}
