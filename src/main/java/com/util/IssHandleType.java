package com.util;





/**
 * iss处理类型枚举
 *
 * @author wuminjian
 */

public enum IssHandleType {
    ORDER_RECEIVE(1, "订单接收"),
    ORDER_CHANGE(2, "订单变更"),
    ORDER_CANCEL(3, "订单作废"),
    ORDER_PICKING_CREATE(4, "订单配货"),
    ORDER_PICKING_DELETE(5, "删除配货单"),
    HW_CALLBACK(6, "海外包裹回收"),
    INSTOCK_CLOUD(7, "云仓入库"),   
    INSTOCK_OTHER(8, "其他入库"),
    INSTOCK_RECEIPT(9, "仓库收货"),
    INSTOCK_RECEIPTDIFF(10, "收货差异"),
    INSTOCK_RECOVERY(11, "回收入库"),
    INSTOCK_REFUND(12, "退件入库"),
    INSTOCK_RETURNED(13, "产品外借归还"),
    INSTOCK_UNFREEZE(14, "取消冻结入库"),
    INSTOCK_UPSHELVESDIFF(15, "上架差异"),
    INSTOCK_UPSHELVES(16, "入库上架"),

    PURCHASE_DELIVER(17, "采购订单送货"),
    PURCHASE_ADUIT(18, "采购订单审核"),
    PURCHASE_CHANGE(19, "采购订单变更"),
    PURCHASE_TRANSFER_DELIVER(20, "采购订单中转送货"),
    PURCHASE_DIFF_DELIVER(21, "采购差异送货"),
    PURCHASE_DEFECTIVER_DELIVER(60, "采购不良品送货"),
    PURCHASE_CANCEL_DIFF(61, "采购差异取消"),
    PURCHASE_CANCEL_DEFECTIVER(62, "采购不良品取消"),
    PURCHASE_NOT_DIFF(63, "采购未到货差异"),
    PURCHASE__WARE_DIFF_DELIVER(22, "库内差异不良送货"),

    HEAD_MATCH(23, "头程配货"),
    HEAD_DELIVERY(24, "头程发货"),
    CENTER_DELIVERY(25, "中央仓配货"),
    CANCEL_DELIVERY(26, "取消配货入库"),

    QC_ALLOT(27, "调拨质检"),
    QC_PURCHASE(28, "采购质检"),


    ALLOT_OUT_STOCK(31, "调拨出库"),
    ALLOT_CANCEL(30, "调拨取消"),

    /**************仓库占用枚举************************/
    ALLOT_SELECT(64, "调拨查询"),
    ALLOT_SELECT_CANCEL(65, "调拨查询取消"),
    ALLOT_APPLY(29, "调拨申请"),
    ALLOT_APPLY_CANCEL(66, "调拨申请取消"),
    MOVE_STOCK_APPLY(67, "搬仓申请"),
    OUTSTOCK_ORDER(32, "订单出库"),
    OUTSTOCK_OTHER(33, "其他出库"),
    OUTSTOCK_BAD(34, "不良品出库"),
    OUTSTOCK_FREEZE(35, "冻结出库"),
    OTHER_OUT_STOCK_APPLY(36, "其他出库申请"),
    OTHER_OUT_STOCK_CANCEL(68, "其他出库取消"),
    OTHER_OUT_STOCK_CHANGE(46, "其他出库变更"),

    OTHER_BAD_CANCEL(37, "不良品退货"),
    OTHER_ORDER_CANCEL(38, "订单取消"),

    ADJUSTMENT_IN_STOCK(39, "调整入库"),
    ADJUSTMENT_OUT_STOCK(40, "调整出库"),

    BAD_ALLOCATION(41, "不良品调拨出库"),

    QC_ALLOT_IN_STOCK(42, "调拨入库质检"),

    ORDER_MERGE(43, "订单合并"),
    ORDER_MERGE_CANCEL(44, "订单合并删除"),

    OTHER_PRODUCT_REPLACE(45, "产品替换"),



    YUN_ALLOT_OUT_STOCK(47, "调拨出库(云仓)"),
    BAILUN_PURCHASE_DELIVER(48, "采购送货【百伦】"),
    RETURN_RESEND(49, "退件重发"),
    HEAD_RETURN(50, "头程退货"),

    INSTOCK_PROFIT(51, "盘盈入库"),
    INSTOCK_CHANGE(52, "库存调整入库"),
    INSTOCK_LOSE_FIND(53, "头程丢失找回入库"),
    OUTSTOCK_LOSS(54, "盘亏出库"),
    OUTSTOCK_CHANGE(55, "库存调整出库"),
    CH_CALLBACK(56, "国内包裹回收"),
    OWNER_CHANGE(57, "货主转移"),
    OTHER_APPLY_BAD(58, "其他出库申请-不良品登记"),
    OTHER_APPLY_RETN_GOOD(59, "其他出库-退货出库"),
    ORDER_PICKING_CREATE_DROP_SHIPPING(69, "订单配货(一件代发)"),
    PLATFORM_IN_STOCK(70, "平台入库"),

    PRODUCT_LENDING_OUT(71, "产品外借出库"),
    PRODUCT_LENDING_DIFF(72, "产品外借收货差异"),
    PRODUCT_LENDING_COMPENSATE(73, "产品外借赔偿"),
    OUT_STOCK_EXP(74, "出库异常处理"),
    SUPPLIER_STOCK_UPDATE(75, "供应商库存更新"),
    ;


    private Integer code;

    private String text;

    IssHandleType(Integer code, String text) {
        this.code = code;
        this.text = text;
    }


    public Integer getCode() {
        return code;
    }


    public String getText() {
        return text;
    }
}
