package com.canvas.krish.pokemanager.app

import android.content.Context
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Module
class PokeManagerAppModule(private val application: PokeManagerApplication) {

    @Provides
    @ApplicationScoped
    fun provideAwsCredentials(): AWSCredentials {
        return object : AWSCredentials {
            override fun getAWSAccessKeyId(): String = Config.AWS_S3_ACCESS_KEY
            override fun getAWSSecretKey(): String = Config.AWS_S3_SECRET_KEY
        }
    }

    @Provides
    @ApplicationScoped
    fun provideAwsS3Client(awsCredentials: AWSCredentials): AmazonS3 {
        val amazonS3Client: AmazonS3 = AmazonS3Client(awsCredentials)
        amazonS3Client.setRegion(Region.getRegion(Regions.DEFAULT_REGION))

        return amazonS3Client
    }

    @Provides
    @ApplicationScoped
    fun provideApplicationContext(): Context {
        return application
    }
}
