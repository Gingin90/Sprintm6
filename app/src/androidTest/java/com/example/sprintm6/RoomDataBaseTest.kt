package com.example.sprintm6

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.sprintm6.data.local.TelefonoDao
import com.example.sprintm6.data.local.TelefonoDataBase
import com.example.sprintm6.data.local.TelefonoEntity

import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var telefonoDao: TelefonoDao
    private lateinit var db: TelefonoDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TelefonoDataBase::class.java).build()
        telefonoDao = db.getTelefonoDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertBreeds_empty() = runBlocking {
        // Given
        val telefonoList = listOf<TelefonoEntity>()

        // When
        telefonoDao.insertTelefono(telefonoList)

        // Then A
        val it = telefonoDao.getTelefonos().getOrAwaitValue()
        assertThat(it).isNotNull() //assertNotEquals(it, null) Son equivalentes
        assertThat(it).isEmpty() //assertEquals(it.size, 0) Son equivalentes

        // Then B
        telefonoDao.getTelefonos().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBreeds_happyCase_1element() = runBlocking {
        // Given
        val telefonoList = listOf(TelefonoEntity(1,"Samsung",1000,"cell_image.jpg"))

        // When
        telefonoDao.insertTelefono(telefonoList)

        // Then
        telefonoDao.getTelefonos().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertBreeds_happyCase_3elements() = runBlocking {
        // Given
        val telefonoList = listOf(
            TelefonoEntity(1,"Samsung",1000,"cell_image1.jpg"),
            TelefonoEntity(2,"SamsungWhite",2000,"cell_image2.jpg"),
            TelefonoEntity(3,"SamsungBlack",3000,"cell_image3.jpg"))

        // When
        telefonoDao.insertTelefono(telefonoList)

        // Then
        telefonoDao.getTelefonos().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}