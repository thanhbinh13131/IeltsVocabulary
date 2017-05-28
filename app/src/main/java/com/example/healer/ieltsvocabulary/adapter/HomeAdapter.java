package com.example.healer.ieltsvocabulary.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.healer.ieltsvocabulary.HomeActivity;
import com.example.healer.ieltsvocabulary.R;

import java.io.IOException;
import java.io.InputStream;

public class HomeAdapter extends CursorAdapter {

	private static final int VIEW_TYPE_COUNT = 1;
	private static final int VIEW_TYPE = 0;

//	public AssetManager getAssets() {
//		return AssetManager.;
//	}

	public static class ViewHolder {
		public final ImageButton img;

		public ViewHolder(View view) {
			img = (ImageButton) view.findViewById(R.id.img_unit);
			//img.setImageBitmap(decodeFile(unit.getAvatar()));
		}
	}
	public HomeAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
	}

//		super(context,layoutId,list);
//		this.context = context;
//		this.layoutId = layoutId;
//		this.arr = list;

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// Choose the layout type
		int viewType = getItemViewType(cursor.getPosition());
		int layoutId = -1;

		layoutId = R.layout.custom_home;

		View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

		ViewHolder viewHolder = new ViewHolder(view);
		view.setTag(viewHolder);

		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		ViewHolder viewHolder = (ViewHolder) view.getTag();

		int viewType = getItemViewType(cursor.getPosition());

		//if(viewType)



		// Read date from cursor
		String avatarUri = cursor.getString(HomeActivity.COL_AVATAR_URI);
        Log.e("Avater Uri:", avatarUri);

		viewHolder.img.setImageBitmap(decodeFile(context, avatarUri));

	}
//	public void setUseTodayLayout(boolean useTodayLayout){
//		mUseTodayLayout = useTodayLayout;
//	}

	@Override
	public int getItemViewType(int position) {
		return VIEW_TYPE;
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}




//
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		if(convertView == null){
//			LayoutInflater inflater = context.getLayoutInflater();
//			convertView = inflater.inflate(layoutId, null);
//		}
//		final Unit unit = arr.get(position);
//		ImageButton img = (ImageButton) convertView.findViewById(R.id.img_unit);
//		img.setImageBitmap(decodeFile(unit.getAvatar()));
//		img.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				LessonFragment lf = LessonFragment.newInstance(position,unit.getId());
//				context.getFragmentManager().beginTransaction().replace(R.id.fragment_container,lf).addToBackStack(null).commit();
//
//				//Toast.makeText(context,"asdfas",Toast.LENGTH_SHORT).show();
//			}
//		});
//		return convertView;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return arr.size();
//	}
//	@Override
//	public long getItemId(int arg0) {
//		// TODO Auto-generated method stub
//		return arg0;
//	}
	// Decodes image and scales it to reduce memory consumption
	private Bitmap decodeFile(Context myContext,String f){
		AssetManager mngr = myContext.getAssets();
		InputStream is = null;
		try {
			is = mngr.open(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Decode image size
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(is,null,o);

		// Decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize=8;
		return BitmapFactory.decodeStream(is, null, o2);
	}


}







//public class HomeAdapter extends ArrayAdapter<Unit> {
//
//	ArrayList<Unit> arr = null;
//	Activity context;
//	int layoutId;
//
//
//
//	public HomeAdapter(Activity context, int layoutId, ArrayList<Unit> list){
//		super(context,layoutId,list);
//		this.context = context;
//		this.layoutId = layoutId;
//		this.arr = list;
//
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		if(convertView == null){
//			LayoutInflater inflater = context.getLayoutInflater();
//			convertView = inflater.inflate(layoutId, null);
//		}
//		final Unit unit = arr.get(position);
//		ImageButton img = (ImageButton) convertView.findViewById(R.id.img_unit);
//		img.setImageBitmap(decodeFile(unit.getAvatar()));
//		img.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				LessonFragment lf = LessonFragment.newInstance(position,unit.getId());
//				context.getFragmentManager().beginTransaction().replace(R.id.fragment_container,lf).addToBackStack(null).commit();
//
//				//Toast.makeText(context,"asdfas",Toast.LENGTH_SHORT).show();
//			}
//		});
//		return convertView;
//	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return arr.size();
//	}
//	@Override
//	public long getItemId(int arg0) {
//		// TODO Auto-generated method stub
//		return arg0;
//	}
//	// Decodes image and scales it to reduce memory consumption
//	private Bitmap decodeFile(String f){
//		AssetManager mngr = this.context.getAssets();
//		InputStream is = null;
//		try {
//            is = mngr.open(f);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		// Decode image size
//		BitmapFactory.Options o = new BitmapFactory.Options();
//		o.inJustDecodeBounds = true;
//		BitmapFactory.decodeStream(is,null,o);
//
//		// Decode with inSampleSize
//		BitmapFactory.Options o2 = new BitmapFactory.Options();
//		o2.inSampleSize=8;
//		return BitmapFactory.decodeStream(is, null, o2);
//	}
//
//
//}
