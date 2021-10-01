package com.icebeal.core.di

import android.os.Build
import androidx.room.Room
import com.icebeal.core.BuildConfig
import com.icebeal.core.data.local.LocalDataSource
import com.icebeal.core.data.local.MovieDb
import com.icebeal.core.data.remote.ApiService
import com.icebeal.core.data.remote.RemoteDataSource
import com.icebeal.core.data.repository.IMovieRepository
import com.icebeal.core.data.repository.MovieRepository
import com.icebeal.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {

    factory { get<MovieDb>().movieDao() }

    single {

        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSWORD.toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            MovieDb::class.java,
            "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    }

}

val networkModule = module {

    single {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {

            val hostName = BuildConfig.BASE_URL
            val certificatePinner = CertificatePinner.Builder()
                .add(hostName, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
                .add(hostName, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
                .add(hostName, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
                .build()

            okHttpClient.certificatePinner(certificatePinner)

        }

        okHttpClient.build()

    }

    single {
        Retrofit.Builder()
            .baseUrl("https://${BuildConfig.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }

}

val repositoryModule = module {

    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }

    single<IMovieRepository> { MovieRepository(get(), get(), get()) }

}