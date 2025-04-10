package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean SingletonBean = ac.getBean(SingletonBean.class);
        SingletonBean SingletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("SingletonBean = " + SingletonBean);
        System.out.println("SingletonBean2 = " + SingletonBean2);
        assertThat(SingletonBean).isSameAs(SingletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
