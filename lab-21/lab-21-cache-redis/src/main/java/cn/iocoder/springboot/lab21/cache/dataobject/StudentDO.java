package cn.iocoder.springboot.lab21.cache.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ludepeng
 * @date 2022-10-21 22:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDO {

    private int stuNo;

    private String stuName;
}
