package by.eapp.thegamesearching.di

import android.app.Application
import androidx.room.Room
import by.eapp.thegamesearching.data.local.FavoritesDatabase
import by.eapp.thegamesearching.data.pagination.GamesByGenreSource
import by.eapp.thegamesearching.data.pagination.GamesBySearchingSource
import by.eapp.thegamesearching.data.pagination.GamesSource
import by.eapp.thegamesearching.data.remote.ApiService
import by.eapp.thegamesearching.data.repository.FavoritesRepositoryImpl
import by.eapp.thegamesearching.data.repository.GameDetailRepositoryImpl
import by.eapp.thegamesearching.data.repository.GamesRepositoryImpl
import by.eapp.thegamesearching.domain.repository.FavoritesRepository
import by.eapp.thegamesearching.domain.repository.GameDetailRepository
import by.eapp.thegamesearching.domain.repository.GamesRepository
import by.eapp.thegamesearching.domain.use_cases.AddFavoriteGameUseCase
import by.eapp.thegamesearching.domain.use_cases.DeleteAllGamesUseCase
import by.eapp.thegamesearching.domain.use_cases.DeleteGameUseCase
import by.eapp.thegamesearching.domain.use_cases.GetAllFavoritesUseCase
import by.eapp.thegamesearching.domain.use_cases.GetGameDetailUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfGamesByGenreUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfGamesUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfGenresUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfPlatformsUseCase
import by.eapp.thegamesearching.domain.use_cases.GetListOfSearchingGamesUseCase
import by.eapp.thegamesearching.domain.use_cases.IsFavoriteUseCase
import by.eapp.thegamesearching.domain.use_cases.UseCases
import by.eapp.thegamesearching.utils.Constants.BASE_URL
import by.eapp.thegamesearching.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesDatabase(app: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            app,
            FavoritesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient):
            Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun providesAppService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesFavoritesRepository(db: FavoritesDatabase): FavoritesRepository {
        return FavoritesRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun providesGamesRepository(
        apiService: ApiService,
        gamesSource: GamesSource,
        gamesBySearchingSource: GamesBySearchingSource,
        gamesByGenreSource: GamesByGenreSource,
    ): GamesRepository {
        return GamesRepositoryImpl(
            apiService,
            gamesSource,
            gamesBySearchingSource,
            gamesByGenreSource
        )
    }

    @Singleton
    @Provides
    fun providesGameDetailRepository(apiService: ApiService): GameDetailRepository {
        return GameDetailRepositoryImpl(apiService = apiService)
    }

    @Singleton
    @Provides
    fun providesUseCases(
        gamesByGenre: GetListOfGamesByGenreUseCase,
        allGames: GetListOfGamesUseCase,
        genres: GetListOfGenresUseCase,
        platformsUseCase: GetListOfPlatformsUseCase,
        isFavoriteUseCase: IsFavoriteUseCase,
        deleteAllGamesUseCase: DeleteAllGamesUseCase,
        getAllFavoritesUseCase: GetAllFavoritesUseCase,
        deleteGameUseCase: DeleteGameUseCase,
        searchingGamesUseCase: GetListOfSearchingGamesUseCase,
        getGameDetailUseCase: GetGameDetailUseCase,
        addFavoriteGameUseCase: AddFavoriteGameUseCase
    ): UseCases {
        return UseCases(
            gamesByGenre,
            allGames,
            genres,
            platformsUseCase,
            isFavoriteUseCase,
            deleteAllGamesUseCase,
            getAllFavoritesUseCase,
            deleteGameUseCase,
            searchingGamesUseCase,
            getGameDetailUseCase,
            addFavoriteGameUseCase
        )
    }
}