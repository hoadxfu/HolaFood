/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.Rate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Ray Sparrow
 */
public class RatingModel extends DBContext{

    public RatingModel() {
        super();
    }
     public int deleteRate(int rateId) throws Exception{
         String sql = "delete from ratings where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, rateId);
        return ps.executeUpdate();
    }
     
     public Rate getRateById(int rateId) throws Exception{
        String sql = "select * from ratings r where r.id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, rateId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return new Rate(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5));
        }
        return null;
    }
     
      public int updateRate(int id,int value, Timestamp updateAt) throws Exception{
         String sql = "update ratings set value=?, updated_at = ? where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, value);
        ps.setTimestamp(2, updateAt);
        ps.setInt(3, id);
        return ps.executeUpdate();
    }
        public ArrayList<Rate> getRate() throws Exception{
        ArrayList<Rate> rates = new ArrayList<>();
        String sql = "select * from ratings";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           rates.add(new Rate(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5)));
        }
        return rates;
    }
}
