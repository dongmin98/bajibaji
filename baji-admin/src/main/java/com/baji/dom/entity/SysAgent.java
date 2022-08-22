package com.baji.dom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName sys_agent
 */
@TableName(value ="sys_agent")
@Data
public class SysAgent implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 业务员代码
     */
    private String agentCode;

    /**
     * 业务员名称
     */
    private String agentName;

    /**
     * 机构代码
     */
    private String manageCode;

    /**
     * 机构名
     */
    private String manageName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 业务员类型：
     */
    private Integer agentType;

    /**
     * 业务员状态：0-激活，1-未激活
     */
    private Integer agentStatus;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 创建用户
     */
    private String createdUser;

    /**
     * 修改时间
     */
    private Date modifiedDate;

    /**
     * 修改用户
     */
    private String modifiedUser;

    /**
     * 删除：0-未删除，1-已删除
     */
    private String isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysAgent other = (SysAgent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAgentCode() == null ? other.getAgentCode() == null : this.getAgentCode().equals(other.getAgentCode()))
            && (this.getAgentName() == null ? other.getAgentName() == null : this.getAgentName().equals(other.getAgentName()))
            && (this.getManageCode() == null ? other.getManageCode() == null : this.getManageCode().equals(other.getManageCode()))
            && (this.getManageName() == null ? other.getManageName() == null : this.getManageName().equals(other.getManageName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getAgentType() == null ? other.getAgentType() == null : this.getAgentType().equals(other.getAgentType()))
            && (this.getAgentStatus() == null ? other.getAgentStatus() == null : this.getAgentStatus().equals(other.getAgentStatus()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getCreatedUser() == null ? other.getCreatedUser() == null : this.getCreatedUser().equals(other.getCreatedUser()))
            && (this.getModifiedDate() == null ? other.getModifiedDate() == null : this.getModifiedDate().equals(other.getModifiedDate()))
            && (this.getModifiedUser() == null ? other.getModifiedUser() == null : this.getModifiedUser().equals(other.getModifiedUser()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAgentCode() == null) ? 0 : getAgentCode().hashCode());
        result = prime * result + ((getAgentName() == null) ? 0 : getAgentName().hashCode());
        result = prime * result + ((getManageCode() == null) ? 0 : getManageCode().hashCode());
        result = prime * result + ((getManageName() == null) ? 0 : getManageName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getAgentType() == null) ? 0 : getAgentType().hashCode());
        result = prime * result + ((getAgentStatus() == null) ? 0 : getAgentStatus().hashCode());
        result = prime * result + ((getCreatedDate() == null) ? 0 : getCreatedDate().hashCode());
        result = prime * result + ((getCreatedUser() == null) ? 0 : getCreatedUser().hashCode());
        result = prime * result + ((getModifiedDate() == null) ? 0 : getModifiedDate().hashCode());
        result = prime * result + ((getModifiedUser() == null) ? 0 : getModifiedUser().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", agentCode=").append(agentCode);
        sb.append(", agentName=").append(agentName);
        sb.append(", manageCode=").append(manageCode);
        sb.append(", manageName=").append(manageName);
        sb.append(", mobile=").append(mobile);
        sb.append(", birthday=").append(birthday);
        sb.append(", agentType=").append(agentType);
        sb.append(", agentStatus=").append(agentStatus);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", createdUser=").append(createdUser);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", modifiedUser=").append(modifiedUser);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}