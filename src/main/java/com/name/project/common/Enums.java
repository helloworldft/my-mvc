package com.name.project.common;
/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月4日 上午9:30:44 
 *
 */
public class Enums {
	//系统类别
	public static enum Category {
		用户管理("用户管理");
		
		private String category;
		
		private Category(String category) {
			this.category = category;
		}
		
		public String getCategory() {
			return this.category;
		}
	}
}
