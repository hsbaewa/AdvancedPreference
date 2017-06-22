package kr.co.hs.content.advancedpreference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 생성된 시간 2017-02-16, Bae 에 의해 생성됨
 * 프로젝트 이름 : AdvancedPreference
 * 패키지명 : kr.co.hs.content.advancedpreference
 */

public class AdvancedPreference implements IAdvancedPreference{
    private SharedPreferences mSharedPreferences;

    private HashMap<String, Object> mPushDataMap;
    private HashMap<String, Object> mCacheDataMap;

    public AdvancedPreference(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        init();
    }
    public AdvancedPreference(Context context, String name, int mode) {
        mSharedPreferences = context.getSharedPreferences(name, mode);
        init();
    }
    public AdvancedPreference(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
        init();
    }
    private void init(){
        mPushDataMap = new HashMap<>();
        mCacheDataMap = new HashMap<>();
    }


    public SharedPreferences getSharedPreference(){
        return this.mSharedPreferences;
    }


    @Override
    public IAdvancedPreference set(String key, String value) {
        synchronized (this.mPushDataMap){
            this.mPushDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public IAdvancedPreference set(String key, int value) {
        synchronized (this.mPushDataMap){
            this.mPushDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public IAdvancedPreference set(String key, long value) {
        synchronized (this.mPushDataMap){
            this.mPushDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public IAdvancedPreference set(String key, boolean value) {
        synchronized (this.mPushDataMap){
            this.mPushDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public IAdvancedPreference set(String key, float value) {
        synchronized (this.mPushDataMap){
            this.mPushDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public IAdvancedPreference set(String key, Set<String> value) {
        synchronized (this.mPushDataMap){
            this.mPushDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public boolean commit() {
        boolean result;
        synchronized (this.mPushDataMap){
            Iterator<String> iter = this.mPushDataMap.keySet().iterator();
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            while(iter.hasNext()){
                String key = iter.next();
                Object obj = this.mPushDataMap.get(key);
                if(obj == null){
                    edit.putString(key, null);
                }else if(obj instanceof String){
                    edit.putString(key, (String) obj);
                }else if(obj instanceof Integer){
                    edit.putInt(key, (Integer) obj);
                }else if(obj instanceof Long){
                    edit.putLong(key, (Long) obj);
                }else if(obj instanceof Float){
                    edit.putFloat(key, (Float) obj);
                }else if(obj instanceof Boolean){
                    edit.putBoolean(key, (Boolean) obj);
                }else if(obj instanceof Set){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                        edit.putStringSet(key, (Set<String>) obj);
                    }
                }
                this.mCacheDataMap.put(key, obj);
            }
            result = edit.commit();
            if(result){
                mPushDataMap.clear();
            }

        }
        return result;
    }

    @Override
    public String getString(String key, String def) {
        if(this.mCacheDataMap.containsKey(key)){
            //key가 존재한다.
            //object를 가져와본다
            Object obj = this.mCacheDataMap.get(key);
            if(obj == null){
                //캐시는 되어 있지만 값이 null이다 그대로 null을 리턴하자
                return null;
            }else{
                //캐시도 되어있고 null이 아닌 뭔가가 있다. String 타입인지 확인하자
                if(obj instanceof String){
                    //String 타입이므로 그대로 string으로 리턴
                    return (String) obj;
                }else{
                    //String 타입이 아니다? 프리퍼런스처럼 Exception을 발생 시키자
                    throw new ClassCastException("저장된 값이 String 타입이 아닙니다.");
                }
            }
        }else{
            //key가 존재하지 않는다
            //ShaeredPreference에서 확인하자
            if(!this.mSharedPreferences.contains(key)){
                //저장된 프리퍼런스 값도 없다. def를 리턴하자
                return def;
            }else{
                //저장된 프리퍼런스 값이 있다.
                String val = this.mSharedPreferences.getString(key, def);
                //캐시 하고
                this.mCacheDataMap.put(key, val);
                //리턴하자
                return val;
            }
        }
    }

    @Override
    public int getInt(String key, int def) {
        if(this.mCacheDataMap.containsKey(key)){
            //key가 존재한다.
            //object를 가져와본다
            Object obj = this.mCacheDataMap.get(key);
            if(obj instanceof Integer){
                //int 타입이므로 그대로 int 리턴
                return (Integer) obj;
            }else{
                //int 타입이 아니다? 프리퍼런스처럼 Exception을 발생 시키자
                throw new ClassCastException("저장된 값이 Integer 타입이 아닙니다.");
            }
        }else{
            //key가 존재하지 않는다
            //ShaeredPreference에서 확인하자
            if(!this.mSharedPreferences.contains(key)){
                //저장된 프리퍼런스 값도 없다. def를 리턴하자
                return def;
            }else{
                //저장된 프리퍼런스 값이 있다.
                int val = this.mSharedPreferences.getInt(key, def);
                //캐시 하고
                this.mCacheDataMap.put(key, val);
                //리턴하자
                return val;
            }
        }
    }

    @Override
    public long getLong(String key, long def) {
        if(this.mCacheDataMap.containsKey(key)){
            //key가 존재한다.
            //object를 가져와본다
            Object obj = this.mCacheDataMap.get(key);
            if(obj instanceof Long){
                //long 타입이므로 그대로 long 리턴
                return (Long) obj;
            }else{
                //int 타입이 아니다? 프리퍼런스처럼 Exception을 발생 시키자
                throw new ClassCastException("저장된 값이 Long 타입이 아닙니다.");
            }
        }else{
            //key가 존재하지 않는다
            //ShaeredPreference에서 확인하자
            if(!this.mSharedPreferences.contains(key)){
                //저장된 프리퍼런스 값도 없다. def를 리턴하자
                return def;
            }else{
                //저장된 프리퍼런스 값이 있다.
                long val = this.mSharedPreferences.getLong(key, def);
                //캐시 하고
                this.mCacheDataMap.put(key, val);
                //리턴하자
                return val;
            }
        }
    }

    @Override
    public boolean getBoolean(String key, boolean def) {
        if(this.mCacheDataMap.containsKey(key)){
            //key가 존재한다.
            //object를 가져와본다
            Object obj = this.mCacheDataMap.get(key);
            if(obj instanceof Boolean){
                //long 타입이므로 그대로 long 리턴
                return (Boolean) obj;
            }else{
                //int 타입이 아니다? 프리퍼런스처럼 Exception을 발생 시키자
                throw new ClassCastException("저장된 값이 Boolean 타입이 아닙니다.");
            }
        }else{
            //key가 존재하지 않는다
            //ShaeredPreference에서 확인하자
            if(!this.mSharedPreferences.contains(key)){
                //저장된 프리퍼런스 값도 없다. def를 리턴하자
                return def;
            }else{
                //저장된 프리퍼런스 값이 있다.
                boolean val = this.mSharedPreferences.getBoolean(key, def);
                //캐시 하고
                this.mCacheDataMap.put(key, val);
                //리턴하자
                return val;
            }
        }
    }

    @Override
    public float getFloat(String key, float def) {
        if(this.mCacheDataMap.containsKey(key)){
            //key가 존재한다.
            //object를 가져와본다
            Object obj = this.mCacheDataMap.get(key);
            if(obj instanceof Float){
                //long 타입이므로 그대로 long 리턴
                return (Float) obj;
            }else{
                //int 타입이 아니다? 프리퍼런스처럼 Exception을 발생 시키자
                throw new ClassCastException("저장된 값이 Float 타입이 아닙니다.");
            }
        }else{
            //key가 존재하지 않는다
            //ShaeredPreference에서 확인하자
            if(!this.mSharedPreferences.contains(key)){
                //저장된 프리퍼런스 값도 없다. def를 리턴하자
                return def;
            }else{
                //저장된 프리퍼런스 값이 있다.
                float val = this.mSharedPreferences.getFloat(key, def);
                //캐시 하고
                this.mCacheDataMap.put(key, val);
                //리턴하자
                return val;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Set<String> getStringSet(String key, Set<String> def) {
        if(this.mCacheDataMap.containsKey(key)){
            //key가 존재한다.
            //object를 가져와본다
            Object obj = this.mCacheDataMap.get(key);
            if(obj instanceof Set){
                //long 타입이므로 그대로 long 리턴
                return (Set<String>) obj;
            }else{
                //int 타입이 아니다? 프리퍼런스처럼 Exception을 발생 시키자
                throw new ClassCastException("저장된 값이 Set<String> 타입이 아닙니다.");
            }
        }else{
            //key가 존재하지 않는다
            //ShaeredPreference에서 확인하자
            if(!this.mSharedPreferences.contains(key)){
                //저장된 프리퍼런스 값도 없다. null를 리턴하자
                return def;
            }else{
                //저장된 프리퍼런스 값이 있다.
                Set<String> val = this.mSharedPreferences.getStringSet(key, def);
                //캐시 하고
                this.mCacheDataMap.put(key, val);
                //리턴하자
                return val;
            }
        }
    }

    @Override
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    @Override
    public IAdvancedPreference syncCache() {
        Map<String, ?> allDatas = getAll();
        Iterator<String> keyset = allDatas.keySet().iterator();
        if(keyset.hasNext()){
            String key = keyset.next();
            Object value = allDatas.get(key);
            mCacheDataMap.put(key, value);
        }
        return this;
    }

    @Override
    public IAdvancedPreference clearCache() {
        this.mCacheDataMap.clear();
        return this;
    }

    @Override
    public Map<String, Object> getCacheDataMap() {
        return this.mCacheDataMap;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
