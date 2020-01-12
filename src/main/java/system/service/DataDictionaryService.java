package system.service;

import system.entity.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    /**
     * 查询list数据信息
     * @return
     */
    List<DataDictionary> getList();

    /**
     * 保存一条信息
     * @param dataDictionary
     */
    void save(DataDictionary dataDictionary);

    /**
     * 删除一条信息
     * @param id 用户id
     */
    void delete(String id);

    /**
     * 获取一条数据字典信息
     * @param id
     * @return
     */
    DataDictionary getInfoById(String id);

    /**
     * 获取数据字典值
     * @param parent 字典编码
     * @param value 值
     * @return
     */
    DataDictionary getInfoByParnetAndValue(String parent, String value);

    /**
     * 更新信息
     * @param dataDictionary
     */
    boolean update(DataDictionary dataDictionary);

}
