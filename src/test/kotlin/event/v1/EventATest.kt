package event.v1

import address.v1.State
import event.event_a.v1.eventA
import event.event_a.v2.EventA
import org.junit.jupiter.api.Test

class EventATest {
    @Test
    fun `create a message for event A v1 using the Kotlin DSL`() {
        val eventA = eventA {
            foo = "foo"
            state = State.STATE_MINNESOTA
        }
    }

    @Test
    fun `create a message for event A v2 using the java builder`() {
        val eventA = EventA.newBuilder()
            .setFoo(2)
            .setState(State.STATE_MINNESOTA)
            .build()
    }
}
