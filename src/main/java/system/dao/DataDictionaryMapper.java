package system.dao;

import org.apache.ibatis.annotations.Param;
import system.entity.DataDictionary;

public interface DataDictionaryMapper {

    /**
     * 获取一条数据字典信息
     * @param id
     * @return
     */
    DataDictionary getInfoById(String id);

    /**
     * 获取一条数据字典信息
     * @param parent 字典编码
     * @param value 值
     * @return
     */
    DataDictionary getInfoByParnetAndValue(@Param("parent") String parent, @Param("value") String value);

}