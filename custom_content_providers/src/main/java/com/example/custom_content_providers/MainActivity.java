package com.example.custom_content_providers;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String TAG = "LOG_TAG";
    private Integer insertedRecordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
            }
        });

        Button buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecord();
            }
        });

        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecords();
            }
        });

        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
                updateRecord();
            }
        });
    }

    private void updateRecord() {
        ContentResolver cr = getContentResolver();
        Log.i(TAG,"data type; " + cr.getType(MyContentProvider.CONTENT_URI));
        ContentValues values = new ContentValues();
        values.put(MyDbContractConstants.MyConstants.PHONE, "999999999");

        String selection = MyDbContractConstants.MyConstants.PHONE + " = ? ";
        String[] selectionArguments = {"021345678"};

        int numberRecordsUpdated = cr.update(MyContentProvider.CONTENT_URI, values, selection, selectionArguments);
        Log.i(TAG, "Number records updated: " + numberRecordsUpdated);
    }

    private void deleteRecords() {
        ContentResolver cr = getContentResolver();
        Log.i(TAG,"data type; " + cr.getType(MyContentProvider.CONTENT_URI));
//        passing null will delete all rows
        String selection = null;  //MyDbContractConstants.MyConstants.EMAIL + " = ? ";
        String[] selectionArguments = null; //{"king_john@royalty.com"};

        int numberRowsDeleted = cr.delete(MyContentProvider.CONTENT_URI, selection, selectionArguments);

        Log.i(TAG, "Number rows deleted: " + numberRowsDeleted);

    }

    private void insertRecord() {
        ContentResolver cr = getContentResolver();
        Log.i(TAG,"data type; " + cr.getType(MyContentProvider.CONTENT_URI));
        ContentValues values = new ContentValues();
        values.put(MyDbContractConstants.MyConstants.NAME, "KingJohn");
        values.put(MyDbContractConstants.MyConstants.EMAIL, "king_john@royalty.com");
        values.put(MyDbContractConstants.MyConstants.PHONE, "021345678");

        Uri insertedUri = cr.insert(MyContentProvider.CONTENT_URI, values);

        // get the row id - it's the last path segment in the returned uri for the inserted record
        String lastPathSegment = insertedUri.getLastPathSegment();

        // save the inserted record's row id in global variable
        insertedRecordId = Integer.valueOf(lastPathSegment);

        Log.i(TAG, "Uri of inserted record: " + insertedUri);
    }

    private void getRecord() {
        //        insert a record to ensure that there is one to query
        insertRecord();
        //        get the row id for the inserted record
        long recordId = insertedRecordId;
        ContentResolver cr = getContentResolver();

        Log.i(TAG,"find record with ID: " + String.valueOf(recordId));
        //        create a uri for the specific record we're looking for'
        Uri recordUri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI,recordId);
        Log.i(TAG,"data type; " + cr.getType(recordUri));

        String[] projection = null;
        String selection = null; //MyDbContractConstants.MyConstants.NAME + " = ?";
        String[] selectionArguments = null; //{"KingJohn"};
        String sortOrder = null;

        Cursor cursor = cr.query(recordUri, projection, selection, selectionArguments, sortOrder);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String key = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            Log.i(TAG, key + " " + name + " " + email + " " + phone);
            cursor.moveToNext();
        }
    }
}
