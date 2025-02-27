package car.exmaple.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationBeanContext.xml");

        Mybean bean = (Mybean) context.getBean("myBean");
        System.out.println(bean);

    }
}
