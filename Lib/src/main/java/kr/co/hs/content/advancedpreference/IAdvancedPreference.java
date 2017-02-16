package kr.co.hs.content.advancedpreference;

import java.util.Map;
import java.util.Set;

/**
 * 생성된 시간 2017-02-16, Bae 에 의해 생성됨
 * 프로젝트 이름 : AdvancedPreference
 * 패키지명 : kr.co.hs.content.advancedpreference
 */

public interface IAdvancedPreference {
    void set(String key, String value);
    void set(String key, int value);
    void set(String key, long value);
    void set(String key, boolean value);
    void set(String key, float value);
    void set(String key, Set<String> value);

    boolean commit();
    String getString(String key, String def);
    int getInt(String key, int def);
    long getLong(String key, long def);
    boolean getBoolean(String key, boolean def);
    float getFloat(String key, float def);
    Set<String> getStringSet(String key, Set<String> def);

    Map<String,?> getAll();
    void syncCache();
    void clearCache();
    Map<String,Object> getCacheDataMap();
}
