package com.mloine.auth.auths.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mloine.auth.auths.dao.MenuInfoMapper;
import com.mloine.auth.auths.dao.RoleInfoMapper;
import com.mloine.auth.auths.dao.UserLoginInfoMapper;
import com.mloine.auth.auths.entity.UserInfoVO;
import com.mloine.auth.auths.entity.UserLoginInfo;
import com.mloine.auth.auths.entity.base.PageInfoVO;
import com.mloine.auth.auths.entity.base.PageVO;
import com.mloine.auth.auths.service.impl.UserLoginService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 */
@Service
public class UserLoginServiceImpl extends BaseServiceImpl<UserLoginInfoMapper, UserLoginInfo> implements
        UserLoginService{


    private final MenuInfoMapper menuInfoMapper;
    @Autowired
    public UserLoginServiceImpl(MenuInfoMapper menuInfoMapper) {
        this.menuInfoMapper = menuInfoMapper;
    }

    /**
     * 获取用户权限
     *
     * @param userId userId
     * @return 用户权限
     */
    @Override
    public Set<String> findPermissionsByUserId(Long userId) {
        Set<String> permissions = menuInfoMapper.findPermissionsByUserId(userId);
        Set<String> result = new HashSet<>();
        for (String permission : permissions) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            permission = StringUtils.trim(permission);
            result.addAll(Arrays.asList(permission.split("\\s*;\\s*")));
        }
        return result;
    }

    /**
     * 获取授权访问的用户列表
     *
     * @param page           分页信息
     * @param searchType     搜索类型， 可以为空
     * @param searchText     搜索字段， 可以为空
     * @param startValidTime
     * @param endValidTime   @return 用户信息列表
     */
    @Override
    public PageVO<UserInfoVO> listPermitted(PageInfoVO page, String searchType, String searchText, Date startValidTime, Date endValidTime) {
        return null;
    }

    /**
     * 根据角色获取用户数量
     *
     * @param roleName
     * @return
     */
    @Override
    public int countUserNumByRoleName(String roleName) {
        return 0;
    }

    /**
     * 根据班级类型编号获得班级列表
     *
     * @param classType 班级类型编号
     * @return 班级列表
     */
    @Override
    public List<String> listClassNoAndClassNameByClassType(Long classType) {
        return null;
    }

    /**
     * 从 Excel 文件批量导入用户
     *
     * @param multipartFile
     * @param className
     * @param classType
     * @param validTime
     * @param invalidTime
     * @return 返回失败的行数和原因
     */
    @Override
    public List<String> batchImportWithExcel(MultipartFile multipartFile, String className, String classType, Date validTime, Date invalidTime) throws IOException {
        return null;
    }

    @Override
    public void saveWithThrowException(UserLoginInfo loginInfoList) {

    }

    /**
     * 登录失败时做的一些数据处理
     *
     * @param userLoginInfo 完整的用户信息
     */
    @Override
    public void handleLoginFail(UserLoginInfo userLoginInfo) {

    }

    /**
     * 登陆成功时做的一些数据处理
     *
     * @param userLoginInfo 完整的用户信息
     */
    @Override
    public void handleLoginSuccess(UserLoginInfo userLoginInfo) {

    }

    /**
     * 处理用户的经验值及等级的训练等信息的更新
     *
     * @param userInfoVO 完整的用户信息
     * @return 经验值
     */
    @Override
    public UserInfoVO updateUserExtendValue(UserInfoVO userInfoVO) {
        return null;
    }

    /**
     * 新建一个用户
     *
     * @param userInfoVO 用户
     * @return
     */
    @Override
    public int newUser(UserInfoVO userInfoVO) {
        return 0;
    }

    /**
     * 更新一个用户
     *
     * @param userInfoVO 用户
     * @return
     */
    @Override
    public int updateUser(UserInfoVO userInfoVO) {
        return 0;
    }

    /**
     * 删除用户
     *
     * @param userId 用户Id
     * @return
     */
    @Override
    public int deleteUser(Long userId) {
        return 0;
    }

    /**
     * 获取图片，获取时会先从本地获取，
     * 如果本地不存在，则到 FTP 服务器获取，
     * 如果 FTP 服务器不存在，则调用 TTS 服务生成音频文件，并上传至 FTP 服务器
     *
     * @param info 问题信息
     * @return 语音文件
     */
    @Override
    public File getImage(UserLoginInfo info) {
        return null;
    }
}
