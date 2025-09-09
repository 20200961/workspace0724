package com.myapp.model.vo;

public class Portfolio {
    private String userId;
    private String assetId;
    private double quantity;
    private double avgPrice;

    public Portfolio() {
        super();
    }

    public Portfolio(String userId, String assetId, double quantity, double avgPrice) {
        super();
        this.userId = userId;
        this.assetId = assetId;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Override
    public String toString() {
        return "Portfolio{" +
                "userId='" + userId + '\'' +
                ", assetId='" + assetId + '\'' +
                ", quantity=" + quantity +
                ", totalValue=" + avgPrice +
                '}';
    }
}