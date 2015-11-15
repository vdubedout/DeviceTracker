package eu.dubedout.devicecounter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.dubedout.devicecounter.R;
import eu.dubedout.devicecounter.bo.Device;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    private List<Device> deviceList;

    public DeviceAdapter(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.currentUser.setText(deviceList.get(position).getCurrentUser());
        holder.deviceModel.setText(deviceList.get(position).getModel());
        holder.date.setText(formatDate(deviceList.get(position).getUpdatedAt()));
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return formatter.format(date);
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView currentUser;
        public final TextView deviceModel;
        public final TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            currentUser = (TextView) itemView.findViewById(R.id.item_device_list_current_user);
            deviceModel = (TextView) itemView.findViewById(R.id.item_device_list_model);
            date = (TextView) itemView.findViewById(R.id.item_device_list_date);

        }
    }
}
