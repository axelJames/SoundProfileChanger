package com.example.james.soundprofilechanger;

import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<Cursor>*/ {
    public int staHr=0;
    public int staMin=0;
    public int endHr=0;
    public int endMin=0;
    int id;
    private DBHelper dbHelper;
    private Cursor c;
    boolean days[];
    /*private ListView lv;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private SimpleCursorAdapter mAdapter;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =new DBHelper(this);
        final Button start=(Button)findViewById(R.id.button);
        assert start != null;
        fillData();
        start.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    id=1;
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(),"start");
                }
        }
        );
        Button stop=(Button)findViewById(R.id.button2);
        assert stop != null;
        stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                id=2;
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"stop");
            }
        }
        );
        Button days=(Button)findViewById(R.id.button3);
        assert days != null;
        days.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DaysDialogFragment();
                newFragment.show(getFragmentManager(),"days");
            }
        }
        );
        Button add=(Button)findViewById(R.id.button4);
        assert add != null;
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.addTime(staHr,staMin,endHr,endMin);
                staHr=0;
                staMin=0;
                endMin=0;
                endHr=0;
                fillData();
            }
        }
        );}
      /*  mAdapter = new SimpleCursorAdapter(this,
                R.layout.listview, c, new String[] { DBHelper.STARTHOUR,
                DBHelper.ENDHOUR }, new int[] { R.id.textView2,
                R.id.textView3 },0);
        lv.setAdapter(mAdapter);
        registerForContextMenu(lv);
        mCallbacks=this;
        LoaderManager lm = getLoaderManager();
        lm.initLoader(1, null, mCallbacks);
    }*/
   private void fillData() {
       ListView lv=(ListView)findViewById(R.id.listView);
       c = dbHelper.fetchAllRows();
       startManagingCursor(c);
       ListAdapter adapter = new SimpleCursorAdapter(this,
               R.layout.listview, c, new String[] { DBHelper.STARTHOUR,
               DBHelper.ENDHOUR }, new int[] { R.id.textView2,
               R.id.textView3 },0);
       lv.setAdapter(adapter);
       registerForContextMenu(lv);

   }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //long position = info.id;
        menu.add(0, v.getId(), 0, "Delete");
        }
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        long position =info.id;
        if(item.getTitle()=="Delete"){
           dbHelper.delete(position);
        }
        fillData();
        return true;
        }

    /*@Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri=contentProvider.CONTENT_URI;
        return new CursorLoader(this,uri,null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }*/
}



