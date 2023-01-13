package git.myapplication.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import git.myapplication.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertDb()
        updateDb()
        deleteDb()
        getAllDb()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    private fun showTxt(text: String) {
        binding.tvResult.append(text + "\n")
    }

    private fun clearEditTexts() {
        with(binding) {
            etId.setText("")
            etName.setText("")
            etPhone.setText("")
            etEmail.setText("")
        }
    }

    private fun insertDb() {
        binding.btnInsert.setOnClickListener {
            try {
                dbHelper.insertData(
                    binding.etName.text.toString().trim(),
                    binding.etPhone.text.toString().trim(),
                    binding.etEmail.text.toString().trim(),
                )
                clearEditTexts()
                showTxt("Data inserted")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateDb() {
        binding.btnUpdate.setOnClickListener {
            try {
                dbHelper.updateData(
                    binding.etId.text.toString().trim(),
                    binding.etName.text.toString().trim(),
                    binding.etPhone.text.toString().trim(),
                    binding.etEmail.text.toString().trim(),
                )
                showTxt("Data updated")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDb() {
        binding.btnDelete.setOnClickListener {
            try {
                dbHelper.deleteData(binding.etId.text.toString().trim())
                clearEditTexts()
                showTxt("Data deleted")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAllDb() {
        binding.btnView.setOnClickListener {
            try {
                val selectResult = dbHelper.getAllData()
                showTxt(selectResult)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}