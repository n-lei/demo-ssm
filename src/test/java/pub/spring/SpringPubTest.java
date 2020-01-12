package pub.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
    "classpath*:spring/applicationContext.xml",
    "classpath*:spring/springmvc.xml"
})
public class SpringPubTest {

    @Before
    public void before(){
        System.out.println("------------ springJunit before ------------");
    }

    @After
    public void after(){
        System.out.println("------------ springJunit after ------------");
    }

}