package com.mloine.auth.auths.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.mloine.auth.auths.entity.base.BaseEntity;
import com.mloine.auth.auths.utils.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户登陆信息表
 */
@Table(name = "pait_user_login_info")
public class UserLoginInfo extends BaseEntity<UserLoginInfo> {

	private static final long serialVersionUID = 1L;


	/**
	 * 员工ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "员工ID", hidden = true)
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 员工E代码
	 */
	@ApiModelProperty(value = "员工E代码")
	@Column(name = "e_code")
	private String eCode;

	/**
	 * 员工UM帐号
	 */
	@ApiModelProperty(value = "员工UM帐号")
	@Column(name = "um_code")
	private String umCode;

	/**
	 * 证件类型
	 */
	@ApiModelProperty(value = "证件类型")
	@Column(name = "card_type")
	private String cardType;

	/**
	 * 证件号码
	 */
	@ApiModelProperty(value = "证件号码")
	@Column(name = "card_no")
	private String cardNo;

	/**
	 * 员工姓名
	 */
	@ApiModelProperty(value = "员工姓名")
	@Column(name = "user_name")
	private String userName;

	/**
	 * 员工状态:在职/离职
	 */
	@ApiModelProperty(value = "员工状态:在职/离职", hidden = true)
	@Column(name = "user_status")
	private String userStatus;

	/**
	 * 生效时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生效时间")
	@Column(name = "valid_time")
	private Date validTime;

	/**
	 * 失效时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "失效时间")
	@Column(name = "Invalid_time")
	private Date invalidTime;

	/**
	 * 批次
	 */
	@ApiModelProperty(value = "批次", hidden = true)
	@Column(name = "batch_no")
	private String batchNo;

	/**
	 * 班级类别
	 */
	@ApiModelProperty(value = "班级类别")
	@Column(name = "class_type")
	private String classType;

	/**
	 * 班级号
	 */
	@ApiModelProperty(value = "班级号")
	@Column(name = "class_no")
	private String classNo;

	/**
	 * 班主任工号
	 */
	@ApiModelProperty(value = "班主任工号")
	@Column(name = "class_manager_code")
	private String classManagerCode;

	/**
	 * 班主任名字
	 */
	@ApiModelProperty(value = "班主任名字")
	@Column(name = "class_manager_name")
	private String classManagerName;

	/**
	 * 班级名称
	 */
	@ApiModelProperty(value = "班级名称")
	@Column(name = "class_name")
	private String className;

	/**
	 * 密码最近更新时间
	 */
	@JsonIgnore
	@ApiModelProperty(value = "密码最近更新时间", hidden = true)
	@Column(name = "password_update")
	private Date passwordUpdate;

	/**
	 * 密码
	 */
	@JsonIgnore
	@ApiModelProperty(value = "密码", hidden = true)
	@Column(name = "password")
	private String password;

	/**
	 * 经验级别
	 */
	@ApiModelProperty(value = "经验级别", hidden = true)
	@Column(name = "experience_level")
	private String experienceLevel;

	/**
	 * 经验值
	 */
	@ApiModelProperty(value = "经验值", hidden = true)
	@Column(name = "experience_value")
	private String experienceValue;

	/**
	 * 机构ID
	 */
	@ApiModelProperty(value = "机构ID")
	@Column(name = "area_code")
	private String areaCode;

	/**
	 * 机构名称
	 */
	@ApiModelProperty(value = "机构名称")
	@Column(name = "area_name")
	private String areaName;

	/**
	 * 扩展1
	 */
	@ApiModelProperty(value = "扩展1", hidden = true)
	@Column(name = "remark1")
	private String remark1;

	/**
	 * 扩展2
	 */
	@ApiModelProperty(value = "扩展2", hidden = true)
	@Column(name = "remark2")
	private String remark2;

	/**
	 * 扩展3
	 */
	@ApiModelProperty(value = "扩展3", hidden = true)
	@Column(name = "remark3")
	private Date remark3;

	@ApiModelProperty(hidden = true)
	@Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(hidden = true)
	@Column(name = "create_user_code")
	private String createUserCode;

	@ApiModelProperty(hidden = true)
	@Column(name = "create_user_name")
	private String createUserName;

	@ApiModelProperty(hidden = true)
	@Column(name = "update_time")
	private Date updateTime;

	@ApiModelProperty(hidden = true)
	@Column(name = "update_user_code")
	private String updateUserCode;

	@ApiModelProperty(hidden = true)
	@Column(name = "update_user_name")
	private String updateUserName;

	/**
	 * 连续登陆失败次数
	 */
	@ApiModelProperty(value = "连续登陆失败次数", hidden = true)
	@Column(name = "fail_count")
	private Long failCount;

