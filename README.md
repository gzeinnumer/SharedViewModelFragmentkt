# SharedViewModelFragment
 pengganti bundle
 
 - [ViewBinding](https://github.com/gzeinnumer/ViewBindingExample)
 - [SharedViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=id#sharing)
 - [KTX ViewModel](https://github.com/gzeinnumer/ViewModelLiveDataExampleKT)

 - `Gradel`
 ```gradle
def lifecycle_version = "2.2.0"
implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
```

- `SharedVM.kt`
```kotlin
class SharedVM : ViewModel(){

    val selected = MutableLiveData<String>()

    fun select(s: String) {
        selected.value = s
    }

}
```

- `MainActivity.kt`
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
    }

    private fun initFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment: Fragment = SecondFragment()
        fragmentTransaction.replace(R.id.fr_2, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
```

- `FirstFragment.kt`
```kotlin
class FirstFragment : Fragment() {

    lateinit var model: SharedVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(activity!!)[SharedVM::class.java]
    }

    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFr1.setOnClickListener { v -> model.select(binding.tvFr1.text.toString()) }
    }
}
```

- `SecondFragment.kt`
```kotlin
class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }

    private lateinit var model: SharedVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProvider(activity!!)[SharedVM::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.selected.observe(this, Observer { s ->
            binding.tvFr2.text = s
        })

    }
}
```
---

```
Copyright 2020 M. Fadli Zein
```
