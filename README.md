# AdvancedPreference
[![](https://jitpack.io/v/hsbaewa/AdvancedPreference.svg)](https://jitpack.io/#hsbaewa/AdvancedPreference)

메모리를 사용하여 성능을 개선시킨 Preference
(Preference that uses memory to improve performance)


gradle setting
<pre><code>
//root build.gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
</code></pre>

<pre><code>
//app build.gradle
dependencies {
    compile 'com.github.hsbaewa:AdvancedPreference:1.0.2'
}
</code></pre>


초기화(initilize)
<pre><code>
SharedPreferences preferences = getSharedPreferences("Test", MODE_PRIVATE);
AdvancedPreference advancedPreference = new AdvancedPreference(preferences);

//or

advancedPreference = new AdvancedPreference(getApplicationContext());
//like this  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//or
advancedPreference = new AdvancedPreference(getApplicationContext(), "name", Context.MODE_PRIVATE);
//like this   preferences = getSharedPreferences("name", Context.MODE_PRIVATE);
</code></pre>

설정값 저장(put data)
<pre><code>
        advancedPreference.set("Key", "Value");
        advancedPreference.commit();
</code></pre>

설정값 불러오기(get data)
<pre><code>
        String data = advancedPreference.getString("Key", "Default");
</code></pre>
