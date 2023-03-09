package com.jymj.zhglxt.zjd.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SysKxXzqData implements Serializable {
	 // 行政区 ID
	private Long xzqId;
	// 行政区名称
	private String name;
	//宅宗数（总计宗数）
	private Integer zs;
	//宅宗数占比（总计占比）
	private BigDecimal zsZb;
	//占地面积（总计）
	private BigDecimal zhaizdmj  = new BigDecimal(0);
	//建筑面积（总计）
	private BigDecimal zhaijzmj  = new BigDecimal(0);
	//建筑面积户均（总计）
	private BigDecimal zhjzhj  = new BigDecimal(0);
	private Integer count4;
	//'四分地以下宗数占比'
	private BigDecimal count4Zb;
	//'四分地以下占地面积亩'
	private BigDecimal count4Zdmj;
	//'四分地以下建筑面积公顷'
	private BigDecimal count4Jzmj;
	//'四分地以下建筑面积户均'
	private BigDecimal count4Hj;
	//4-5分宗数
	private Integer count45;
	//'4分-5分宗数占比'
	private BigDecimal count45Zb;
	//'4分-5分占地面积亩
	private BigDecimal count45Zdmj;
	//'4分-5分建筑面积公顷'
	private BigDecimal count45Jzmj;
	//'4分-5分建筑面积户均'
	private BigDecimal count45Hj;
	//5-6分宗数
	private Integer count56;
	//'5分-6分宗数占比'
	private BigDecimal count56Zb;
	//'5分-6分占地面积亩'
	private BigDecimal count56Zdmj;
	//'5分-6分建筑面积公顷'
	private BigDecimal count56Jzmj;
	//'5分-6分建筑面积户均'
	private BigDecimal count56Hj;
	private Integer count68;
	//'6分-8分宗数占比'
	private BigDecimal count68Zb;
	//'6分-8分占地面积亩'
	private BigDecimal count68Zdmj;
	//'6分-8分建筑面积公顷'
	private BigDecimal count68Jzmj;
	//'6分-8分建筑面积户均'
	private BigDecimal count68Hj;
	//'8分以上宗数'
	private Integer count8;
	//'8分以上宗数占比'
	private BigDecimal count8Zb;
	//'8分以上占地面积亩'
	private BigDecimal count8Zdmj;
	//'8分以上建筑面积公顷'
	private BigDecimal count8Jzmj;
	//'8分以上建筑面积户均'
	private BigDecimal count8Hj;

	public Long getXzqId() {
		return xzqId;
	}

	public void setXzqId(Long xzqId) {
		this.xzqId = xzqId;
	}

	public String getName() {
		return name==null?"":name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getZhzdmj() {
		return zhaizdmj==null?new BigDecimal(0):zhaizdmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setZhzdmj(BigDecimal zhzdmj) {
		this.zhaizdmj = zhzdmj;
	}

	public BigDecimal getZhjzmj() {
		return zhaijzmj==null?new BigDecimal(0):zhaijzmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setZhjzmj(BigDecimal zhaijzmj) {
		this.zhaijzmj = zhaijzmj;
	}

	public Integer getZs() {
		return zs==null?0:zs;
	}

	public void setZs(Integer zs) {
		this.zs = zs;
	}

	public Integer getCount4() {
		return count4==null?0:count4;
	}

	public void setCount4(Integer count4) {
		this.count4 = count4;
	}

	public Integer getCount68() {
		return count68==null?0:count68;
	}

	public void setCount68(Integer count68) {
		this.count68 = count68;
	}

	public BigDecimal getZsZb() {
		return zsZb==null?new BigDecimal(0):zsZb.setScale(3, RoundingMode.HALF_UP);
	}

	public void setZsZb(BigDecimal zsZb) {
		this.zsZb = zsZb;
	}

	public BigDecimal getZhjzhj() {
		return zhjzhj==null?new BigDecimal(0):zhjzhj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setZhjzhj(BigDecimal zhjzhj) {
		this.zhjzhj = zhjzhj;
	}

	public BigDecimal getCount4Zb() {
		return count4Zb==null?new BigDecimal(0):count4Zb.setScale(3, RoundingMode.HALF_UP);
	}

	public void setCount4Zb(BigDecimal count4Zb) {
		this.count4Zb = count4Zb;
	}

	public BigDecimal getCount4Zdmj() {
		return count4Zdmj==null?new BigDecimal(0):count4Zdmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount4Zdmj(BigDecimal count4Zdmj) {
		this.count4Zdmj = count4Zdmj;
	}

	public BigDecimal getCount4Jzmj() {
		return count4Jzmj==null?new BigDecimal(0):count4Jzmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount4Jzmj(BigDecimal count4Jzmj) {
		this.count4Jzmj = count4Jzmj;
	}

	public BigDecimal getCount4Hj() {
		return count4Hj==null?new BigDecimal(0):count4Hj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount4Hj(BigDecimal count4Hj) {
		this.count4Hj = count4Hj;
	}

	public Integer getCount45() {
		return count45==null?0:count45;
	}

	public void setCount45(Integer count45) {
		this.count45 = count45;
	}

	public BigDecimal getCount45Zb() {
		return count45Zb==null?new BigDecimal(0):count45Zb.setScale(3, RoundingMode.HALF_UP);
	}

	public void setCount45Zb(BigDecimal count45Zb) {
		this.count45Zb = count45Zb;
	}

	public BigDecimal getCount45Zdmj() {
		return count45Zdmj==null?new BigDecimal(0):count45Zdmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount45Zdmj(BigDecimal count45Zdmj) {
		this.count45Zdmj = count45Zdmj;
	}

	public BigDecimal getCount45Jzmj() {
		return count45Jzmj==null?new BigDecimal(0):count45Jzmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount45Jzmj(BigDecimal count45Jzmj) {
		this.count45Jzmj = count45Jzmj;
	}

	public BigDecimal getCount45Hj() {
		return count45Hj==null?new BigDecimal(0):count45Hj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount45Hj(BigDecimal count45Hj) {
		this.count45Hj = count45Hj;
	}

	public Integer getCount56() {
		return count56==null?0:count56;
	}

	public void setCount56(Integer count56) {
		this.count56 = count56;
	}

	public BigDecimal getCount56Zb() {
		return count56Zb==null?new BigDecimal(0):count56Zb.setScale(3, RoundingMode.HALF_UP);
	}

	public void setCount56Zb(BigDecimal count56Zb) {
		this.count56Zb = count56Zb;
	}

	public BigDecimal getCount56Zdmj() {
		return count56Zdmj==null?new BigDecimal(0):count56Zdmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount56Zdmj(BigDecimal count56Zdmj) {
		this.count56Zdmj = count56Zdmj;
	}

	public BigDecimal getCount56Jzmj() {
		return count56Jzmj==null?new BigDecimal(0):count56Jzmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount56Jzmj(BigDecimal count56Jzmj) {
		this.count56Jzmj = count56Jzmj;
	}

	public BigDecimal getCount56Hj() {
		return count56Hj==null?new BigDecimal(0):count56Hj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount56Hj(BigDecimal count56Hj) {
		this.count56Hj = count56Hj;
	}

	public BigDecimal getCount68Zb() {
		return count68Zb==null?new BigDecimal(0):count68Zb.setScale(3, RoundingMode.HALF_UP);
	}

	public void setCount68Zb(BigDecimal count68Zb) {
		this.count68Zb = count68Zb;
	}

	public BigDecimal getCount68Zdmj() {
		return count68Zdmj==null?new BigDecimal(0):count68Zdmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount68Zdmj(BigDecimal count68Zdmj) {
		this.count68Zdmj = count68Zdmj;
	}

	public BigDecimal getCount68Jzmj() {
		return count68Jzmj==null?new BigDecimal(0):count68Jzmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount68Jzmj(BigDecimal count68Jzmj) {
		this.count68Jzmj = count68Jzmj;
	}

	public BigDecimal getCount68Hj() {
		return count68Hj==null?new BigDecimal(0):count68Hj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount68Hj(BigDecimal count68Hj) {
		this.count68Hj = count68Hj;
	}

	public Integer getCount8() {
		return count8==null?0:count8;
	}

	public void setCount8(Integer count8) {
		this.count8 = count8;
	}

	public BigDecimal getCount8Zb() {
		return count8Zb==null?new BigDecimal(0):count8Zb.setScale(3, RoundingMode.HALF_UP);
	}

	public void setCount8Zb(BigDecimal count8Zb) {
		this.count8Zb = count8Zb;
	}

	public BigDecimal getCount8Zdmj() {
		return count8Zdmj==null?new BigDecimal(0):count8Zdmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount8Zdmj(BigDecimal count8Zdmj) {
		this.count8Zdmj = count8Zdmj;
	}

	public BigDecimal getCount8Jzmj() {
		return count8Jzmj==null?new BigDecimal(0):count8Jzmj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount8Jzmj(BigDecimal count8Jzmj) {
		this.count8Jzmj = count8Jzmj;
	}

	public BigDecimal getCount8Hj() {
		return count8Hj==null?new BigDecimal(0):count8Hj.setScale(1, RoundingMode.HALF_UP);
	}

	public void setCount8Hj(BigDecimal count8Hj) {
		this.count8Hj = count8Hj;
	}
}
