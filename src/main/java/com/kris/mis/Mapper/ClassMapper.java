package com.kris.mis.Mapper;

import com.kris.mis.Entity.Classinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ClassMapper {
    @Select("select * from class_info")
    List<Classinfo>findAllClassinfo();
}
