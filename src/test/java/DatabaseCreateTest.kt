import org.example.db.Database
import org.example.db.GroupDatabase
import org.example.db.ProductDatabase
import org.example.utils.Mock
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseCreateTest {
    private val database = Database("client", "root", "root")
    private val groupDatabase = GroupDatabase(database)
    private val productDatabase = ProductDatabase(database)

    @BeforeAll
    fun setup() {
        database.dropTables()
        database.createTables()
    }

    @Test
    fun `Create three group`() {
        Mock.group.forEachIndexed { index, group ->
            val result = groupDatabase.createGroup(group)
            assert(result.isSuccess)
            assert(result.getOrThrow() == index + 1)
        }
    }

    @Test
    fun `Create four product`() {
        Mock.products.forEachIndexed { index, product ->
            val result = productDatabase.createProduct(product)
            assert(result.isSuccess)
            assert(result.getOrThrow() == index + 1)
        }
    }
}
