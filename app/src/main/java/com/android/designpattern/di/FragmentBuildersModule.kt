package com.android.designpattern.di

import com.android.designpattern.ui.home.RepoHome
import com.android.designpattern.ui.home.Home
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): Home

    @ContributesAndroidInjector
    abstract fun contributeContactsFragment(): RepoHome

}
