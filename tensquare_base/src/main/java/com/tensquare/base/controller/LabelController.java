package com.tensquare.base.controller;

import com.sun.org.apache.bcel.internal.generic.LADD;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanshuiqiu
 * @create 2020-09-24 14:20
 */

@RestController
@CrossOrigin  //不同微服务间实现跨域访问
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部标签列表
     * @return
     */
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", this.labelService.findAll());
    }

    /**
     * 根据id查询标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK, "查询成功", this.labelService.findById(labelId));
    }

    /**
     * 添加标签
     * @param label
     * @return
     */
//    @PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        this.labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改标签
     * @param labelId
     * @param label
     * @return
     */
//    @PutMapping("/{labelId}")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        this.labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     * @param labelId
     * @return
     */
//    @DeleteMapping("/{labelId}")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId){
        this.labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        return new Result(true, StatusCode.OK, "查询成功", this.labelService.findSearch(label));
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageQuery(@PathVariable int page, @PathVariable int size, @RequestBody Label label){
        Page<Label> pageDate = this.labelService.pageQuery(page, size, label);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageDate.getTotalElements(), pageDate.getContent()));
    }

}
