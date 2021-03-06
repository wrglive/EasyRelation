package com.marshall.sky.easyrelation.model;

import java.util.Date;

public class Relation {

  private Long leftId;

  private Long rightId;

  private Long createTime;

  private Date updateTime;

  private Integer state;

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

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getExtParams() {
    return extParams;
  }

  public void setExtParams(String extParams) {
    this.extParams = extParams == null ? null : extParams.trim();
  }
}