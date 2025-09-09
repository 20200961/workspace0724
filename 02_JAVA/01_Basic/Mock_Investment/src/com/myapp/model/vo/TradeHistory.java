package com.myapp.model.vo;

import java.sql.Timestamp;

public class TradeHistory {
	private String user_Id;
	private String assetId;
	private boolean tradetype;
	private double price;
	private double allPrice;
	private double quantity;
	private Timestamp trade_time;
	
	public TradeHistory() {
		super();
	}
	
	public TradeHistory(String user_Id) {
	    this.user_Id = user_Id;
	}
	
	public TradeHistory(String user_Id, String assetId, boolean tradetype, double price, double allPrice,
			double quantity, Timestamp trade_time) {
		super();
		this.user_Id = user_Id;
		this.assetId = assetId;
		this.tradetype = tradetype;
		this.price = price;
		this.allPrice = allPrice;
		this.quantity = quantity;
		this.trade_time = trade_time;
	}

	public String getUser_id() {
		return user_Id;
	}

	public void setUser_id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public boolean isTradetype() {
		return tradetype;
	}

	public void setTradetype(boolean tradetype) {
		this.tradetype = tradetype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Timestamp getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(Timestamp trade_time) {
		this.trade_time = trade_time;
	}

	@Override
	public String toString() {
	    return "TradeHistory{" +
	            "user_Id='" + user_Id + '\'' +
	            ", assetId='" + assetId + '\'' +
	            ", tradetype=" + (tradetype ? "BUY" : "SELL") +
	            ", price=" + price +
	            ", allPrice=" + allPrice +
	            ", quantity=" + quantity +
	            ", trade_time=" + trade_time +
	            '}';
	}
	
	
}
