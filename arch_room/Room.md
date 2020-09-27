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



### Q& A

Q1

错误: Cannot figure out how to save this field into database. You can consider adding a type converter for it. - children in com.vaulert.lib_common.database.entities.Article错误: Cannot figure out how to read this field from a cursor. - children in com.vaulert.lib_common.database.entities.Article
FAILURE: Build failed with an exception.

Execution failed for task ':lib_network:kaptDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptExecution
   > java.lang.reflect.InvocationTargetException (no error message)

A1
Room不支持直接存储列表的功能,或者包含不支持的类型字段:Any,List,一般会提示哪个字段出现问题,通过@TypeConverters自定义类型解决

---

Q2
Room 数据库限制存储数据的个数,例如:只存入10条数据

