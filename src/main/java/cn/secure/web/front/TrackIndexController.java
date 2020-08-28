
/**  
 * Project Name		batch  
 * File Name		IndexController.java  
 * Package Name		cn.secure.web.front  
 * Date				2018年6月22日下午2:43:08  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 *  
 */  

package cn.secure.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**@Description	TODO
 *
 * @ClassName	IndexController
 * @author		ygc
 * @date		2018年6月22日 下午2:43:08
 * @version		V1.0   
 * @since		JDK 1.8
 */
@Controller
public class TrackIndexController {
	@RequestMapping("/batchRun")
	public ModelAndView batchRun() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("batchRun");
		return mv;
	}
}
  
