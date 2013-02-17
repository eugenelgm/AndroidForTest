package com.example.test;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.model.Item;
import com.example.test.model.ItemSet.ItemSetChangeListener;

public class MainActivity extends Activity implements ItemSetChangeListener {
	
	private MainController controller;
	private EditText editText;
	private Button addButton;
	private ListView listView;
	private MainListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		controller = MainController.getInstance();
		controller.setChangedListener(this);
		
		editText = (EditText) findViewById(R.id.editText1);
		addButton = (Button) findViewById(R.id.button1);
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (editText.getText().length() > 0) {
					controller.add(new Item(editText.getText().toString()));
				}
			}
		});
		
		listView = (ListView) findViewById(R.id.listView1);
		adapter = new MainListAdapter(this, controller.getAll());
		listView.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		controller.removeChacnedListener(this);
	}
	
	@Override
	public void onChanged() {
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}


	private static class ViewHolder {
		TextView textView;
	}
	
	private class MainListAdapter extends BaseAdapter {
		
		private final Context context;
		private final List<Item> items;
		
		MainListAdapter(Context context, List<Item> items) {
			this.context = context;
			this.items = items;
		}

		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public Item getItem(int position) {
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = null;
			ViewHolder holder;
			if (null == convertView) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				layout = inflater.inflate(R.layout.list_item, null);
				holder = new ViewHolder();
				holder.textView = (TextView) layout.findViewById(R.id.text_view);
				layout.setTag(holder);
			} else {
				layout = convertView;
				holder = (ViewHolder) layout.getTag();
			}
			holder.textView.setText(getItem(position).getText());
			return layout;
		}
		
	}


}
