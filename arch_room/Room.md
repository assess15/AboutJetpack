## Room

**@Dao**

Data Assess Object 数据访问对象

包含增删改查，注意包含耗时操作,需要在子线程中操作

- @Query
- @Update
- @Insert
- @Delete

```Kotlin
@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(vararg student: StudentEntity)
    @Delete
    suspend fun deleteStudent(vararg student: StudentEntity)
    @Update
    suspend fun updateStudent(vararg student: StudentEntity)
    @Query("DELETE FROM student")
    suspend fun deleteAllStudent()
    @Query("SELECT * FROM student ORDER BY id DESC")
    fun queryAllStudent(): LiveData<List<StudentEntity>>
}    
```

---

**@Entity**

```Kotlin
@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "age")
    val age: Int?
)
```

---

**@Database**

标记的类必须是抽象类,完整示例:

```Kotlin
@Database(
    entities = [StudentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}
```

---

**@TypeConverters**

一般Room中的Entity存储的类型不包括,Any,List等类型,需要使用@TypeConverters处理List类型

```
@Entity(tableName = "user")
@TypeConverters(CarConverters::class)
data class User(
    @PrimaryKey
    val userId: String,
    val userName: String,
    val userAge: String,
    val cars: List<Car>
)

data class Car(val name: String, val color: String)
```

```
class CarConverters {

    @TypeConverter
    fun stringToObject(value: String): List<Car> {
        val type = object : TypeToken<List<Car>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun objectToString(list: List<Car>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
```

---



### Room数据迁移

常见需求:

- v1 → v2 增加字段
- v2 → v3 修改原有字段类型/删除原有字段

**表中新增字段:**
例如:

version = 1 

```Kotlin
@Entity(tableName = "User")
@TypeConverters(CarConverters::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    val userId: Int,
    @ColumnInfo(name = "UserName")
    val userName: String,
    @ColumnInfo(name = "UserAge")
    val userAge: String
)
```

version = 2

```kotlin
@Entity(tableName = "User")
@TypeConverters(CarConverters::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    val userId: Int,
    @ColumnInfo(name = "UserName")
    val userName: String,
    @ColumnInfo(name = "UserAge")
    val userAge: String,
   @@ColumnInfo(name = "UserSex")
    val sex: String
)
```

```Kotlin
val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN UserSex TEXT NOT NULL DEFAULT 0")
    }
}
```







---



### Q& A

**Q1**

错误: Cannot figure out how to save this field into database. You can consider adding a type converter for it. - children in com.vaulert.lib_common.database.entities.Article错误: Cannot figure out how to read this field from a cursor. - children in com.vaulert.lib_common.database.entities.Article
FAILURE: Build failed with an exception.

Execution failed for task ':lib_network:kaptDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptExecution
   > java.lang.reflect.InvocationTargetException (no error message)

**A1**
Room不支持直接存储列表的功能,或者包含不支持的类型字段:Any,List,一般会提示哪个字段出现问题,通过@TypeConverters自定义类型解决

---

**Q2**
Room 数据库限制存储数据的个数,例如:只存入10条数据

**A2**

---

**Q3**
为了避免 UI 阻塞，一些比较耗时的操作如 insert ，可以使用 suspend 关键字修饰，然后利用协程在非 UI 线程执行此方法，需要注意的是，带有 LiveData 返回类型的方法 getAllWords() 并没有添加 suspend 关键字，这是因为 suspend 关键字会在编译时自动给方法添加参数导致
Not sure how to convert a Cursor to this method's return type 异常，解决办法可以参考 [issues231](https://github.com/Kotlin/kotlinx.coroutines/issues/231)

**A3**

移除suspend关键字

```Kotlin
@Query("select * from words")
fun getAllWords() : LiveData<List(Word)>
```



---

**Q4**

java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.

**A4**

修改了数据库内容,需要更新数据库版本.例如:修改表名,新增字段

```Kotlin
// 修改version版本号
@Database(entities = [User::class], version = 2, exportSchema = true)
abstract fun UserDatabase:RoomDatabase(){}
```

```Kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `User` (`UserId` INTEGER, `UserName` TEXT,PRIMARY KEY(`UserId`))")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
    }
}
```

```Kotlin
Room.databaseBuilder(context.applicationContext, 
                     UsersDatabase::class.java, "user.db")
// 添加
.addMigrations(MIGRATION_1_2,MIGRATION_2_3)
.build()
```



---

**Q5**

java.lang.IllegalStateException: A migration from 1 to 2 was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.

**A5**

---