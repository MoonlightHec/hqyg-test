package com.hqyg.iss;

public class StockQuantity {

    //在库库存
    private String inWarehouse;
    //共享在库库存
    private String inWarehouseShare;
    //仓库占用库存
    private String stockOccupy;
    //共享仓库占用库存
    private String stockOccupyShare;
    //订单共享占用库存
    private String orderOccupyShare;
    //订单占用库存
    private String orderOccupy;


    public String getInWarehouse() {
        return inWarehouse;
    }

    public void setInWarehouse(String inWarehouse) {
        this.inWarehouse = inWarehouse;
    }

    public String getInWarehouseShare() {
        return inWarehouseShare;
    }

    public void setInWarehouseShare(String inWarehouseShare) {
        this.inWarehouseShare = inWarehouseShare;
    }

    public String getStockOccupy() {
        return stockOccupy;
    }

    public void setStockOccupy(String stockOccupy) {
        this.stockOccupy = stockOccupy;
    }

    public String getStockOccupyShare() {
        return stockOccupyShare;
    }

    public void setStockOccupyShare(String stockOccupyShare) {
        this.stockOccupyShare = stockOccupyShare;
    }

    public String getOrderOccupyShare() {
        return orderOccupyShare;
    }

    public void setOrderOccupyShare(String orderOccupyShare) {
        this.orderOccupyShare = orderOccupyShare;
    }

    public String getOrderOccupy() {
        return orderOccupy;
    }

    public void setOrderOccupy(String orderOccupy) {
        this.orderOccupy = orderOccupy;
    }
}
