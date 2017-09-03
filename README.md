# android-oss-page
Adds OSS page to your application.

# How to use

1 extends `OSSListFragment` and implement `getLibraries` method.

```
class MyOSSListFragment : OSSListFragment() {
    override fun getLibraries(): List<OSSLibrary> {
        return listOf(
                OSSLibrary("Lib1", "oss_0.txt"),
                OSSLibrary("Lib2", "oss_1.txt"))
    }
}
```

2 put license file(oss_0.txt and oss_1.txt for above example) on `src/main/assets` folder.

3 put `MyOSSListFragment` on your layout! 

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.add(R.id.container, MyOSSListFragment())

            transaction.commit()
        }
    }
}
```
