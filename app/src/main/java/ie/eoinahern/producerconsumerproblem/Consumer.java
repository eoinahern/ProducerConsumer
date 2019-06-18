package ie.eoinahern.producerconsumerproblem;

import android.os.Handler;
import android.os.Looper;

public class Consumer extends Thread {

	private Handler handler;
	private UpdateListCallback callback;

	public Consumer(UpdateListCallback updateListCallback) {
		this.callback = updateListCallback;
		handler = new Handler(Looper.getMainLooper());
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(4000);
				handler.post(() -> callback.removeItem());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
