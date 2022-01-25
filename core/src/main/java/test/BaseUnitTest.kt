package test

import org.junit.Rule
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
open class BaseUnitTest {

    @Rule
    @JvmField
    val injectMocks = TestRule { statement, _ ->
        MockitoAnnotations.openMocks(this)
        statement
    }
}