package com.spring.model;

import lombok.Data;

/**
 * Created by dalp on 2017/8/7.
 */
@Data
public class GoddessInfo extends BaseModel {
    private String name; //姓名
    private String phone; //电话
    private String school; //学校
    private String major; //专业
    private String introduction; //简介
    private String primaryPhoto; //主照片
    private String secondaryPhoto; //副照片
    private String number; //编号
    private Long vote; //总票数
    private Long ranking; //排名
    private Long pageView; //围观人数
    private Integer status; //审核状态（0：未审核、1：审核未通过、2：已审核、3:取消报名）
    private Integer Reason; //审核未通过原因(0:图片不清晰 1:姓名不符合规范 2:专业信息不符合规范 3:图片不符合规范 4:简介内容不符合规范 5:其他)
    private String remark; //备注
}
