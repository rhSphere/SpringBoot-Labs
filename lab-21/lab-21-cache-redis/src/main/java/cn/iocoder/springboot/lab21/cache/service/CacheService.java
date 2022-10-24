package cn.iocoder.springboot.lab21.cache.service;

import cn.iocoder.springboot.lab21.cache.dataobject.StudentDO;
import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ludepeng
 * @date 2022-10-21 22:14
 */
@Service
@CacheConfig(cacheManager = "redisCacheManager")
public class CacheService {

    /**
     * key：缓存key名称，支持SPEL
     * value：缓存名称
     * condition：满足条件可缓存才缓存结果，支持SPEL
     * unless：满足条件结果不缓存，支持SPEL
     *
     * @param stuNo
     * @return
     */
    @Cacheable(value = "student-cache", key = "#stuNo", condition = "#stuNo gt 0", unless = "#result eq null")
    public StudentDO getStudentByNo(int stuNo) {
        StudentDO student = new StudentDO(stuNo, "liuyalou");
        System.out.println("模拟从数据库中读取：" + student);
        return student;
    }

    /**
     * 不指定key，默认会用{@link org.springframework.cache.interceptor.SimpleKeyGenerator}
     * 如果方法无参数则返回空字符串，只有一个参数则返回参数值，两个参数则返回包含两参数的SimpleKey
     *
     * @param username
     * @param age
     * @return
     */
    @Cacheable(value = "user-cache", unless = "#result eq null")
    public UserDO getUserByUsernameAndAge(String username, int age) {
        UserDO userDo = new UserDO(username, age);
        System.out.println("模拟从数据库中读取：" + userDo);
        return userDo;
    }

    @Cacheable(value = "student-cache", key = "#stuNo + '_' +#stuName", unless = "#result?.stuName eq null")
    public Optional<StudentDO> getStudentByNoAndName(int stuNo, String stuName) {
        if (stuNo <= 0) {
            return Optional.empty();
        }

        StudentDO student = new StudentDO(stuNo, stuName);
        System.out.println("模拟从数据库中读取：" + student);

        return Optional.ofNullable(student);
    }

    @CacheEvict(value = "student-cache", key = "#stuNo")
    public void removeStudentByStudNo(int stuNo) {
        System.out.println("从数据库删除数据，key为" + stuNo + "的缓存将会被删");
    }

    @CachePut(value = "student-cache", key = "#student.stuNo", condition = "#result ne null")
    public StudentDO updateStudent(StudentDO student) {
        System.out.println("数据库进行了更新，检查缓存是否一致");
        return student;
    }
}
