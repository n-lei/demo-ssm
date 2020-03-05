package springMvc.config;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import springMvc.enums.RespBodyEnum;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: niel
 * @Description: 反馈json对象 数据类型转换
 **/
public class FastJsonConfigRespDataType extends FastJsonConfig{

    public FastJsonConfigRespDataType(){
        super();
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class,ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE,ToStringSerializer.instance);
        this.setSerializeConfig(serializeConfig);
    }

}
