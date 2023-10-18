package com.wagyufari.alfardan.di

import android.app.Application
import com.wagyufari.alfardan.core.network.Network
import com.wagyufari.alfardan.ui.data.datasource.AuthApi
import com.wagyufari.alfardan.ui.data.datasource.CurrencyApi
import com.wagyufari.alfardan.ui.data.datasource.UserApi
import com.wagyufari.alfardan.ui.data.repositories.AuthRepositoryImpl
import com.wagyufari.alfardan.ui.data.repositories.CurrencyRepositoryImpl
import com.wagyufari.alfardan.ui.data.repositories.UserRepositoryImpl
import com.wagyufari.alfardan.ui.domain.repository.AuthRepository
import com.wagyufari.alfardan.ui.domain.repository.CurrencyRepository
import com.wagyufari.alfardan.ui.domain.repository.UserRepository
import com.wagyufari.alfardan.ui.domain.usecase.auth.AuthUseCases
import com.wagyufari.alfardan.ui.domain.usecase.auth.AuthenticateUserUseCase
import com.wagyufari.alfardan.ui.domain.usecase.auth.RegisterUserUseCase
import com.wagyufari.alfardan.ui.domain.usecase.currency.CurrencyUseCases
import com.wagyufari.alfardan.ui.domain.usecase.currency.GetCurrenciesUseCase
import com.wagyufari.alfardan.ui.domain.usecase.user.GetUsersUseCase
import com.wagyufari.alfardan.ui.domain.usecase.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideRetrofit(context: Application): Retrofit {
        return Network.retrofitClient(context)
    }

    @Provides
    @Singleton
    @Named("baseUrlUser")
    fun provideRetrofitUser(context: Application): Retrofit {
        return Network.retrofitUserClient(context)
    }

    @Provides
    @Singleton
    fun provideAuthApi(@Named("baseUrl") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyApi(@Named("baseUrl") retrofit: Retrofit): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(@Named("baseUrlUser") retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository {
        return UserRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyApi): CurrencyRepository {
        return CurrencyRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCurrencyUseCases(repository: CurrencyRepository): CurrencyUseCases {
        return CurrencyUseCases(
            getCurrenciesUseCase = GetCurrenciesUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            authenticateUserUseCase = AuthenticateUserUseCase(repository),
            registerUserUseCase = RegisterUserUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUsersUseCase = GetUsersUseCase(repository)
        )
    }
}