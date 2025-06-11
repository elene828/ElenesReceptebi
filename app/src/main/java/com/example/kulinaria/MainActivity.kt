package com.example.kulinaria

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kulinaria.R
import com.example.kulinaria.databinding.ActivityMainBinding
import com.example.kulinaria.models.recepti
import com.example.kulinaria.recyclerview.kulinarirecviewadapter
import com.example.kulinaria.recyclerview.kulinarirecviewint

class MainActivity : AppCompatActivity(), kulinarirecviewint {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:kulinarirecviewadapter
    private var receptidata: ArrayList<recepti> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        receptidata.add(recepti("ხინკალი", "500 გრ ფქვილი\n" +
                                            "\n" +
                                            "250 მლ წყალი\n" +
                                            "\n" +
                                            "500 გრ საქონლის ხორცი (მოზელილი)\n" +
                                            "\n" +
                                            "მარილი, წიწაკა:"))
        receptidata.add(recepti("ხარჩო", "500 გრ საქონლის ხორცი\n" +
                                            "\n" +
                                            "150 გრ ბრინჯი\n" +
                                            "\n" +
                                            "2 ხახვი\n" +
                                            "\n" +
                                            "3 კბილი ნიორი\n" +
                                            "\n" +
                                            "ქინძი, ტყემალი, წიწაკა:"))
        receptidata.add(recepti("ბორში", "300 გრ წითელი ჭარხალი\n" +
                                        "\n" +
                                        "200 გრ კარტოფილი\n" +
                                        "\n" +
                                        "1 სტაფილო\n" +
                                        "\n" +
                                        "1 ხახვი\n" +
                                        "\n" +
                                        "500 გრ ხორცი\n" +
                                        "\n" +
                                        "სმეტანა\n" +
                                        "\n:"))
        receptidata.add(recepti("ლობიო", "300 გრ ლობიო\n" +
                                        "\n" +
                                        "1 ხახვი\n" +
                                        "\n" +
                                        "2 კბილი ნიორი\n" +
                                        "\n" +
                                        "ქინძი, ქინძი\n" +
                                        "\n" +
                                        "მარილი, ზეთი:"))
        receptidata.add(recepti("მწვადი ", "1 კგ საქონლის ხორცი\n" +
                                            "\n" +
                                            "მარილი, წიწაკა, ძმარი:"))
        receptidata.add(recepti("ცეზარის სალათი", "1 თავი რბილი სალათი (რომანოს ტიპი)\n" +
                                                    "\n" +
                                                    "200 გრ ქათმის ფილე\n" +
                                                    "\n" +
                                                    "პურიანი კრამი\n" +
                                                    "\n" +
                                                    "პარმეზანის ყველი\n" +
                                                    "\n" +
                                                    "ცეზარის სოუსი:"))


        adapter = kulinarirecviewadapter(
            this, this, receptidata)


        binding.recycleview.adapter = adapter
        binding.recycleview.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onClick(recepti: recepti) {
        val intent = Intent(this@MainActivity, receptiactivity::class.java)
        // Putting genres inside a single string

        intent.putExtra("saxeli", recepti.title)
        intent.putExtra("recepti", recepti.info)
        //intent.putExtra("IMAGE", game.image)

        startActivity(intent)
        }
}