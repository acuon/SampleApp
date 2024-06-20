package com.acuon.sampleapp.ui.home

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acuon.sampleapp.R
import com.acuon.sampleapp.common.BaseFragment
import com.acuon.sampleapp.common.BundleKeys
import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.databinding.FragmentHomeBinding
import com.acuon.sampleapp.ui.home.viewmodel.HomeViewModel
import com.acuon.sampleapp.utils.extensions.addDecoration
import com.acuon.sampleapp.utils.extensions.createDecorator
import com.acuon.sampleapp.utils.extensions.dp
import com.acuon.sampleapp.utils.extensions.executeWithAction
import com.acuon.sampleapp.utils.extensions.show
import com.acuon.sampleapp.utils.extensions.verticalView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId() = R.layout.fragment_home

    @Inject
    lateinit var usersAdapter: UsersAdapter

    private val viewModel: HomeViewModel by activityViewModels()

    override fun setupViews() {
        binding.apply {
            etSearch.addTextChangedListener {
                viewModel.searchQuery(it.toString())
            }
            header.apply {
                tvHeading.text = getString(R.string.app_name)
                ivRightIcon.setImageResource(R.drawable.ic_favorite)
                ivRightIcon.setOnClickListener(clickListener)
                ivRightIcon.show()
            }
            rcvUsers.apply {
                verticalView(context)
                adapter = usersAdapter
                addDecoration(
                    createDecorator(
                        top = 8.dp,
                        bottom = 8.dp,
                        left = 8.dp,
                        right = 8.dp
                    )
                )
            }
            usersAdapter.onItemClick {
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailsFragment,
                    bundleOf(BundleKeys.USER_DATA to it)
                )
            }
            usersAdapter.onFavoriteClick { item, favorite ->
                if (item?.id != null && favorite != null) {
                    viewModel.addToFavorite(item.id, favorite)
                }
            }
        }
    }

    override fun bindViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {
            when (it) {
                is ResultOf.Success -> {
                    usersAdapter.list = it.value
                    showToast("success")
                }

                is ResultOf.Error -> showToast("error")
                is ResultOf.Loading -> showToast("loading")
            }
            binding.executeWithAction { uiState = HomeUIState(it) }
        }
    }

    override fun onViewClicked(view: View?) {
        when (view) {
            binding.header.ivRightIcon -> {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }
    }

}