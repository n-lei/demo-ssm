package system.utils;

import nl.spring.util.SpringBeanUtil;
import system.entity.DataDictionary;
import system.service.DataDictionaryService;

public class DataDictionaryUtil {

    /**
     * 查找数据字典值
     * @param parent 字典编码
     * @param value 值
     */
    public static String getName(String parent, String value){
        DataDictionaryService dataDictionaryService =
                SpringBeanUtil.getBean("dataDictionaryServiceImp", DataDictionaryService.class);
//        System.out.println("------------[dataDictionaryService]:" + dataDictionaryService);
        DataDictionary dataDictionary = dataDictionaryService.getInfoByParnetAndValue(parent, value);
        if(dataDictionary == null) {
            return "";
        } else {
            //System.out.println("------------[dataDictionary]:" + dataDictionary);
            //System.out.println("=========" + dataDictionary.getName() + "=========");
            return dataDictionary.getName();
        }
    }
}