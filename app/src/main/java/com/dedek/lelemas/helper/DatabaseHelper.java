package com.dedek.lelemas.helper;

import com.dedek.lelemas.model.Food;
import com.dedek.lelemas.model.Inventory;
import com.dedek.lelemas.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "LeleMas";

	// Table Names
	private static final String TABLE_FOOD = "food";
	private static final String TABLE_INVENTORY = "inventory";
	private static final String TABLE_INVENTORY_FOOD = "inventory_food";
	private static final String TABLE_FOODIN = "foodIn";
	private static final String TABLE_FOODOUT = "foodOut";
	private static final String TABLE_FOODREDUCE = "foodReduce";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_TIME = "time";

	// Common column for entity
	private static final String KEY_NAME = "name";
	private static final String KEY_DESC = "desc";

	private static final String KEY_FOOD_MEASURE = "measure";
	private static final String KEY_AMOUNT = "amount";

	// Table Create Statements
	// Todo table create statement
	private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
			+ TABLE_FOOD + "(" + TABLE_FOOD + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
			+ " TEXT," + KEY_DESC + " TEXT" + ")";

	// Food table create statement
	private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE " + TABLE_INVENTORY
			+ "(" + TABLE_INVENTORY + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
			+ KEY_DESC + " TEXT" + ")";

	// inventory_food table create statement
	private static final String CREATE_TABLE_INVENTORY_FOOD = "CREATE TABLE "
			+ TABLE_INVENTORY_FOOD + "(" + TABLE_INVENTORY_FOOD + KEY_ID + " INTEGER PRIMARY KEY,"
			+ TABLE_INVENTORY + KEY_ID + " INTEGER," + TABLE_FOOD + KEY_ID + " INTEGER,"
			+ KEY_AMOUNT + " INTEGER" + ")";

	// foodIn table create statement
	private static final String CREATE_TABLE_FOODIN = "CREATE TABLE "
			+ TABLE_FOODIN + "(" + TABLE_FOODIN + KEY_ID + " INTEGER PRIMARY KEY,"
			+ TABLE_INVENTORY_FOOD + KEY_ID + " INTEGER," + KEY_TIME + " DATETIME,"
			+ KEY_AMOUNT + " INTEGER" + ")";

	// foodOut table create statement
	private static final String CREATE_TABLE_FOODOUT = "CREATE TABLE "
			+ TABLE_FOODOUT + "(" + TABLE_FOODOUT + KEY_ID + " INTEGER PRIMARY KEY,"
			+ TABLE_INVENTORY_FOOD + KEY_ID + " INTEGER," + KEY_TIME + " DATETIME,"
			+ KEY_AMOUNT + " INTEGER" + ")";

	// foodReduce table create statement
	private static final String CREATE_TABLE_FOODREDUCE = "CREATE TABLE "
			+ TABLE_FOODREDUCE + "(" + TABLE_FOODREDUCE + KEY_ID + " INTEGER PRIMARY KEY,"
			+ TABLE_INVENTORY_FOOD + KEY_ID + " INTEGER," + KEY_TIME + " DATETIME,"
			+ KEY_AMOUNT + " INTEGER" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_FOOD);
		db.execSQL(CREATE_TABLE_INVENTORY);
		db.execSQL(CREATE_TABLE_INVENTORY_FOOD);
		db.execSQL(CREATE_TABLE_FOODIN);
		db.execSQL(CREATE_TABLE_FOODOUT);
		db.execSQL(CREATE_TABLE_FOODREDUCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_FOOD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODOUT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODREDUCE);

		// create new tables
		onCreate(db);
	}

	// ------------------------ "todos" table methods ----------------//

	/*
	 * Creating a food
	 */
	public long createFood(Food food) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, food.getName());
		values.put(KEY_DESC, food.getDescription());
		values.put(KEY_FOOD_MEASURE, food.getMeasure());

		// insert row
		long food_id = db.insert(TABLE_FOOD, null, values);

		// insert tag_ids
//		for (long tag_id : tag_ids) {
//			createTodoTag(todo_id, tag_id);
//		}

		return food_id;
	}
	/*
	 * Creating a inventory
	 */
	public long createInventory(Inventory inventory) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, inventory.getName());
		values.put(KEY_DESC, inventory.getDescription());

		// insert row
		long inventory_id = db.insert(TABLE_FOOD, null, values);

		// insert tag_ids
