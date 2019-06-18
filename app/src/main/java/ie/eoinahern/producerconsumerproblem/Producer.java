package ie.eoinahern.producerconsumerproblem;

import android.os.Handler;
import android.os.Looper;

public class Producer extends Thread {

	private String name;
	private UpdateListCallback callback;
	private Handler handler;

	public Producer(String name, UpdateListCallback listCallback) {
		this.name = name;
		this.callback = listCallback;
		handler = new Handler(Looper.getMainLooper());
	}

	@Override
	public void run() {
		while (true)
			try {
				sleep(3000);
				handler.post(() -> callback.addItem(name));
			} catch (Exception e) {
				System.out.println("exception in consumer");
				e.printStackTrace();
			}
	}
}
