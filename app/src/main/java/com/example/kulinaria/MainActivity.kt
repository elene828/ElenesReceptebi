package com.example.kulinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kulinaria.appi.Rec
import com.example.kulinaria.databinding.ActivityMainBinding
import com.example.kulinaria.models.Recepti
import com.example.kulinaria.recyclerview.kulinarirecviewadapter
import com.example.kulinaria.recyclerview.kulinarirecviewint
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class MainActivity : AppCompatActivity(), kulinarirecviewint {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:kulinarirecviewadapter
    private var receptidata: ArrayList<Recepti> = ArrayList()
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
        Rec.init()

        lifecycleScope.launch {
            try {

                val response = Rec.getrecepti().getrecepti()
                receptidata = response.body() as ArrayList<Recepti>

                adapter = kulinarirecviewadapter(
                    this@MainActivity, this@MainActivity, receptidata)

                binding.recycleview.adapter = adapter
                binding.recycleview.layoutManager = LinearLayoutManager(this@MainActivity)

            } catch (e: IOException) {
                Toast.makeText(this@MainActivity, "Invalid request", Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(this@MainActivity, "Http Exception", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(recepti: Recepti) {
        val intent = Intent(this@MainActivity, Recepti_Activity::class.java)


        intent.putExtra("saxeli", recepti.saxeli)
        intent.putExtra("recepti", recepti.info)
        intent.putExtra("image", recepti.imageUrl)

        startActivity(intent)
        }
}