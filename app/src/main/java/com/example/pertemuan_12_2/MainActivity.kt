package com.example.pertemuan_12_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan_12_2.databinding.ActivityMainBinding
import com.example.pertemuan_12_2.model.DataModel
import com.example.pertemuan_12_2.model.ResultItem
import com.example.pertemuan_12_2.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var pahlawanAdapter: PahlawanAdapter
    private val listPahlawan = ArrayList<ResultItem>() // Deklarasi item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        manager = GridLayoutManager(this@MainActivity,2)

        // Set up RecyclerView with ItemAdapter
        pahlawanAdapter = PahlawanAdapter(this@MainActivity, listPahlawan) { clickedData ->
            // Handle item click event if needed
            Toast.makeText(this, "Clicked: ${clickedData.title}", Toast.LENGTH_SHORT).show()
        }

        binding.layoutRV.layoutManager = manager
        binding.layoutRV.adapter = pahlawanAdapter

        // Fetch data from the API
        fetchData()
    }

    private fun fetchData() {
        val client = ApiClient.getInstance()
        val response = client.getDataData()

        response.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    val thisResult = response.body()
                    val datas = thisResult?.result?: emptyList()

                    // Update the list directly
                    listPahlawan.clear()
                    listPahlawan.addAll(datas.filterNotNull())

                    // Notify the adapter that the data has changed
                    pahlawanAdapter.notifyDataSetChanged()
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(
                        this@MainActivity, "Error Response", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                // Handle failure
                Toast.makeText(
                    this@MainActivity, "Error Connection", Toast.LENGTH_LONG).show()
            }
        })
    }
}