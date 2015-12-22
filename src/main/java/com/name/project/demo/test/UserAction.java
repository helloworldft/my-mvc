package com.name.project.demo.test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月8日 下午1:51:24 
 *
 */
@Controller
public class UserAction {
	public void queryUsers(){

        System.out.println("查询所有用户【all users list】");
    }

    public static void main(String[] args) {

        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        /*UserAction userAction = (UserAction)ctx.getBean("userAction");
        userAction.queryUsers();*/

        //ctx.destroy();
        
       /* ClassPathResource res = new ClassPathResource("spring-context.xml");
        
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        
        reader.loadBeanDefinitions(res);*/
        
        FileSystemXmlApplicationContext ctx1 = new FileSystemXmlApplicationContext("classpath:spring-test.xml");
        
        UserAction userAction = (UserAction)ctx1.getBean("userAction");
        
        Class<UserAction> obj = (Class<UserAction>)ctx1.getType("userAction");
        
        userAction.queryUsers();
        
    }
}
