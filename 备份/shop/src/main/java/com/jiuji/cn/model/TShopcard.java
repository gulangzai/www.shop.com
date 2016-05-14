package com.jiuji.cn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TShopcard generated by hbm2java
 */
@Entity
@Table(name = "t_shopcard", catalog = "shop")
public class TShopcard implements java.io.Serializable {

	private String FShopCardId;
	private String FUserId;
	private String FProduceId;

	public TShopcard() {
	}

	public TShopcard(String FShopCardId) {
		this.FShopCardId = FShopCardId;
	}

	public TShopcard(String FShopCardId, String FUserId, String FProduceId) {
		this.FShopCardId = FShopCardId;
		this.FUserId = FUserId;
		this.FProduceId = FProduceId;
	}

	@Id

	@Column(name = "F_ShopCardId", unique = true, nullable = false, length = 44)
	public String getFShopCardId() {
		return this.FShopCardId;
	}

	public void setFShopCardId(String FShopCardId) {
		this.FShopCardId = FShopCardId;
	}

	@Column(name = "F_UserId", length = 44)
	public String getFUserId() {
		return this.FUserId;
	}

	public void setFUserId(String FUserId) {
		this.FUserId = FUserId;
	}

	@Column(name = "F_ProduceId", length = 44)
	public String getFProduceId() {
		return this.FProduceId;
	}

	public void setFProduceId(String FProduceId) {
		this.FProduceId = FProduceId;
	}

}
