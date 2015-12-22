package com.name.project.common.web.core;

import javax.servlet.http.Cookie;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月5日 下午9:24:22 
 *
 */
public class CookieManager {
	public Cookie getCookieValue(Cookie[] cookies, String name) {
		if (cookies == null)
			return null;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name))
				return cookie;
		}

		return null;
	}
}
