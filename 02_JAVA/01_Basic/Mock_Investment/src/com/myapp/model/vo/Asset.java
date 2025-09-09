package com.myapp.model.vo;

public class Asset {
	private String assetId;
	private String assetName;
	private double currentPrice;
	private double volatility;
	
	public Asset() {
		super();
	}
	
	public Asset(String assetId, String assetName, double currentPrice, double volatility) {
		super();
		this.assetId = assetId;
		this.assetName = assetName;
		this.currentPrice = currentPrice;
		this.volatility = volatility;
	}
	
	public Asset(String assetId) {
        this.assetId = assetId;
    }

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getVolatility() {
		return volatility;
	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}

	@Override
	public String toString() {
	    return "Asset{" +
	            "assetId='" + assetId + '\'' +
	            ", assetName='" + assetName + '\'' +
	            ", currentPrice=" + currentPrice +
	            ", volatility=" + volatility +
	            '}';
	}
	
	
	
	
	
}
