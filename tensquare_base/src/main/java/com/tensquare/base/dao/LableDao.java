package com.tensquare.base.dao;

/**
 * @author hanshuiqiu
 * @create 2020-09-23 19:50
 *
 * 标签数据访问接口
 *
 * JpaRepository: 提供了基本的增删改查
 * JpaSpecificationExecutor: 用于做复杂的条件查询，如分页查询
 */

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LableDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {


}
