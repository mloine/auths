package com.mloine.auth.auths.entity;

import com.mloine.auth.auths.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * 菜单信息表
 */
@Table(name = "pait_menu_info")
public class MenuInfo extends BaseEntity<MenuInfo> {

	private static final long serialVersionUID = 1L;


	/**
	 * 菜单ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "菜单ID", hidden = true)
	@Column(name = "menu_id")
	private Long menuId;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	@Column(name = "menu_name")
	private String menuName;

	/**
	 * 菜单URL
	 */
	@ApiModelProperty(value = "菜单URL")
	@Column(name = "menu_url")
	private String menuUrl;

	/**
	 * 菜单权限
	 */
	@ApiModelProperty(value = "菜单权限")
	@Column(name = "menu_permissions")
	private String menuPermissions;

	/**
	 * 菜单级别
	 */
	@ApiModelProperty(value = "菜单级别")
	@Column(name = "menu_level")
	private String menuLevel;

	/**
	 * 父级菜单ID
	 */
	@ApiModelProperty(value = "父级菜单ID")
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 菜单状态
	 */
	@ApiModelProperty(value = "菜单状态")
	@Column(name = "menu_status")
	private String menuStatus;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", hidden = true)
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 创建人姓名
	 */
	@ApiModelProperty(value = "创建人姓名", hidden = true)
	@Column(name = "create_user_name")
	private String createUserName;

	/**
	 * 创建人工号
	 */
	@ApiModelProperty(value = "创建人工号", hidden = true)
	@Column(name = "create_user_code")
	private String createUserCode;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间", hidden = true)
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 更新人姓名
	 */
	@ApiModelProperty(value = "更新人姓名", hidden = true)
	@Column(name = "update_user_name")
	private String updateUserName;

	/**
	 * 更新人工号
	 */
	@ApiModelProperty(value = "更新人工号", hidden = true)
	@Column(name = "update_user_code")
	private String updateUserCode;

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
	 * 是否被授权访问
	 */
	@ApiModelProperty(value = "是否被授权访问", hidden = true)
	@Transient
	private Boolean permitted;

	/**
	 * GET 菜单ID
	 */
	public Long getMenuId() {
		return this.menuId;
	}

	/**
	 * SET 菜单ID
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * GET 菜单名称
	 */
	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * SET 菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * GET 菜单URL
	 */
	public String getMenuUrl() {
		return this.menuUrl;
	}

	/**
	 * SET 菜单URL
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * GET 菜单权限
	 */
	public String getMenuPermissions() {
		return this.menuPermissions;
	}

	/**
	 * SET 菜单权限
	 */
	public void setMenuPermissions(String menuPermissions) {
		this.menuPermissions = menuPermissions;
	}

	/**
	 * GET 菜单级别
	 */
	public String getMenuLevel() {
		return this.menuLevel;
	}

	/**
	 * SET 菜单级别
	 */
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	/**
	 * GET 父级菜单ID
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**
	 * SET 父级菜单ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * GET 菜单状态
	 */
	public String getMenuStatus() {
		return this.menuStatus;
	}

	/**
	 * SET 菜单状态
	 */
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
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

	public Boolean isPermitted() {
		return permitted;
	}

	public void setPermitted(Boolean permitted) {
		this.permitted = permitted;
	}

}
