package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.dao.LogMapper;
import com.along.entity.Log;
import com.along.entity.LogExample;

@Component
public class LogServiceImpl extends CrudService<LogMapper, Log, LogExample>{

}
