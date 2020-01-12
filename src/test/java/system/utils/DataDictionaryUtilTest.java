package system.utils;

import org.junit.Test;
import pub.spring.SpringPubTest;

public class DataDictionaryUtilTest extends SpringPubTest {

   @Test
    public void getName1() {
        System.out.println(DataDictionaryUtil.getName("1001", "1"));
        System.out.println(DataDictionaryUtil.getName("1001", "3"));
        System.out.println(DataDictionaryUtil.getName("1001", ""));
    }
}