package system.service.imp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pub.spring.SpringPubTest;
import system.entity.DataDictionary;
import system.service.DataDictionaryService;

import java.util.List;

public class DataDictionaryServiceImpTest extends SpringPubTest {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Test
    public void getList() {
        List<DataDictionary> list = dataDictionaryService.getList();
        System.out.println(list);
    }

    @Test
    public void save() {
        System.out.println(1);
    }

    @Test
    public void delete() {
    }

    @Test
    public void getInfoById() {
        DataDictionary dataDictionary = dataDictionaryService.getInfoById("1001");
        System.out.println(dataDictionary);
    }

    @Test
    public void getInfoByParnetAndValue() {
        DataDictionary dataDictionary = dataDictionaryService.getInfoByParnetAndValue("1001", "1");
        System.out.println(dataDictionary);
        System.out.println(dataDictionary.getName());
    }

    @Test
    public void update() {
    }
}