	/**
	 * 上次登陆时间
	 */
	@ApiModelProperty(value = "上次登陆时间", hidden = true)
	@Column(name = "last_login_time")
	private Date lastLoginTime;

	/**
	 * 头像图片的 FTP 路径
	 */
	@ApiModelProperty(value = "头像图片的 FTP 路径")
	@Column(name = "head_image_path")
	private String headImagePath;

	/**
	 * GET 员工ID
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * SET 员工ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * GET 员工E代码
	 */
	public String getECode() {
		return this.eCode;
	}

	/**
	 * SET 员工E代码
	 */
	public void setECode(String eCode) {
		this.eCode = eCode;
	}

	/**
	 * GET 员工UM帐号
	 */
	public String getUmCode() {
		return this.umCode;
	}

	/**
	 * SET 员工UM帐号
	 */
	public void setUmCode(String umCode) {
		this.umCode = umCode;
	}

	/**
	 * GET 证件类型
	 */
	public String getCardType() {
		return this.cardType;
	}

	/**
	 * SET 证件类型
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * GET 证件号码
	 */
	public String getCardNo() {
		return this.cardNo;
	}

	/**
	 * SET 证件号码
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * GET 员工姓名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * SET 员工姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * GET 员工状态:在职/离职
	 */
	public String getUserStatus() {
		return this.userStatus;
	}

	/**
	 * SET 员工状态:在职/离职
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * GET 生效时间
	 */
	public Date getValidTime() {
		return this.validTime;
	}

	/**
	 * SET 生效时间
	 */
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	/**
	 * GET 失效时间
	 */
	public Date getInvalidTime() {
		return this.invalidTime;
	}

	/**
	 * SET 失效时间
	 */
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	/**
	 * GET 批次
	 */
	public String getBatchNo() {
		return this.batchNo;
	}

	/**
	 * SET 批次
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * GET 班级类别
	 */
	public String getClassType() {
		return this.classType;
	}

	/**
	 * SET 班级类别
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}

	/**
	 * GET 班级号
	 */
	public String getClassNo() {
		return this.classNo;
	}

	/**
	 * SET 班级号
	 */
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	/**
	 * GET 班主任工号
	 */
	public String getClassManagerCode() {
		return this.classManagerCode;
	}

	/**
	 * SET 班主任工号
	 */
	public void setClassManagerCode(String classManagerCode) {
		this.classManagerCode = classManagerCode;
	}

	/**
	 * GET 班主任名字
	 */
	public String getClassManagerName() {
		return this.classManagerName;
	}

	/**
	 * SET 班主任名字
	 */
	public void setClassManagerName(String classManagerName) {
		this.classManagerName = classManagerName;
	}

	/**
	 * GET 班级名称
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * SET 班级名称
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * GET 密码最近更新时间
	 */
	public Date getPasswordUpdate() {
		return this.passwordUpdate;
	}

	/**
	 * SET 密码最近更新时间
	 */
	public void setPasswordUpdate(Date passwordUpdate) {
		this.passwordUpdate = passwordUpdate;
	}

	/**
	 * GET 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * SET 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * GET 经验级别
	 */
	public String getExperienceLevel() {
		return this.experienceLevel;
	}

	/**
	 * SET 经验级别
	 */
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	/**
	 * GET 经验值
	 */
	public String getExperienceValue() {
		return this.experienceValue;
	}

	/**
	 * SET 经验值
	 */
	public void setExperienceValue(String experienceValue) {
		this.experienceValue = experienceValue;
	}

	/**
	 * GET 机构ID
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * SET 机构ID
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * GET 机构名称
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * SET 机构名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * GET 扩展1
	 */
	public String getRemark1() {
		return this.remark1;
	}

	/**
	 * SET 扩展1
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	/**
	 * GET 扩展2
	 */
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * SET 扩展2
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * GET 扩展3
	 */
	public Date getRemark3() {
		return this.remark3;
	}

	/**
	 * SET 扩展3
	 */
	public void setRemark3(Date remark3) {
		this.remark3 = remark3;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserCode() {
		return this.createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserCode() {
		return this.updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getUpdateUserName() {
		return this.updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	/**
	 * GET 连续登陆失败次数
	 */
	public Long getFailCount() {
		return this.failCount;
	}

	/**
	 * SET 连续登陆失败次数
	 */
	public void setFailCount(Long failCount) {
		this.failCount = failCount;
	}

	/**
	 * GET 上次登陆时间
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * SET 上次登陆时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * GET 头像图片的 FTP 路径
	 */
	public String getHeadImagePath() {
		return this.headImagePath;
	}

	/**
	 * SET 头像图片的 FTP 路径
	 */
	public void setHeadImagePath(String headImagePath) {
		this.headImagePath = headImagePath;
	}

	@Override
	public String toString() {
		return JsonUtils.encode(this);
	}
}
