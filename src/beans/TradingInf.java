package beans;

public class TradingInf {
	// 交易ID
	private int tradingId;
	// 交易商品ID
	private int tradingGoodsId;
	// 交易卖家ID
	private int tradingUserId;
	// 交易数量
	private int tradingNum;

	public int getTradingId() {
		return tradingId;
	}

	public void setTradingId(int tradingId) {
		this.tradingId = tradingId;
	}

	public int getTradingGoodsId() {
		return tradingGoodsId;
	}

	public void setTradingGoodsId(int tradingGoodsId) {
		this.tradingGoodsId = tradingGoodsId;
	}

	public int getTradingUserId() {
		return tradingUserId;
	}

	public void setTradingUserId(int tradingUserId) {
		this.tradingUserId = tradingUserId;
	}

	public int getTradingNum() {
		return tradingNum;
	}

	public void setTradingNum(int tradingNum) {
		this.tradingNum = tradingNum;
	}

}
