package com.example.james.soundprofilechanger;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by james on 19-07-2016.
 */
public class contentProvider extends ContentProvider {

        public static final String PROVIDER_NAME = "com.example.james.soundprofilechanger";

        /** A uri to do operations on cust_master table. A content provider is identified by its uri */
        public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/customers" );

        /** Constants to identify the requested operation */
        private static final int CUSTOMERS = 1;

        private static final UriMatcher uriMatcher ;
        static {
            uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            uriMatcher.addURI(PROVIDER_NAME, "customers", CUSTOMERS);
        }

        /** This content provider does the database operations by this object */
        DBHelper mCustomerDB;

        /** A callback method which is invoked when the content provider is starting up */
        @Override
        public boolean onCreate() {
            mCustomerDB = new DBHelper(getContext());
            return true;
        }

        @Override
        public String getType(Uri uri) {
            return null;
        }

        /** A callback method which is by the default content uri */
        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

            if(uriMatcher.match(uri)==CUSTOMERS){
                return mCustomerDB.fetchAllRows();
            }else{
                return null;
            }
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            return 0;
        }

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            return null;
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection,
                          String[] selectionArgs) {
            return 0;
        }
    }

