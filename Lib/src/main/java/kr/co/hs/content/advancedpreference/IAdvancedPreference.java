package kr.co.hs.content.advancedpreference;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * 생성된 시간 2017-02-16, Bae 에 의해 생성됨
 * 프로젝트 이름 : AdvancedPreference
 * 패키지명 : kr.co.hs.content.advancedpreference
 */

public interface IAdvancedPreference {
    IAdvancedPreference set(String key, String value);
    IAdvancedPreference set(String key, int value);
    IAdvancedPreference set(String key, long value);
    IAdvancedPreference set(String key, boolean value);
    IAdvancedPreference set(String key, float value);
    IAdvancedPreference set(String key, Set<String> value);

    boolean commit();
    String getString(String key, String def);
    int getInt(String key, int def);
    long getLong(String key, long def);
    boolean getBoolean(String key, boolean def);
    float getFloat(String key, float def);
    Set<String> getStringSet(String key, Set<String> def);

    Map<String,?> getAll();
    IAdvancedPreference syncCache();
    IAdvancedPreference clearCache();
    Map<String,Object> getCacheDataMap();

    void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);
    void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);
}
