package com.xh.vo;

import com.xh.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleVo extends SysRole {

    private Long userRoleId;

}
