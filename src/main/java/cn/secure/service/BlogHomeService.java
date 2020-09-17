package cn.secure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.secure.entity.Announcement;
import cn.secure.entity.Article;
import cn.secure.entity.Message;
import cn.secure.entity.Recharge;
import cn.secure.entity.Talk;
import cn.secure.entity.WxEvent;

@Service
public interface BlogHomeService {
	// 查询文章
	List<Article> findArticleList(Integer id, Integer falge, Integer size);

	List<Article> findArticleList(String title);

	// 修改阅读数
	Boolean updateReadNum(Integer id);

	// 修改阅读数
	Boolean updateLoveNum(Integer id);

	// 查询评论
	List<Talk> findTalkList(Integer id);

	// 添加评论
	Boolean insertTalk(Talk talk);

	// 联系搜索
	List<Article> findQueryString(String title);

	// 查询留言
	List<Message> findMessageList();

	// 插入评论
	Boolean insertMessage(String content);
	
	//查询学习资料
	List<Recharge> findRecharge();
	//查询公告栏
	List<Announcement> findAnnouncement();
	
	
	List<WxEvent> findWXLatest() ;
	List<WxEvent> findWXTitle(int id);
	Boolean insertWxEvent(String title,String content); 
	
	List<WxEvent> findWXNext(int curr) ;

}
