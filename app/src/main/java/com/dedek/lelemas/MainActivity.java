package com.dedek.lelemas;

import com.dedek.lelemas.helper.DatabaseHelper;
import com.dedek.lelemas.model.Food;
import com.dedek.lelemas.model.Todo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	// Database Helper
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = new DatabaseHelper(getApplicationContext());

		// Creating tags
		Food food1 = new Food("Shopping");
		Food food2 = new Food("Important");
		Food food3 = new Food("Watchlist");
		Food food4 = new Food("Androidhive");

		// Inserting tags in db
		long tag1_id = db.createTag(food1);
		long tag2_id = db.createTag(food2);
		long tag3_id = db.createTag(food3);
		long tag4_id = db.createTag(food4);

		Log.d("Food Count", "Food Count: " + db.getAllTags().size());

		// Creating ToDos
		Todo todo1 = new Todo("iPhone 5S", 0);
		Todo todo2 = new Todo("Galaxy Note II", 0);
		Todo todo3 = new Todo("Whiteboard", 0);

		Todo todo4 = new Todo("Riddick", 0);
		Todo todo5 = new Todo("Prisoners", 0);
		Todo todo6 = new Todo("The Croods", 0);
		Todo todo7 = new Todo("Insidious: Chapter 2", 0);

		Todo todo8 = new Todo("Don't forget to call MOM", 0);
		Todo todo9 = new Todo("Collect money from John", 0);

		Todo todo10 = new Todo("Post new Article", 0);
		Todo todo11 = new Todo("Take database backup", 0);

		// Inserting todos in db
		// Inserting todos under "Shopping" Food
		long todo1_id = db.createFood(todo1, new long[] { tag1_id });
		long todo2_id = db.createFood(todo2, new long[] { tag1_id });
		long todo3_id = db.createFood(todo3, new long[] { tag1_id });

		// Inserting todos under "Watchlist" Food
		long todo4_id = db.createFood(todo4, new long[] { tag3_id });
		long todo5_id = db.createFood(todo5, new long[] { tag3_id });
		long todo6_id = db.createFood(todo6, new long[] { tag3_id });
		long todo7_id = db.createFood(todo7, new long[] { tag3_id });

		// Inserting todos under "Important" Food
		long todo8_id = db.createFood(todo8, new long[] { tag2_id });
		long todo9_id = db.createFood(todo9, new long[] { tag2_id });

		// Inserting todos under "Androidhive" Food
		long todo10_id = db.createFood(todo10, new long[] { tag4_id });
		long todo11_id = db.createFood(todo11, new long[] { tag4_id });

		Log.e("Todo Count", "Todo count: " + db.getToDoCount());

		// "Post new Article" - assigning this under "Important" Food
		// Now this will have - "Androidhive" and "Important" Tags
		db.createTodoTag(todo10_id, tag2_id);

		// Getting all tag names
		Log.d("Get Tags", "Getting All Tags");

		List<Food> allFoods = db.getAllTags();
		for (Food food : allFoods) {
			Log.d("Food Name", food.getTagName());
		}

		// Getting all Todos
		Log.d("Get Todos", "Getting All ToDos");

		List<Todo> allToDos = db.getAllToDos();  // change to food
		for (Todo todo : allToDos) {
			Log.d("ToDo", todo.getNote());
		}

		// Getting todos under "Watchlist" tag name
		Log.d("ToDo", "Get todos under single Food name");

		List<Todo> tagsWatchList = db.getAllToDosByTag(food3.getTagName());
		for (Todo todo : tagsWatchList) {
			Log.d("ToDo Watchlist", todo.getNote());
		}

		// Deleting a ToDo
		Log.d("Delete ToDo", "Deleting a Todo");
		Log.d("Food Count", "Food Count Before Deleting: " + db.getToDoCount());

		db.deleteToDo(todo8_id);

		Log.d("Food Count", "Food Count After Deleting: " + db.getToDoCount());

		// Deleting all Todos under "Shopping" tag
		Log.d("Food Count",
				"Food Count Before Deleting 'Shopping' Todos: "
						+ db.getToDoCount());

		db.deleteTag(food1, true);

		Log.d("Food Count",
				"Food Count After Deleting 'Shopping' Todos: "
						+ db.getToDoCount());

		// Updating tag name
		food3.setTagName("Movies to watch");
		db.updateTag(food3);

		// Don't forget to close database connection
		db.closeDB();
		
	}
}
