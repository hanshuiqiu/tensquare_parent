package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanshuiqiu
 * @create 2020-09-24 14:18
 *
 * 标签业务逻辑类
 */
@Service
public class LabelService {
    @Autowired
    private LableDao lableDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return this.lableDao.findAll();
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return this.lableDao.findById(id).get();
    }

    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        label.setId(idWorker.nextId() + "");  //设置ID
        this.lableDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        this.lableDao.save(label);
    }

    /**
     * 根据id删除标签
     * @param id
     */
    public void deleteById(String id){
        this.lableDao.deleteById(id);
    }

    /**
     * 根据label查询标签
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label){
        return this.lableDao.findAll(createSpecification(label));
    }

    /**
     * 构建查询条件
     * @param label
     * @return
     */
    private Specification<Label> createSpecification(Label label) {
        return new Specification<Label>() {
            /**
             * @param root 根对象，把条件封装到该对象中。where 类名=label.getId
             * @param criteriaQuery 封装查询关键字，如group by等
             * @param cb 封装条件对象，如果直接返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();  //存放多个查询条件

                if (label.getLabelname()!= null && !"".equals(label.getLabelname())){
                    //等同于labelname like "%"小明"%"
                    list.add(cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
                }

                if (label.getState()!= null && !"".equals(label.getState())){
                    //等同于state="1"
                    list.add(cb.equal(root.get("state").as(String.class), label.getState()));
                }

                if (label.getRecommend()!= null && !"".equals(label.getRecommend())){
                    //等同于recommend="1"
                    list.add(cb.equal(root.get("recommend").as(String.class), label.getRecommend()));
                }

                //最终拼接成 where labelname like "%"小明"%" and state="1" and recommend="1"
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }

    /**
     * 分页查询标签
     * @param page
     * @param size
     * @param label
     * @return
     */
    public Page<Label> pageQuery(int page, int size, Label label) {
        Specification<Label> s = this.createSpecification(label);
        PageRequest pr = PageRequest.of(page-1, size);
        return this.lableDao.findAll(s, pr);
    }
}
