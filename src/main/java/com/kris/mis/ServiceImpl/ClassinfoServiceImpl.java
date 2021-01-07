package com.kris.mis.ServiceImpl;

import com.kris.mis.Entity.Classinfo;
import com.kris.mis.Mapper.ClassMapper;
import com.kris.mis.Service.ClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassinfoServiceImpl implements ClassinfoService {
    @Autowired
    private ClassMapper classMapper;
    @Override
    public List<Classinfo> findAllClassinfo() {
        return classMapper.findAllClassinfo();
    }
}
