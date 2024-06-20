package com.acuon.sampleapp.ui.details

import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acuon.sampleapp.R
import com.acuon.sampleapp.common.BaseFragment
import com.acuon.sampleapp.common.BundleKeys
import com.acuon.sampleapp.databinding.FragmentDetailsBinding
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.ui.details.viewmodel.DetailsViewModel
import com.acuon.sampleapp.utils.extensions.parcelable
import com.acuon.sampleapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun getLayoutId() = R.layout.fragment_details

    private val userItem: UserItem? by lazy { arguments?.parcelable(BundleKeys.USER_DATA) }
    private val viewModel: DetailsViewModel by activityViewModels()

    override fun setupViews() {
        binding.vm = viewModel
        Log.d(BundleKeys.USER_DATA, userItem.toString())
        setUserData()
        binding.apply {
            header.apply {
                tvHeading.text = getString(R.string.user_details_screen)
                ivRightIcon.setImageResource(R.drawable.ic_favorite)
                ivRightIcon.setOnClickListener(clickListener)
                ivRightIcon.show()
                ivLeftIcon.setImageResource(R.drawable.ic_back_arrow)
                ivLeftIcon.setOnClickListener(clickListener)
                ivLeftIcon.show()
            }
            btnUpdate.setOnClickListener(clickListener)
            etName.addTextChangedListener {
                viewModel.name.set(it.toString())
            }
            etPhone.addTextChangedListener {
                viewModel.phone.set(it.toString())
            }
            etEmail.addTextChangedListener {
                viewModel.email.set(it.toString())
            }
            etUsername.addTextChangedListener {
                viewModel.username.set(it.toString())
            }
            etWebsite.addTextChangedListener {
                viewModel.website.set(it.toString())
            }
            etStreet.addTextChangedListener {
                viewModel.street.set(it.toString())
            }
            etSuite.addTextChangedListener {
                viewModel.suite.set(it.toString())
            }
            etCity.addTextChangedListener {
                viewModel.city.set(it.toString())
            }
            etZipcode.addTextChangedListener {
                viewModel.zipcode.set(it.toString())
            }
            etLng.addTextChangedListener {
                viewModel.longitude.set(it.toString())
            }
            etLat.addTextChangedListener {
                viewModel.latitude.set(it.toString())
            }
            etCompanyName.addTextChangedListener {
                viewModel.companyName.set(it.toString())
            }
            etCompanyBs.addTextChangedListener {
                viewModel.companyBs.set(it.toString())
            }
            etCompanyCatchphrase.addTextChangedListener {
                viewModel.companyCatchphrase.set(it.toString())
            }
        }
    }

    private fun setUserData() {
        binding.apply {
            etName.setText(userItem?.name)
            etPhone.setText(userItem?.phone)
            etEmail.setText(userItem?.email)
            etWebsite.setText(userItem?.website)
            etUsername.setText(userItem?.username)
            etStreet.setText(userItem?.street)
            etSuite.setText(userItem?.suite)
            etZipcode.setText(userItem?.zipcode)
            etCity.setText(userItem?.phone)
            etLat.setText(userItem?.latitude)
            etLng.setText(userItem?.longitude)
            etCompanyBs.setText(userItem?.companyBs)
            etCompanyName.setText(userItem?.companyName)
            etCompanyCatchphrase.setText(userItem?.companyCatchPhrase)
        }
    }

    override fun bindViewModel() {

    }

    override fun onViewClicked(view: View?) {
        when (view) {
            binding.header.ivLeftIcon -> {
                findNavController().popBackStack()
            }

            binding.header.ivRightIcon -> {
                findNavController().navigate(R.id.action_detailsFragment_to_favoriteFragment)
            }

            binding.btnUpdate -> {
                if (viewModel.editMode.get()) {
                    viewModel.updateUser()
                    showToast("user updated")
                    findNavController().popBackStack()
                }
                viewModel.setUpdateButton()
            }
        }
    }

}