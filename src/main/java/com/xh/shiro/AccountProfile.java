package com.xh.shiro;

import com.xh.entity.SysUser;
import lombok.*;

import java.io.Serializable;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfile implements Serializable {

    private Long userId;

    private String username;

    private String email;

    private String mobile;

    public static AccountProfile createAccountProfile(SysUser user){
        return AccountProfile.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .build();
    }

}