//		for (long tag_id : tag_ids) {
//			createTodoTag(todo_id, tag_id);
//		}

		return inventory_id;
	}

	/*
	 * Creating a Inventory_Food
	 */
	public long createInventoryFood(int inventoryId, int foodId, int amount) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TABLE_INVENTORY + KEY_ID, inventoryId);
		values.put(TABLE_FOOD + KEY_ID, foodId);
		values.put(KEY_AMOUNT, amount);

		long id = db.insert(TABLE_INVENTORY_FOOD, null, values);

		return id;
	}

	/*
	 * Creating a FoodIn
	 */
	public long createFoodIn(int inventoryFoodId, Date waktu, int amount) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(waktu);

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TABLE_INVENTORY_FOOD + KEY_ID, inventoryFoodId);
		values.put(KEY_TIME, date);
		values.put(KEY_AMOUNT, amount);

		long id = db.insert(TABLE_FOODIN, null, values);

		return id;
	}
	/*
	 * Creating a FoodOut
	 */
	public long createFoodOut(int inventoryFoodId, Date waktu, int amount) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(waktu);

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TABLE_INVENTORY_FOOD + KEY_ID, inventoryFoodId);
		values.put(KEY_TIME, date);
		values.put(KEY_AMOUNT, amount);

		long id = db.insert(TABLE_FOODOUT, null, values);

		return id;
	}
	/*
	 * Creating a FoodOut
	 */
	public long createFoodReduce(int inventoryFoodId, Date waktu, int amount) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(waktu);

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TABLE_INVENTORY_FOOD + KEY_ID, inventoryFoodId);
		values.put(KEY_TIME, date);
		values.put(KEY_AMOUNT, amount);

		long id = db.insert(TABLE_FOODREDUCE, null, values);

		return id;
	}

	/*
	 * Creating a inventory
	 */
	public long UpdateInventoryFood(int inventoryId, int foodId, int amount) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TABLE_INVENTORY + KEY_ID, inventoryId);
		values.put(TABLE_FOOD + KEY_ID, foodId);
		values.put(KEY_AMOUNT, amount);

		//long id = db.insert(TABLE_INVENTORY_FOOD, null, values);
		return db.update(TABLE_INVENTORY_FOOD, values, TABLE_INVENTORY + KEY_ID + "=" +
				inventoryId + " AND " + TABLE_FOOD + KEY_ID + " = " + foodId, null);
	}

	/*
	 * get single todo
	 */
	public Food getFood(long food_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_FOOD + " WHERE "
				+ TABLE_FOOD + KEY_ID + " = " + food_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Food food = new Food();
		food.setId(c.getInt(c.getColumnIndex(TABLE_FOOD + KEY_ID)));
		food.setName((c.getString(c.getColumnIndex(KEY_NAME))));
		food.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
		food.setMeasure(c.getString(c.getColumnIndex(KEY_FOOD_MEASURE)));

		return food;
	}

	/**
	 * getting all todos
	 * */
	public List<Food> getAllFoods() {
		List<Food> foods = new ArrayList<Food>();
		String selectQuery = "SELECT  * FROM " + TABLE_FOOD;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Food food = new Food();
				food.setId(c.getInt(c.getColumnIndex(TABLE_FOOD + KEY_ID)));
				food.setName((c.getString(c.getColumnIndex(KEY_NAME))));
				food.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
				food.setMeasure(c.getString(c.getColumnIndex(KEY_FOOD_MEASURE)));

				// adding to todo list
				foods.add(food);
			} while (c.moveToNext());
		}

		return foods;
	}

	/**
	 * getting all todos under single tag
	 * */
	public List<Food> getAllFoodsByInventory(int inventory_id) {  // before: getAllToDosByTags
		List<Food> foods = new ArrayList<Food>();

		String selectQuery = "SELECT  " + TABLE_FOOD + "." + TABLE_FOOD + KEY_ID + ", "
				+ TABLE_FOOD + "." + KEY_NAME + "," + TABLE_FOOD + "." + KEY_FOOD_MEASURE
				+" FROM " + TABLE_FOOD + " tf, "
				+ TABLE_INVENTORY + " ti, " + TABLE_INVENTORY_FOOD + " tif WHERE ti."
				+ TABLE_INVENTORY + KEY_ID + " = " + inventory_id  + " AND ti." + TABLE_INVENTORY + KEY_ID
				+ " = " + "tif." + TABLE_INVENTORY + KEY_ID + " AND tf." + TABLE_FOOD + KEY_ID + " = "
				+ "tif." + TABLE_FOOD + KEY_ID;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Food food = new Food();
				food.setId(c.getInt((c.getColumnIndex(TABLE_FOOD + KEY_ID))));
				food.setName((c.getString(c.getColumnIndex(KEY_NAME))));
				food.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
				food.setMeasure(c.getString(c.getColumnIndex(KEY_FOOD_MEASURE)));

				// adding to todo list
				foods.add(food);
			} while (c.moveToNext());
		}

		return foods;
	}

	/*
	 * getting todo count
	 */
	public int getFoodsCount() {
		String countQuery = "SELECT  " + TABLE_FOOD + KEY_ID + " FROM " + TABLE_FOOD;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		int count = cursor.getCount();
		cursor.close();

		// return count
		return count;
	}

	/*
	 * Updating a todo
	 */
	public int updateFood(Food food) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, food.getName());
		values.put(KEY_DESC, food.getDescription());
		values.put(KEY_FOOD_MEASURE, food.getMeasure());

		// updating row
		return db.update(TABLE_FOOD, values, TABLE_FOOD + KEY_ID + " = ?",
				new String[] { String.valueOf(food.getId()) });
	}

	/*
	 * Deleting a todo
	 */
	public void deleteFood(int food_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FOOD, TABLE_FOOD + KEY_ID + " = ?",
				new String[] { String.valueOf(food_id) });
	}


	/**
	 * getting all tags
	 * */
	public List<Inventory> getAllInventory() {
		List<Inventory> inventories = new ArrayList<Inventory>();
		String selectQuery = "SELECT  * FROM " + TABLE_INVENTORY;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Inventory t = new Inventory();
				t.setId(c.getInt((c.getColumnIndex(TABLE_INVENTORY + KEY_ID))));
				t.setName(c.getString(c.getColumnIndex(KEY_NAME)));
				t.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));

				// adding to foods list
				inventories.add(t);
			} while (c.moveToNext());
		}
		return inventories;
	}

	/*
	 * Updating a food
	 */
	public int updateInventory(Inventory inventory) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, inventory.getName());
		values.put(KEY_DESC, inventory.getDescription());

		// updating row
		return db.update(TABLE_INVENTORY, values, TABLE_INVENTORY + KEY_ID + " = ?",
				new String[] { String.valueOf(inventory.getId()) });
	}

	/*
	 * Deleting a food
	 */
	public void deleteInventory(Inventory inventory, boolean should_delete_all_inventory_food) {
		SQLiteDatabase db = this.getWritableDatabase();

		// before deleting food
		// check if todos under this food should also be deleted
		if (should_delete_all_inventory_food) {
			// get all todos under this food
			List<Food> allFoodInventory = getAllFoodsByInventory(inventory.getId());

			// delete all todos
			for (Food food : allFoodInventory) {
				// delete todo
				deleteFood(food.getId());
			}
		}

		// now delete the inventory
		db.delete(TABLE_INVENTORY, TABLE_INVENTORY + KEY_ID + " = ?",
				new String[] { String.valueOf(inventory.getId()) });
	}

	// ------------------------ "Inventory_Food" table methods ----------------//

	/*
	 * Deleting a todo tag
	 */
	public void deleteToDoTag(long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FOOD, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	public long getInventoryId(long inventoryId, long foodId)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLE_INVENTORY_FOOD + " WHERE "
				+ TABLE_INVENTORY + KEY_ID + " = " + inventoryId
				+ " AND " + TABLE_FOOD + KEY_ID + " = " + foodId;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);
		if(c.getCount() > 0)
		{
			c.moveToFirst();
			return c.getInt(c.getColumnIndex(CREATE_TABLE_INVENTORY_FOOD + KEY_ID));
		}

		return -1;
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	/**
	 * get datetime
	 * */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
}
