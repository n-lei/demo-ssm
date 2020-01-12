//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nl.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    // spring容器中配置bean之后，会在项目启动的时候给applicationContext赋值
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println("--------======== SpringBeanUtil 初始化 ========--------" + context);
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> clazz) {
//        System.out.println("------------------------------SpringBeanUtil.getBean(class)：" + applicationContext);
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
//        System.out.println("------------------------------SpringBeanUtil.getBean(name, class)：" + applicationContext);
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }
}