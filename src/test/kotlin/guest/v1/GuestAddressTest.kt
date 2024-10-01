package guest.v1

import address.v1.State
import build.buf.protovalidate.Validator
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GuestAddressTest {
    @Test
    fun `create a message using the Kotlin DSL`() {
        val address = guestAddress {
            addressLine += "1 Main Street"
            city = "Minneapolis"
            state = State.STATE_MINNESOTA
            zipCode = "55401"
        }

        address.city shouldBe "Minneapolis"
    }

    @Test
    fun `instantiate an event using Java builder`() {
        val address = GuestAddress.newBuilder()
            .addAllAddressLine(listOf("1 Main Street"))
            .setCity("Minneapolis")
            .setState(State.STATE_MINNESOTA)
            .setZipCode("55401")
            .build()

        address.city shouldBe "Minneapolis"
    }

    @Test
    fun `validate guest address`() {
        val address = guestAddress { }

        val validator = Validator()
        val result = validator.validate(address)
        result.violations.size shouldBe 1
        assertSoftly(result.violations[0]) {
            constraintId shouldBe "string.min_len"
            fieldPath shouldBe "city"
        }
    }

}
