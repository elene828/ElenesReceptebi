package com.example.kulinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kulinaria.appi.Rec
import com.example.kulinaria.databinding.ActivityMainBinding
import com.example.kulinaria.models.Recepti
import com.example.kulinaria.recyclerview.KulinariRecViewAdapter
import com.example.kulinaria.recyclerview.KulinariRecViewInt
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class MainActivity : AppCompatActivity(), KulinariRecViewInt {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: KulinariRecViewAdapter
    private var receptiData: ArrayList<Recepti> = ArrayList()

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
                receptiData = response.body() as ArrayList<Recepti>

                adapter = KulinariRecViewAdapter(
                    recyclerViewInterface = this@MainActivity,
                    context = this@MainActivity,
                    destinationModels = receptiData
                )

                binding.recycleview.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recycleview.adapter = adapter

                setupSwipeToDelete()

            } catch (e: IOException) {
                Toast.makeText(this@MainActivity, "Invalid request", Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(this@MainActivity, "Http Exception", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message ?: "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onClick(recepti: Recepti) {
        val intent = Intent(this@MainActivity, Recepti_Activity::class.java).apply {
            putExtra("saxeli", recepti.saxeli)
            putExtra("recepti", recepti.info)
            putExtra("image", recepti.imageUrl)
        }
        startActivity(intent)
    }


    private fun setupSwipeToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                receptiData.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(this@MainActivity, "რეცეპტი წაიშალა", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycleview)
    }
}
