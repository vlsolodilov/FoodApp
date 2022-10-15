package com.solodilov.foodapp.presentation.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.solodilov.foodapp.App
import com.solodilov.foodapp.R
import com.solodilov.foodapp.databinding.FragmentMenuBinding
import javax.inject.Inject

class MenuFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MenuViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }

    private var _binding: FragmentMenuBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding is null")

    private var mealAdapter: MealAdapter? = null
    private var bannerAdapter: BannerAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        initViews()
        observeViewModel()

        return binding.root
    }

    private fun initViews() {
        bannerAdapter = BannerAdapter()
        binding.bannerList.adapter = bannerAdapter
        bannerAdapter?.submitList(listOf(
                R.drawable.banner,
                R.drawable.banner_reverse,
                R.drawable.banner,
            ))
        mealAdapter = MealAdapter()
        binding.mealList.adapter = mealAdapter

        binding.errorLayout.tryButton.setOnClickListener { viewModel.loadMealList() }

        resources.getStringArray(R.array.category).forEach { addChip(it) }
        val chipGroup = binding.groupChipsCategory
        chipGroup.check(chipGroup.getChildAt(0).id)

        val  adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            resources.getStringArray(R.array.city_name)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.citySpinner.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
    }

    private fun processState(state: MenuScreenState) {
        when (state) {
            is MenuScreenState.Loading -> renderLoadingState()
            is MenuScreenState.Content -> renderContentState(state)
            is MenuScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState(contentState: MenuScreenState.Content) {
        mealAdapter?.submitList(contentState.content)
        toggleState(
            isLoading = false,
            isContent = true,
            isError = false,
        )
    }

    private fun renderErrorState() {
        toggleState(
            isLoading = false,
            isContent = false,
            isError = true,
        )
    }

    private fun addChip(name: String) {
        val chip = layoutInflater.inflate(
            R.layout.single_chip_layout,
            binding.groupChipsCategory,
            false
        ) as Chip
        chip.text = name
        binding.groupChipsCategory.addView(chip)
    }

    private fun toggleState(isLoading: Boolean, isContent: Boolean, isError: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.mealList.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    override fun onDestroyView() {
        bannerAdapter = null
        mealAdapter = null
        _binding = null
        super.onDestroyView()
    }
}