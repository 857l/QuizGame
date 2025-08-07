package ru.n857l.quizgame

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.n857l.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GameViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var uiState: GameUiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as MyApplication).viewModel

        binding.firstChoiceButton.setOnClickListener {
            uiState = viewModel.chooseFirst()
            uiState.update(binding = binding)
        }
        binding.secondChoiceButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            uiState.update(binding = binding)
        }
        binding.thirdChoiceButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            uiState.update(binding = binding)
        }
        binding.forthChoiceButton.setOnClickListener {
            uiState = viewModel.chooseForth()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            uiState = viewModel.check()
            uiState.update(binding = binding)
        }
        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            uiState.update(binding = binding)
        }

        if (savedInstanceState == null) {
            uiState = viewModel.init()
        } else {
            uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable("uiState", GameUiState::class.java) as GameUiState
            }else{
                savedInstanceState.getSerializable("uiState") as GameUiState
            }
        }

        uiState.update(binding = binding)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("uiState", uiState)
    }

}