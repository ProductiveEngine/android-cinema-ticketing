package mn.cinema.modelce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConn {
		
		// Database Related Constants -------------------------------------
		
		private static final String DATABASE_NAME = "CE";
	    private static final String DATABASE_TABLE = "Ticket";
	    private static final int DATABASE_VERSION = 1;
	    
	    public static final String KEY_ROWID = "_id";
	    public static final String KEY_FIRST_NAME = "FirstName";
	    public static final String KEY_LAST_NAME = "LastName";
	    public static final String KEY_SERVER_ID = "ServerId";
	    public static final String KEY_INFO = "Info";
	    public static final String KEY_STATUS = "Status";
	    //-----------------------------------------------------------------
	    
	    private static final String TAG = "DatabaseConn";
	    private DatabaseHelper mDbHelper;
	    private SQLiteDatabase mDb;
	    
	    
	    
	    /**
	     * Database creation SQL statement
	     */
	    private static final String DATABASE_CREATE =
	            "create table " + DATABASE_TABLE + " ("
	            		+ KEY_ROWID + " integer primary key autoincrement, "
	                    + KEY_FIRST_NAME + " text not null, " 
	                    + KEY_LAST_NAME + " text not null, " 
	                    + KEY_SERVER_ID + " integer not null, " 
	                    + KEY_INFO + " text not null, " 
	                    + KEY_STATUS + " integer not null);"; 
	  
	    private final Context mCtx;

	    private static class DatabaseHelper extends SQLiteOpenHelper {

	        DatabaseHelper(Context context) {
	            super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        }

	        @Override
	        public void onCreate(SQLiteDatabase db) {

	            db.execSQL(DATABASE_CREATE);
	        }

	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	                    + newVersion + ", which will destroy all old data");
	            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	            onCreate(db);
	        }
	    }

	    
	    public DatabaseConn(Context ctx) {
	        this.mCtx = ctx;
	    }

	   
	    public DatabaseConn open() throws SQLException {
	        mDbHelper = new DatabaseHelper(mCtx);
	        mDb = mDbHelper.getWritableDatabase();
	        return this;
	    }
	    
	    public void close() {
	        mDbHelper.close();
	    }

  
	    /**
	     * Create a new row 
	     * 
	     * @param fullPath 
	     * @param displayName 
	     * @param playlistName
	     * 
	     * @return the row number that the song was inserted
	     */
	    public long saveBookedTicket(String firstName, String lastName, int serverId, String info, int status) {
	    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_FIRST_NAME, firstName);
	        initialValues.put(KEY_LAST_NAME, lastName); 
	        initialValues.put(KEY_SERVER_ID, serverId);
	        initialValues.put(KEY_INFO, info);
	        initialValues.put(KEY_STATUS, status);
	        
	        
	        return  mDb.insert(DATABASE_TABLE, null, initialValues);
	    }

	
	    /**
	     * Return all the database's rows and columns
	     * 
	     * @return Cursor 
	     */
	    public Cursor fetchAllDb() {

	        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_FIRST_NAME,
	        		KEY_LAST_NAME, KEY_SERVER_ID,KEY_INFO, KEY_STATUS }, null, null, null, null, null);
	    }

	    public Cursor fetchTickets() {

	        return mDb.query(DATABASE_TABLE, new String[] {KEY_FIRST_NAME,
	        		KEY_LAST_NAME, KEY_INFO}, null, null, null, null, null);
	    }

	    /**
	     * Update a row (not used)
	     * 
	     * @param rowId
	     * @param title
	     * @param body
	     * @param reminderDateTime
	     * 
	     * @return true on success else false
	     */
	    public boolean updatePlaylist(long rowId, String title, String body, String reminderDateTime) {
	        ContentValues args = new ContentValues();
	      //  args.put(KEY_FULL_PATH, title);
	      //  args.put(KEY_DISPLAY_NAME, body);
	       // args.put(KEY_LIST_NAME , reminderDateTime);

	        return mDb.update(DATABASE_TABLE, args, KEY_SERVER_ID + "=" + rowId, null) > 0;
	    }
	

}
