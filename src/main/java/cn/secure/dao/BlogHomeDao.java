
package cn.secure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Component;

import cn.secure.entity.Article;
import cn.secure.entity.Message;
import cn.secure.entity.Recharge;
import cn.secure.entity.Talk;
import cn.secure.util.BaseDao;

@Component
public class BlogHomeDao extends BaseDao {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Article> findArticleList(Integer id, String tag,Integer size) {
		// 设置参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " SELECT 	*  FROM   article , (SELECT min(id) minid, max(id) maxid FROM article)  t1 WHERE 1 = 1 ";
		if (id != null && 0!=id)
			sql += " AND id=:id   ";
		if (tag != null && !"".equals(tag))
			sql += " AND tag=:tag   ";
		sql += " 	GROUP BY id  ORDER BY isTop DESC ,time DESC ";
		if(null !=size )
			sql += "  LIMIT 0,:size ";
		paramMap.put("id", id);
		paramMap.put("tag", tag);
		paramMap.put("size", size);
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, articleMapper);
	}
	
	//联系搜索查询
	public List<Article> findArticleList( String title) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " SELECT 	*  FROM   article , (SELECT min(id) minid, max(id) maxid FROM article)  t1 WHERE title like :title ";	
		paramMap.put("title", "%"+title+"%");
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, articleMapper);
	}

	private RowMapper<Article> articleMapper = new RowMapper<Article>() {
		public Article mapRow(ResultSet rs, int arg1) throws SQLException {
			Article data = new Article();
			data.setId(rs.getInt("id"));
			data.setLeaveNum(rs.getInt("leaveNum"));
			data.setLoveNum(rs.getInt("loveNum"));
			data.setReadNum(rs.getInt("readNum"));

			data.setTitle(rs.getString("title"));
			data.setPoint(rs.getString("point"));
			data.setTag(rs.getString("tag"));
			data.setIsTop(rs.getInt("isTop"));
			data.setContent(rs.getString("content"));

			data.setTime(sdf.format(rs.getTimestamp("time")));
			data.setMaxid(rs.getInt("maxid"));
			data.setMinid(rs.getInt("minid"));

			return data;
		}
	};

	public int updateReadNum(Integer id) {
		// 设置参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " UPDATE  article  SET  readNum = readNum + 1 WHERE id = :id  ";
		paramMap.put("id", id);
		return this.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	public int updateLoveNum(Integer id) {
		// 设置参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " UPDATE  article  SET  loveNum = loveNum + 1 WHERE id = :id  ";
		paramMap.put("id", id);
		return this.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	public List<Talk> findTalkList(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " SELECT 	*   FROM   talk  WHERE aid = :id   ";
		paramMap.put("id", id);
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, talkMapper);
	}

	private RowMapper<Talk> talkMapper = new RowMapper<Talk>() {
		public Talk mapRow(ResultSet rs, int arg1) throws SQLException {
			Talk data = new Talk();
			data.setId(rs.getInt("id"));
			data.setAid(rs.getInt("aid"));
			data.setTid(rs.getInt("tid"));
			data.setTalk(rs.getString("talk"));
			data.setHref(rs.getString("href"));
			data.setName(rs.getString("name"));
			data.setToHref(rs.getString("toHref"));
			data.setToName(rs.getString("toName"));
			data.setTime(sdf.format(rs.getTimestamp("time")));
			return data;
		}
	};

	public Integer insertTalk(Talk talk) {
		String sql = "INSERT INTO talk VALUES(NULL,:href,:aid,:name,:tid,:toHref,:toName,:time,:talk) ";
		int count = this.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(talk));
		return count;
	}
	
	public Integer insertMessage(String content) {
		String sql = "INSERT INTO message VALUES(NULL,:content,NOW()) ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("content", content);
		int count = this.getNamedParameterJdbcTemplate().update(sql,paramMap);
		return count;
	}
	
	public List<Article> findQueryString(String title) {
		// 设置参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " SELECT id , 	title,content  FROM   article  WHERE title like :title ";
		paramMap.put("title", "%"+title+"%");
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, queryStringMapper);
	}
	private RowMapper<Article> queryStringMapper = new RowMapper<Article>() {
		public Article mapRow(ResultSet rs, int arg1) throws SQLException {
			Article data = new Article();	
			data.setId(rs.getInt("id"));
			data.setTitle(rs.getString("title"));			
			data.setContent(rs.getString("content"));
			return data;
		}
	};
	
	//查询留言
	public List<Message> findMessageList() {
		// 设置参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " SELECT 	*  FROM   message";
		
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, messageMapper);
	}
	
	private RowMapper<Message> messageMapper = new RowMapper<Message>() {
		public Message mapRow(ResultSet rs, int arg1) throws SQLException {
			Message data = new Message();
			data.setId(rs.getInt("id"));
			data.setContent(rs.getString("content"));
			data.setTime(sdf.format(rs.getTimestamp("time")));
			return data;
		}
	};
	
	public List<Recharge> findRecharge() {
		// 设置参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = " SELECT 	*  FROM   recharge";
		
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, rechargeMapper);
	}
	
	private RowMapper<Recharge> rechargeMapper = new RowMapper<Recharge>() {
		public Recharge mapRow(ResultSet rs, int arg1) throws SQLException {
			Recharge data = new Recharge();
			data.setId(rs.getInt("id"));
			data.setTitle(rs.getString("title"));
			data.setToBig(rs.getString("toBig"));
			data.setToSmall(rs.getString("toSmall"));
			data.setImg(rs.getString("img"));
			data.setSmallImg(rs.getString("smallImg"));
			data.setPercent(rs.getInt("percent"));			
			return data;
		}
	};

}
