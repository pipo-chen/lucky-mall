package com.mall.dao;

import com.mall.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {

    int deleteByPrimaryKey(Integer adminUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    AdminUser selectLogin(@Param("username") String username, @Param("password") String password);

    int checkNickname(String nickname);

    int checkUsername(String username);

    List<AdminUser> selectAllUser();

    int changeLockedStatus(@Param("adminUserId") Integer adminUserId, @Param("locked") Byte locked);
}