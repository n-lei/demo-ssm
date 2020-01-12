package system.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import system.dao.DataDictionaryMapper;
import system.entity.DataDictionary;
import system.service.DataDictionaryService;

import java.util.List;

@Scope("prototype")
@Service
public class DataDictionaryServiceImp implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> getList() {
        return null;
    }

    @Override
    public void save(DataDictionary dataDictionary) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public DataDictionary getInfoById(String id) {
        return dataDictionaryMapper.getInfoById(id);
    }

    @Override
    public DataDictionary getInfoByParnetAndValue(String parent, String value) {
        return dataDictionaryMapper.getInfoByParnetAndValue(parent, value);
    }

    @Override
    public boolean update(DataDictionary dataDictionary) {
        return false;
    }

    public static void main(String[] args){
        System.out.println(1);
        DataDictionaryServiceImp di = new DataDictionaryServiceImp();
        DataDictionary d = di.getInfoById("100101");
    }
}