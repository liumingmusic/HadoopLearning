package com.c503.scala.entity;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 描述: 城市实体类
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-29 23:05
 */
public class City implements Writable {

    //地区编号
    private String areaId;

    //父地区编号
    private String parentId;

    //地区中文名称
    private String areaName;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeBytes(areaId);
        dataOutput.writeBytes(parentId);
        dataOutput.writeBytes(areaName);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.areaId = dataInput.readLine();
        this.parentId = dataInput.readLine();
        this.areaName = dataInput.readLine();
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        //规定area长度为6，不足添加0对齐
        if (parentId.length() == 6) {
            return parentId;
        } else {
            return String.format("%0" + 6 + "d", Integer.parseInt(parentId) + 1);
        }
    }

    public String getParentId() {
        //规定area长度为6，不足添加0对齐
        if (areaId.length() == 6) {
            return areaId;
        } else {
            return String.format("%0" + 6 + "d", Integer.parseInt(areaId) + 1);
        }
    }

    public String getAreaName() {
        return areaName;
    }

}
