package nl.hva.madlevel2

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
    }

    private fun initAdapter() {
        val itemList = mutableListOf(
                City(R.drawable.img1_yes_denmark, "Denmark", true),
                City(R.drawable.img2_no_canada, "Canada", false),
                City(R.drawable.img3_no_bangladesh, "Bangladesh", false),
                City(R.drawable.img4_yes_kazachstan, "Kazachstan", false),
                City(R.drawable.img5_no_colombia, "Colombia", false),
                City(R.drawable.img6_yes_poland, "Poland", true),
                City(R.drawable.img7_yes_malta, "Malta", true),
                City(R.drawable.img8_no_thailand, "Thailand", false)
        )
        val adapter = MainAdapter(itemList) {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        ItemTouchHelper(SwipeToDeleteCallback(this, adapter)).attachToRecyclerView(recyclerView)
    }

}

data class City(val id: Int, val name: String, val inEurope: Boolean)

class SwipeToDeleteCallback(private val context: Context, private val adapter: MainAdapter) :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val swipedItem = adapter.itemList[position]
        adapter.deleteItem(position)

        if ((direction == ItemTouchHelper.LEFT && swipedItem.inEurope) ||
                (direction == ItemTouchHelper.RIGHT && !swipedItem.inEurope)) {
            Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "False...", Toast.LENGTH_SHORT).show()
        }
    }

}