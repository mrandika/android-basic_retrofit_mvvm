package space.mrandika.md32studygroup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.mrandika.md32studygroup.R
import space.mrandika.md32studygroup.adapter.UsersAdapter
import space.mrandika.md32studygroup.model.UserResponse
import space.mrandika.md32studygroup.viewmodel.UsersViewModel
import space.mrandika.md32studygroup.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = obtainViewModel(this@MainActivity)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.users_recyclerView)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.users.observe(this) {
            setUsersData(it)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val divider = DividerItemDecoration(this, layoutManager.orientation)
        recyclerView.addItemDecoration(divider)
    }

    private fun showLoading(status: Boolean) {
        progressBar.visibility = if (status) View.VISIBLE else View.INVISIBLE
        recyclerView.visibility = if (status) View.INVISIBLE else View.VISIBLE
    }

    private fun setUsersData(data: UserResponse) {
        val adapter = UsersAdapter(data)
        recyclerView.adapter = adapter
    }

    private fun obtainViewModel(activity: AppCompatActivity): UsersViewModel {
        val factory = ViewModelFactory.getInstance()

        return ViewModelProvider(this, factory)[UsersViewModel::class.java]
    }
}