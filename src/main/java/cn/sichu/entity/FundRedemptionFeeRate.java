package cn.sichu.entity;

/**
 * fund_redemption_rule
 *
 * @author sichu huang
 * @date 2024/03/12
 **/
public class FundRedemptionFeeRate {

    private Long id;
    private String code;
    private String feeRateChangeDays;
    private String feeRate;
    private String tradingPlatform;

    public FundRedemptionFeeRate() {
    }

    public FundRedemptionFeeRate(Long id, String code, String feeRateChangeDays, String feeRate,
        String tradingPlatform) {
        this.id = id;
        this.code = code;
        this.feeRateChangeDays = feeRateChangeDays;
        this.feeRate = feeRate;
        this.tradingPlatform = tradingPlatform;
    }

    @Override
    public String toString() {
        return "FundRedemptionFeeRate{" + "id=" + id + ", code='" + code + '\'' + ", feeRateChangeDays='"
            + feeRateChangeDays + '\'' + ", feeRate='" + feeRate + '\'' + ", tradingPlatform='" + tradingPlatform + '\''
            + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFeeRateChangeDays() {
        return feeRateChangeDays;
    }

    public void setFeeRateChangeDays(String feeRateChangeDays) {
        this.feeRateChangeDays = feeRateChangeDays;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getTradingPlatform() {
        return tradingPlatform;
    }

    public void setTradingPlatform(String tradingPlatform) {
        this.tradingPlatform = tradingPlatform;
    }
}
