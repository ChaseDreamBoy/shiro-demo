package com.xh.controller;


import com.xh.enums.SysResultEnums;
import com.xh.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公司表，用来测试权限 前端控制器
 * sys:company:list
 * sys:company:save
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("list")
    public Result queryList() {
        log.info("query company list...");
        return Result.customResultEnum(SysResultEnums.COMPANY_LIST_SUCCESS);
    }

    @PostMapping("add")
    public Result addCompany() {
        log.info("add company ...");
        return Result.customResultEnum(SysResultEnums.COMPANY_ADD_SUCCESS);
    }

    @PostMapping("del")
    public Result delCompany() {
        log.info("del company ...");
        return Result.customResultEnum(SysResultEnums.COMPANY_DEL_SUCCESS);
    }

    @PostMapping("detail")
    public Result detailCompany() {
        log.info("detail company ...");
        return Result.customResultEnum(SysResultEnums.COMPANY_DETAIL_SUCCESS);
    }

    @PostMapping("update")
    public Result updateCompany() {
        log.info("update company ...");
        return Result.customResultEnum(SysResultEnums.COMPANY_UPDATE_SUCCESS);
    }

}
