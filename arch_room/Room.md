Room

@Dao
 @Query("select * from user")
 @Update
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 @Delete

@Entity(tableName = "user")
 @PrimaryKey

@Database(entities = [User::class], version = 1, exportSchema = true)