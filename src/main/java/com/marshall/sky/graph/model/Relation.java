package com.marshall.sky.graph.model;

import java.util.Date;

public class Relation {

  private Long leftId;

  private Long rightId;

  private Long createTime;

  private Date updateTime;

  private Boolean state;

  private String extParams;

  public Long getLeftId() {
    return leftId;
  }

  public void setLeftId(Long leftId) {
    this.leftId = leftId;
  }

  public Long getRightId() {
    return rightId;
  }

  public void setRightId(Long rightId) {
    this.rightId = rightId;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Boolean getState() {
    return state;
  }

  public void setState(Boolean state) {
    this.state = state;
  }

  public String getExtParams() {
    return extParams;
  }

  public void setExtParams(String extParams) {
    this.extParams = extParams == null ? null : extParams.trim();
  }
}