package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Profit;
import jdbc.DBUtil;

public class Service {
	private List list;
	
	public List getProfit() {
		list = new ArrayList<Profit>();
		Connection conn = DBUtil.getConnection();
		
		String sql = "select g.goods_name goodsName,g.selling sellingPrice,g.cost_price costPrice,g.goods_id goodsId "
				+ "from goodsinfo g, tradinginfo t where t.trading_goods_id=g.goods_id "
				+ "GROUP BY goodsName,sellingPrice,costPrice,goodsId";
		
		try {
			PreparedStatement ptmt;
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Profit pf = new Profit();
				pf.setGoodsName(rs.getString("goodsName"));
				pf.setSellingPrice(rs.getInt("sellingPrice"));
				pf.setCostPrice(rs.getInt("costPrice"));
				pf.setGoodsId(rs.getInt("goodsId"));
				
				int earn = pf.getSellingPrice()-pf.getCostPrice();
				String sql1 = "select SUM(t.trading_number) sumNum from tradinginfo t "
						+ "WHERE t.trading_goods_id= "+pf.getGoodsId()+"";
				PreparedStatement ptmt1 = conn.prepareStatement(sql1);
				ResultSet rs1 = ptmt1.executeQuery();
				while (rs1.next()) {
					pf.setTradingNum(rs1.getInt("sumNum"));
				}
				
				pf.setProfit(earn*pf.getTradingNum());
				
				String sql2 = "SELECT SUM(t.trading_goods_id) times from tradinginfo t "
						+ "where t.trading_goods_id="+pf.getGoodsId()+"";
				PreparedStatement ptmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = ptmt2.executeQuery();
				while (rs2.next()) {
					pf.setTimes(rs2.getInt("times"));
				}
				
				list.add(pf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		Service s = new Service();
//		List l = s.getProfit();
//		for (int i = 0; i < l.size(); i++) {
//			Profit pf = (Profit)l.get(i);
//			System.out.println(pf.toString());
//		}
//	}
}



















