package com.marshall.sky.easyrelation.model;

public enum StateEnum {
  OFFLINE(0),
  ONLINE(1),
  ;

  int index;

  public int getIndex() {
    return index;
  }

  StateEnum(int index) {
    this.index = index;
  }
}
