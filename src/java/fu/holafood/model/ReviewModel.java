/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.Reviews;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;



/**
 *
 * @author BH_Wind
 */
public class ReviewModel extends DBContext{

    public ReviewModel() {
        super();
    }
    
        public ArrayList<Reviews> getReview() throws Exception {
        ArrayList<Reviews> reviews = new ArrayList<>();
        String sql = "select * from reviews";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            reviews.add(new Reviews(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6)));
        }
        return reviews;
    }
    
    public Reviews getReviewById(int reviewId) throws Exception {
        String sql = "select * from reviews p where p.id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, reviewId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return new Reviews(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getString(4),  rs.getTimestamp(5), rs.getTimestamp(6));
        }
        return null;
    }
    
        public int deleteReview(int reviewId) throws Exception {
        String sql = "delete from reviews where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, reviewId);
        return ps.executeUpdate();
        }
        
        public int updateReview(int id, String value, Timestamp update_At) throws Exception {
        String sql = "update reviews set  value = ?, updated_at = ? where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
       // ps.setInt(1, item_id);
        ps.setString(1, value);
       // ps.setTimestamp(5, create_At);
        ps.setTimestamp(2, update_At);
        ps.setInt(3, id);
        return ps.executeUpdate();
  }
}