package cn.secure.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.secure.dao.BlogHomeDao;
import cn.secure.entity.Announcement;
import cn.secure.entity.Article;
import cn.secure.entity.Message;
import cn.secure.entity.Recharge;
import cn.secure.entity.Talk;
import cn.secure.entity.WxEvent;
import cn.secure.service.BlogHomeService;

@Service("BlogsHomeService")
public class BlogHomeServiceImp implements BlogHomeService {

	@Resource
	private BlogHomeDao blogHomeDao;

	@Override
	public List<Article> findArticleList(Integer id,Integer falge,Integer size) {
		String tag = "";
		switch (falge) {
		case 3:
			tag = "前端";
			break;
		case 4:
			tag = "算法";
			break;
		case 5:
			tag = "架构";
			break;
		case 7:
			tag = "生活琐事";
			break;
		case 8:
			tag = "心灵鸡汤";
			break;
		case 9:
			tag = "天马行空";
			break;
		default:
			tag = "";
			break;
		}
		return blogHomeDao.findArticleList(id,tag,size);
	}

	@Override
	public List<Article> findArticleList(String title) {
		return blogHomeDao.findArticleList(title);
	}

	@Override
	public Boolean updateReadNum(Integer id) {
		int count = blogHomeDao.updateReadNum(id);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Boolean updateLoveNum(Integer id) {
		int count = blogHomeDao.updateLoveNum(id);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public List<Talk> findTalkList(Integer id) {
		List<Talk> talkList = blogHomeDao.findTalkList(id);
		Iterator<Talk> it = talkList.iterator();
		while (it.hasNext()) {
			Talk item = it.next();
			// 是回复其他评论的
			if (item.getTid() != 0) {
				for (Talk ele : talkList) {
					if (ele.getTid() == 0 && ele.getId() == item.getTid())
						// 添加到该评论的回复中
						ele.addReplyTalk(item);
					
				}
				it.remove();
			}
		}
		return talkList;
	}

	@Override
	public Boolean insertTalk(Talk talk) {
		Integer count = blogHomeDao.insertTalk(talk);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public List<Article> findQueryString(String title) {
		return blogHomeDao.findQueryString(title);
	}

	@Override
	public List<Message> findMessageList() {
		return blogHomeDao.findMessageList();
	}

	@Override
	public Boolean insertMessage(String content) {
		 Integer count = blogHomeDao.insertMessage(content);
		 if (count > 0)
				return true;
			return false;
	}

	@Override
	public List<Recharge> findRecharge() {
		return blogHomeDao.findRecharge();
	}

	@Override
	public List<Announcement> findAnnouncement() {
		return blogHomeDao.findAnnouncement();
	}
	
	@Override
	public List<WxEvent> findWXLatest() {
		return blogHomeDao.findWXLatest();
	}
	@Override
	public List<WxEvent> findWXTitle(int id) {
		return blogHomeDao.findWXTitle(id);
	}
	@Override
	public Boolean insertWxEvent(String title, String content) {
		int count = blogHomeDao.insertWxEvent(title, content);
		return count > 0 ;
	}
	@Override
	public List<WxEvent> findWXNext(int curr) {
		return blogHomeDao.findWXNext(curr) ;
	}


}
