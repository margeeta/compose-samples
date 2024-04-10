package com.example.jetcaster.core.player

import com.example.jetcaster.core.model.PlayerEpisode
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Duration

class MockEpisodePlayerTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockEpisodePlayer = MockEpisodePlayer(testDispatcher)
    private val testEpisodes = listOf(
        PlayerEpisode(
            uri = "uri1",
            duration = Duration.ofSeconds(60)
        ),
        PlayerEpisode(
            uri = "uri2",
            duration = Duration.ofSeconds(60)
        ),
        PlayerEpisode(
            uri = "uri3",
            duration = Duration.ofSeconds(60)
        ),
    )

    @Test
    fun whenNextQueueEmpty_doesNothing() {
        val episode = testEpisodes[0]
        mockEpisodePlayer.currentEpisode = episode
        mockEpisodePlayer.play()

        mockEpisodePlayer.next()

        assertEquals(episode, mockEpisodePlayer.currentEpisode)
    }

    @Test
    fun whenNextQueueNotEmpty_removeFromQueue() {
        val episode = PlayerEpisode()
        mockEpisodePlayer.currentEpisode = episode
        testEpisodes.forEach { mockEpisodePlayer.addToQueue(it) }
        mockEpisodePlayer.play()

        mockEpisodePlayer.next()

        assertEquals(testEpisodes.first(), mockEpisodePlayer.currentEpisode)
    }

    @Test
    fun whenNextQueueNotEmpty_notRemovedFromQueue() {
    }

    @Test
    fun whenPreviousQueueEmpty_resetSameEpisode() {
    }

    @Test
    fun whenPreviousQueueNotEmpty_differentEpisode() {
    }
}
