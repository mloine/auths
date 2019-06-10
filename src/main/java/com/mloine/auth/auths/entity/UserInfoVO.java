package com.mloine.auth.auths.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/20
 * History :
 */
public class UserInfoVO extends UserLoginInfo {

    /**
     * 经验值
     */
    @Transient
    private Boolean levelUp;

    @Transient
    private String levelName;

    @Transient
    private Integer trainCount;

    @Transient
    private String totalTrainTime;

    /**
     * 角色 Id
     */
    @Transient
    @ApiModelProperty(value = "角色Id")
    @JsonIgnore
    private Long roleId;
    
    @Transient
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @Transient
    private Integer trainQuestionWaitTime;
    @Transient
    private Integer trainConversationWaitTime;


    @Override
    public String getCardNo() {
        return super.getCardNo();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @JsonIgnore
    @Override
    public Date getPasswordUpdate() {
        return super.getPasswordUpdate();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(Boolean levelUp) {
        this.levelUp = levelUp;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    public Integer getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(Integer trainCount) {
        this.trainCount = trainCount;
    }

    public String getTotalTrainTime() {
        return totalTrainTime;
    }

    public void setTotalTrainTime(String totalTrainTime) {
        this.totalTrainTime = totalTrainTime;
    }

    public Integer getTrainQuestionWaitTime() {
        return trainQuestionWaitTime;
    }

    public void setTrainQuestionWaitTime(Integer trainQuestionWaitTime) {
        this.trainQuestionWaitTime = trainQuestionWaitTime;
    }

    public Integer getTrainConversationWaitTime() {
        return trainConversationWaitTime;
    }

    public void setTrainConversationWaitTime(Integer trainConversationWaitTime) {
        this.trainConversationWaitTime = trainConversationWaitTime;
    }
}
