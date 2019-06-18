package ie.eoinahern.producerconsumerproblem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements UpdateListCallback {

	private MyAdapter adapter = new MyAdapter();
	private Button btnProducer;
	private Button btnConsumer;
	private RecyclerView recycler;
	private int threadNo = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpViewElements();
		setUpAdapter();
		setClickListeners();
	}

	private void setUpViewElements() {
		btnProducer = findViewById(R.id.btn_producer);
		btnConsumer = findViewById(R.id.btn_consumer);
		recycler = findViewById(R.id.recycler);
	}

	private void setUpAdapter() {
		LinearLayoutManager manager = new LinearLayoutManager(this);
		recycler.setLayoutManager(manager);
		recycler.setAdapter(adapter);
	}

	private void setClickListeners() {
		btnProducer.setOnClickListener((view) -> new Producer(getThreadName(), this).start());
		btnConsumer.setOnClickListener((view) -> new Consumer(this).start());
	}

	/**
	 * callbacks to update adapter from threads
	 *
	 * @param name
	 */

	@Override public void addItem(String name) {
		adapter.addName(name);
	}

	@Override public void removeItem() {
		adapter.removeItemFromBottom();
	}

	/**
	 * give Producer thread a numeric identified so it
	 * can be visualised in our RecyclerView as
	 * it adds to it.
	 *
	 * @return
	 */

	private String getThreadName() {
		return getResources().getString(R.string.thread_prefix)
				.concat(String.valueOf(threadNo++));
	}
}
