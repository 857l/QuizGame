package ru.n857l.quizgame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.n857l.quizgame.core.QuizApp
import ru.n857l.quizgame.databinding.FragmentGameOverBinding
import ru.n857l.quizgame.game.NavigateToGame

class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: GameOverViewModel =
            (requireActivity().application as QuizApp).gameOverViewModel

        binding.statsTextView.update(viewModel.statsUiState())

        binding.newGameButton.setOnClickListener {
            (requireActivity() as NavigateToGame).navigateToGame()
        }

        viewModel.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}