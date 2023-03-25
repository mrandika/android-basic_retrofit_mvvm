package space.mrandika.md32studygroup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory()
                }
            }

            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Tambahkan ViewModel lain jika perlu disini...
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel!")
    }
}