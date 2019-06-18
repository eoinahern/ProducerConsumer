package ie.eoinahern.producerconsumerproblem;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

	private List<String> nameList = new ArrayList<>();
	private static final String ITEMREMOVED_TAG = "item removed";
	private static final String SUCCESSFUL_REMOVE = "successful";


	@NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View simpleView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.name_view_item, parent, false);
		return new ViewHolder(simpleView);
	}

	@Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setName(nameList.get(position));
	}

	@Override public int getItemCount() {
		return nameList.size();
	}

	/**
	 * multiple threads trying to access and update my list.
	 * make it a synchronized operation to prevent adding
	 * at the same time.
	 *
	 * @param name
	 */

	synchronized void addName(String name) {
		nameList.add(name);
		notifyItemInserted(nameList.size() - 1);
		notifyDataSetChanged();
	}

	/**
	 * multiple threads will also be trying to remove from
	 * our recyclerview.
	 */

	synchronized void removeItemFromBottom() {
		if (getItemCount() != 0) {
			int lastIndex = nameList.size() - 1;
			nameList.remove(lastIndex);
			notifyItemRemoved(lastIndex);
			notifyItemRangeChanged(lastIndex, nameList.size());
			Log.d(ITEMREMOVED_TAG, SUCCESSFUL_REMOVE);
		}
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private TextView nameTxt;

		public ViewHolder(View v) {
			super(v);
			nameTxt = v.findViewById(R.id.name_text);
		}

		public void setName(String name) {
			nameTxt.setText(name);
		}
	}
}
