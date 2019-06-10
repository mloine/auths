package com.mloine.auth.auths.entity;

import com.mloine.auth.auths.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色信息表
 */
@Table(name = "pait_role_info")
public class RoleInfo extends BaseEntity<RoleInfo> {

	public static final String ADMIN = "ADMIN";
	public static final String DESIGNER = "DESIGNER";
	public static final String TEACHER = "TEACHER";
	public static final String STUDENT = "STUDENT";


	private static final long serialVersionUID = 1L;


	/**
	 * 角色编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "角色编号", hidden = true)
	@Column(name = "role_code")
	private Long roleCode;

	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称")
	@Column(name = "role_name")
	private String roleName;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", hidden = true)
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 创建人工号
	 */
	@ApiModelProperty(value = "创建人工号", hidden = true)
	@Column(name = "create_user_code")
	private String createUserCode;

	/**
	 * 创建人姓名
	 */
	@ApiModelProperty(value = "创建人姓名", hidden = true)
	@Column(name = "create_user_name")
	private String createUserName;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间", hidden = true)
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 更新人工号
	 */
	@ApiModelProperty(value = "更新人工号", hidden = true)
	@Column(name = "update_user_code")
	private String updateUserCode;

	/**
	 * 更新人姓名
	 */
	@ApiModelProperty(value = "更新人姓名", hidden = true)
	@Column(name = "update_user_name")
	private String updateUserName;

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

	/**
	 * GET 角色编号
	 */
	public Long getRoleCode() {
		return this.roleCode;
	}

	/**
	 * SET 角色编号
	 */
	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * GET 角色名称
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * SET 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * GET 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * SET 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * GET 创建人工号
	 */
	public String getCreateUserCode() {
		return this.createUserCode;
	}

	/**
	 * SET 创建人工号
	 */
	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	/**
	 * GET 创建人姓名
	 */
	public String getCreateUserName() {
		return this.createUserName;
	}

	/**
	 * SET 创建人姓名
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * GET 更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * SET 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * GET 更新人工号
	 */
	public String getUpdateUserCode() {
		return this.updateUserCode;
	}

	/**
	 * SET 更新人工号
	 */
	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	/**
	 * GET 更新人姓名
	 */
	public String getUpdateUserName() {
		return this.updateUserName;
	}

	/**
	 * SET 更新人姓名
	 */
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
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

}
