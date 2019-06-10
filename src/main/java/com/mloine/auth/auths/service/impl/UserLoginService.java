package com.mloine.auth.auths.service.impl;




import com.mloine.auth.auths.entity.UserInfoVO;
import com.mloine.auth.auths.entity.UserLoginInfo;
import com.mloine.auth.auths.entity.base.PageInfoVO;
import com.mloine.auth.auths.entity.base.PageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserLoginService extends BaseService<UserLoginInfo> {

    /**
     * 获取用户权限
     * @param userId userId
     * @return 用户权限
     */
    Set<String> findPermissionsByUserId(Long userId);

    /**
     * 获取授权访问的用户列表
     * @param page 分页信息
     * @param searchType 搜索类型， 可以为空
     * @param searchText 搜索字段， 可以为空
     * @return 用户信息列表
     */
    PageVO<UserInfoVO> listPermitted(PageInfoVO page, String searchType, String searchText, Date startValidTime, Date endValidTime);

    /**
     * 根据角色获取用户数量
     * @param roleName
     * @return
     */
    int countUserNumByRoleName(String roleName);
    


    /**
     * 根据班级类型编号获得班级列表
     * @param classType 班级类型编号
     * @return 班级列表
     */
    List<String> listClassNoAndClassNameByClassType(Long classType);

    /**
     * 从 Excel 文件批量导入用户
     * @return 返回失败的行数和原因
     */
    List<String> batchImportWithExcel(MultipartFile multipartFile, String className, String classType, Date validTime, Date invalidTime) throws IOException;

    void saveWithThrowException(UserLoginInfo loginInfoList);

    /**
     * 登录失败时做的一些数据处理
     * @param userLoginInfo 完整的用户信息
     */
    void handleLoginFail(UserLoginInfo userLoginInfo);

    /**
     * 登陆成功时做的一些数据处理
     * @param userLoginInfo 完整的用户信息
     */
    void handleLoginSuccess(UserLoginInfo userLoginInfo);

    /**
     * 处理用户的经验值及等级的训练等信息的更新
     * @param userInfoVO 完整的用户信息
     * @return 经验值
     */
    UserInfoVO updateUserExtendValue(UserInfoVO userInfoVO);

    /**
     * 新建一个用户
     * @param userInfoVO 用户
     * @return
     */
    int newUser(UserInfoVO userInfoVO);

    /**
     * 更新一个用户
     * @param userInfoVO 用户
     * @return
     */
    int updateUser(UserInfoVO userInfoVO);

    /**
     * 删除用户
     * @param userId 用户Id
     * @return
     */
    int deleteUser(Long userId);

    /**
     * 获取图片，获取时会先从本地获取，
     * 如果本地不存在，则到 FTP 服务器获取，
     * 如果 FTP 服务器不存在，则调用 TTS 服务生成音频文件，并上传至 FTP 服务器
     * @param info 问题信息
     * @return 语音文件
     */
    File getImage(UserLoginInfo info);
}
