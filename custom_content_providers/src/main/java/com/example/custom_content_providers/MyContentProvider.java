package com.example.custom_content_providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {
    String TAG = "LOG_TAG";

    public static final String AUTHORITY = "com.example.custom_content_providers.MyContentProvider";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, MyDbContractConstants.MyConstants.DATABASE_TABLE);

    private static final int SINGLE_ROW = 1;
    private static final int ALL_ROWS = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, MyDbContractConstants.MyConstants.DATABASE_TABLE, ALL_ROWS);
        uriMatcher.addURI(AUTHORITY, MyDbContractConstants.MyConstants.DATABASE_TABLE + "/#", SINGLE_ROW);
    }

    private SQLiteDatabase database;
    //SQLite open helper
    private MyDbOpenHelper databasehelper;

    public void openDatabase() throws SQLiteException {
        try {
            //  first try and get a writable database
            database = databasehelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            //  if can't get writable database then get readable one
            database = databasehelper.getReadableDatabase();
        }
    }

    /*creates the content provider and database, only if it does not exist
    * it does not open the database - only open the database when you need it*/
    @Override
    public boolean onCreate() {
        databasehelper = new MyDbOpenHelper(getContext(),
                MyDbContractConstants.MyConstants.DATABASE_NAME, null,
                MyDbContractConstants.MyConstants.DATABASE_VERSION);
        return true;
    }

    /*query the database*/
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArguments, String sortOrder) {
        //open database
        openDatabase();

        //replace with valid values if necessary
        String groupBy = null;
        String having = null;

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MyDbContractConstants.MyConstants.DATABASE_TABLE);

        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                //for single row given ID
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(MyDbContractConstants.MyConstants.KEY_ID + "=" + rowID);
                break;
            default:
                break;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArguments, groupBy, having, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    /*return content provider's MIME type*/
    @Override
    public String getType(Uri uri) {
        //return string identifying mime type
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                //single item
                return "vnd.android.cursor.item/vnd.com.example.slqitemodule.MyContentProvider." + MyDbContractConstants.MyConstants.DATABASE_TABLE;
            case ALL_ROWS:
                //all items
                return "vnd.android.cursor.dir/vnd.com.example.slqitemodule.MyContentProvider." + MyDbContractConstants.MyConstants.DATABASE_TABLE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    /*insert item/s*/
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        openDatabase();
        String nullColumnHack = null;
        //insert values into table
        long id = database.insert(MyDbContractConstants.MyConstants.DATABASE_TABLE, nullColumnHack, contentValues);
        //construct and return uri of newly inserted row
        if (id > -1) {
            Uri insertedId = ContentUris.withAppendedId(BASE_URI, id);
            //notify observers of change in data set
            getContext().getContentResolver().notifyChange(CONTENT_URI, null);
            return insertedId;
        } else {
            return null;
        }
    }

    /*delete row/s*/
    @Override
    public int delete(Uri uri, String selection, String[] selectionArguments) {
        openDatabase();

        //if a row uri, limit deletion to specified row
        switch (uriMatcher.match(uri)) {

        }
        //to return number deleted rows - use where clause
        //to delete all - pass in 1
        if (selection == null) {
            selection = "1";
        }
        int deleteCount = database.delete(MyDbContractConstants.MyConstants.DATABASE_TABLE, selection, selectionArguments);
        //notify observers
        getContext().getContentResolver().notifyChange(uri, null);

        return deleteCount;
    }

    /*update row/s*/
    @Override
    public int update(Uri uri, ContentValues contentValues,
                      String selection, String[] selectionArguments) {
        openDatabase();
        //if row uri, limit update to specified row
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                selection = MyDbContractConstants.MyConstants.KEY_ID + "=" + rowID + (!TextUtils.isEmpty(selection) ? (" AND (" + selection + ")") : "");
            default:
                break;
        }

        int numberRowsUpdated = database.update(MyDbContractConstants.MyConstants.DATABASE_TABLE, contentValues, selection, selectionArguments);
        //notify observers of change in data set
        getContext().getContentResolver().notifyChange(uri, null);
        return numberRowsUpdated;
    }
}
