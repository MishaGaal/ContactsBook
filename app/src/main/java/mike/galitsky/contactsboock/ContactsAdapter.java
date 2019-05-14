package mike.galitsky.contactsboock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsAdapter extends ListAdapter<Contact,ContactsAdapter.ContactHolder> {

    private OnItemClickListener listener;

    public ContactsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Contact> DIFF_CALLBACK = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getLastName().equals(newItem.getLastName()) &&
                    oldItem.getPhone().equals(newItem.getPhone());
        }
    };
    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);

        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = getItem(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewLastName.setText(contact.getLastName());
        holder.textViewPhone.setText(contact.getPhone());
    }



    public Contact getContactAt(int pos){
        return getItem(pos);
    }


    class ContactHolder extends RecyclerView.ViewHolder{
        private TextView textViewName, textViewLastName, textViewPhone;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.Name);
            textViewLastName = itemView.findViewById(R.id.LastName);
            textViewPhone = itemView.findViewById(R.id.Phone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.onClick(getItem(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onClick(Contact contact);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
