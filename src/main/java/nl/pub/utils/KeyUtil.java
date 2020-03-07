package nl.pub.utils;

public class KeyUtil {

    // 雪花算法对象
    private static SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 返回雪花算法 ID
     */
    public static long nextId(){
        if(snowflakeIdWorker == null){
            snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
        }
        return snowflakeIdWorker.nextId();
    }

    /**
     * 返回雪花算法 ID - String类型
     */
    public static String nextStrId(){
        return String.valueOf(nextId());
    }
}