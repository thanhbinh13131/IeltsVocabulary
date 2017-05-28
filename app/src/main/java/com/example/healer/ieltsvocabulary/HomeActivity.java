package com.example.healer.ieltsvocabulary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import com.example.healer.ieltsvocabulary.adapter.HomeAdapter;
import com.example.healer.ieltsvocabulary.data.MyDataDbHelper;
import com.example.healer.ieltsvocabulary.data.VocabularyBuiltUri;
import com.example.healer.ieltsvocabulary.model.Unit;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    HomeAdapter homeAdapter = null;
    ArrayList<Unit> listUnit = null;

    private static final int FORECAST_LOADER = 0;
    private HomeAdapter mHomeAdapter;

    private GridView mGridView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private static final String[] UNIT_COLUMNS = {

            VocabularyBuiltUri.UnitEntry.TABLE_NAME + "." + VocabularyBuiltUri.UnitEntry.TABLE_ID,
            VocabularyBuiltUri.UnitEntry.COLUMN_NAME,
            VocabularyBuiltUri.UnitEntry.COLUMN_NUM_WORD,
            VocabularyBuiltUri.UnitEntry.COLUMN_AVATAR_URI
    };

    // These indices are tied to UNIT_COLUMNS.  If UNIT_COLUMNS changes, these
    // must change.
    public static final int COL_UNIT_ID = 0;
    public static final int COL_UNIT_NAME = 1;
    public static final int COL_NUM_OF_WORD = 2;
    public static final int COL_AVATAR_URI = 3;




    MyDataDbHelper myData = null;
    SQLiteDatabase myDataBase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Uri avatarUri = VocabularyBuiltUri.UnitEntry.CONTENT_URI;
        Cursor cur = this.getContentResolver().query(avatarUri,
                UNIT_COLUMNS, null, null, null);

        Log.e("so cot: ", "" + cur.getCount());
        mHomeAdapter = new HomeAdapter(this, cur, 0);

        mGridView = (GridView) findViewById(R.id.list_unit_gridview);

        mGridView.setAdapter(mHomeAdapter);
//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
//                LessonFragment lf = LessonFragment.newInstance(position,cursor.getInt(COL_UNIT_ID));
//				getFragmentManager().beginTransaction().replace(R.id.fragment_container,lf).addToBackStack(null).commit();
//
//				Toast.makeText(HomeActivity.this,"You clicked a unit!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mGridView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//				LessonFragment lf = LessonFragment.newInstance(position,unit.getId());
//				context.getFragmentManager().beginTransaction().replace(R.id.fragment_container,lf).addToBackStack(null).commit();
//
//				//Toast.makeText(context,"asdfas",Toast.LENGTH_SHORT).show();
//			}
//		});

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri avatarUri = VocabularyBuiltUri.UnitEntry.CONTENT_URI;
        String[] projection = new String[] { VocabularyBuiltUri.UnitEntry.COLUMN_AVATAR_URI };
        Log.e("onCreateLoader: ", "thach thanh binh");
        return new CursorLoader(this,
                avatarUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.e("on Fisnished", "this is on finished");
        mHomeAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        mHomeAdapter = new HomeAdapter(getApplicationContext(),null,0);
//
//
//
//
//
//
//
//        listUnit = new ArrayList<Unit>();
//        myData = new MyDataDbHelper(this);
//        myData.open();
//        myDataBase = myData.getMyDatabase();
//        listUnit = loadData();
//        homeAdapter = new HomeAdapter(this,R.layout.custom_home, listUnit);
//        GridView gv = (GridView) findViewById(R.id.listUnit);
//        gv.setAdapter(homeAdapter);
//    }
//    public ArrayList<Unit> loadData(){
//        ArrayList<Unit> arrUnit = new ArrayList<Unit>();
//        Cursor c = myDataBase.rawQuery("SELECT * FROM UNIT", null);
//        c.moveToFirst();
//        String data = "";
//        while(c.isAfterLast() == false){
//            arrUnit.add(new Unit(c.getInt(0),c.getString(1).toString().trim(),Integer.parseInt(c.getString(2)),c.getString(3).trim().toString()));
//            c.moveToNext();
//        }
//        c.close();
//        return arrUnit;
//    }
//}
