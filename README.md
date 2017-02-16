# AdvancedPreference

메모리를 사용하여 성능을 개선시킨 Preference
(Preference that uses memory to improve performance)

초기화(initilize)
<pre><code>
        SharedPreferences preferences = getSharedPreferences("Test", MODE_PRIVATE);
        AdvancedPreference advancedPreference = new AdvancedPreference(preferences);
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