package com.example.technical.challenge.presentation.splash

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.filters.MediumTest
import com.example.technical.challenge.R
import com.example.technical.challenge.base.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SplashFragmentTest{

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Test
    fun setup(){
        hiltAndroidRule.inject()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onSplashAction_navigateToProductsListFragment(){
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<SplashFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        runBlocking {
            delay(3000)
        }

        verify(navController).navigate(
            R.id.action_splash_to_products_list
        )
    }

}