package br.com.lucaspires.tibiainfotracker.di

import androidx.room.Room
import br.com.lucaspires.tibiainfotracker.BuildConfig
import br.com.lucaspires.tibiainfotracker.data.api.TibiaDataService
import br.com.lucaspires.tibiainfotracker.data.api.TibiaDataService.Companion.BASE_URL
import br.com.lucaspires.tibiainfotracker.data.local.dao.FavoriteDAO
import br.com.lucaspires.tibiainfotracker.data.local.database.FavoritesDatabase
import br.com.lucaspires.tibiainfotracker.data.remote.intercepter.ConnectionIntercepter
import br.com.lucaspires.tibiainfotracker.data.repository.RepositoryImp
import br.com.lucaspires.tibiainfotracker.domain.repository.Repository
import br.com.lucaspires.tibiainfotracker.domain.usecase.character.CharacterUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.character.CharacterUseCaseImp
import br.com.lucaspires.tibiainfotracker.domain.usecase.favorite.FavoriteUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.favorite.FavoriteUseCaseImp
import br.com.lucaspires.tibiainfotracker.domain.usecase.highscores.HighscoresUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.highscores.HighscoresUseCaseImp
import br.com.lucaspires.tibiainfotracker.domain.usecase.houses.HousesUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.houses.HousesUseCaseImp
import br.com.lucaspires.tibiainfotracker.domain.usecase.news.NewsUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.news.NewsUseCaseImp
import br.com.lucaspires.tibiainfotracker.domain.usecase.support.SupportUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.support.SupportUseCaseImp
import br.com.lucaspires.tibiainfotracker.domain.usecase.worlds.WorldsUseCase
import br.com.lucaspires.tibiainfotracker.domain.usecase.worlds.WorldsUseCaseImp
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.about.AboutViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.character.CharacterViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.favorite.FavoriteViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.highscore.HighscoreViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses.HouseDetailViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses.HousesViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.news.NewsViewModel
import com.google.firebase.database.FirebaseDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//todo: mudar para dagger hilt ou continuar com koin?(estudo)
internal object AppModules {

    private const val FAVORITE_DATABASE_NAME = "favorite-database"

    private fun dataModule() = module {
        factory {
            OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
                addInterceptor(ConnectionIntercepter(get()))
            }.build()
        }

        factory { FirebaseDatabase.getInstance().reference }
        factory<Repository> { RepositoryImp(get(), get(), get()) }

        single<TibiaDataService> {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
                .create(TibiaDataService::class.java)
        }

        single {
            Room.databaseBuilder(
                androidContext(),
                FavoritesDatabase::class.java,
                FAVORITE_DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }

        fun providesFavoriteDao(db: FavoritesDatabase): FavoriteDAO {
            return db.favoriteDao()
        }

        single { providesFavoriteDao(get()) }
    }

    private fun domainModule() = module {
        factory<CharacterUseCase> { CharacterUseCaseImp(get()) }
        factory<NewsUseCase> { NewsUseCaseImp(get()) }
        factory<WorldsUseCase> { WorldsUseCaseImp(get()) }
        factory<HousesUseCase> { HousesUseCaseImp(get()) }
        factory<HighscoresUseCase> { HighscoresUseCaseImp(get()) }
        factory<FavoriteUseCase> { FavoriteUseCaseImp(get()) }
        factory<SupportUseCase> { SupportUseCaseImp(get()) }
    }

    private fun presentationModule() = module {
        viewModel { CharacterViewModel(get()) }
        viewModel { NewsViewModel(get()) }
        viewModel { HousesViewModel(get(), get()) }
        viewModel { HouseDetailViewModel(get()) }
        viewModel { HighscoreViewModel(get(), get()) }
        viewModel { FavoriteViewModel(get()) }
        viewModel { AboutViewModel(get()) }
    }

    fun getModules() = dataModule() + domainModule() + presentationModule()
}