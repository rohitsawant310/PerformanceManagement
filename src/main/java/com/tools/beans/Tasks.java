package com.tools.beans;

import java.util.Date;

public class Tasks {
	
	private Integer id;
	private String taskName;
	private String taskType;
	private String taskDescription;
	private Date taskCreateDate;
	private Date taskUpdateDate;
	private Date taskEndDate;
	private Date taskClosedDate;
	private String ownerUserId;
	private String ownerGroupId;
	private String enabled;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Date getTaskCreateDate() {
		return taskCreateDate;
	}
	public void setTaskCreateDate(Date taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}
	public Date getTaskUpdateDate() {
		return taskUpdateDate;
	}
	public void setTaskUpdateDate(Date taskUpdateDate) {
		this.taskUpdateDate = taskUpdateDate;
	}
	public Date getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	public Date getTaskClosedDate() {
		return taskClosedDate;
	}
	public void setTaskClosedDate(Date taskClosedDate) {
		this.taskClosedDate = taskClosedDate;
	}
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public String getOwnerGroupId() {
		return ownerGroupId;
	}
	public void setOwnerGroupId(String ownerGroupId) {
		this.ownerGroupId = ownerGroupId;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "Tasks [id=" + id + ", taskName=" + taskName + ", taskType=" + taskType + ", taskDescription="
				+ taskDescription + ", taskCreateDate=" + taskCreateDate + ", taskUpdateDate=" + taskUpdateDate
				+ ", taskEndDate=" + taskEndDate + ", taskClosedDate=" + taskClosedDate + ", ownerUserId=" + ownerUserId
				+ ", ownerGroupId=" + ownerGroupId + ", enabled=" + enabled + "]";
	}
	
}
