package com.example.anandparmeetsingh.books;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ParmeetSingh on 5/9/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    ArrayList<Word> Word = new ArrayList<>();
    Activity mContext;

    public WordAdapter(Activity context, ArrayList<Word> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
    }

    //public WordAdapter(Activity context, ArrayList<Word> Word) {
      //  mContext = context;
        //this.Word = Word;
        //super(context, 0, Word);
    //}

    @Override
    public int getCount() {
        return Word.size();
    }

    @Override
    public com.example.anandparmeetsingh.books.Word getItem(int pos) {
        return Word.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final CampaignItemViewHolders holder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.activity_list_display, null);
//            v = li.inflate(R.layout.aa, null);
            holder = new CampaignItemViewHolders(v);
            v.setTag(holder);
        } else {
            holder = (CampaignItemViewHolders) v.getTag();
        }


        String title = Word.get(position).getMagnitude();
        holder.magnitudeView.setText(title);

        String description = Word.get(position).getLocation();
        holder.desc.setText(description);

        //String set =Word.get(position).getmSet();
        //holder.setv.setText(set);

        String set1 = Word.get(position).getDate();
        holder.set1.setText(set1);

        String url = Word.get(position).getUrl();
        holder.setv.setText(url);

        return v;
    }

}

class CampaignItemViewHolders {
    TextView magnitudeView;
    TextView setv;
    TextView set1;
    TextView desc;

    public CampaignItemViewHolders(View base) {

        magnitudeView = (TextView) base.findViewById(R.id.magnitude);
        setv = (TextView) base.findViewById(R.id.primary_location);
        desc = (TextView) base.findViewById(R.id.location_offset);
        set1 = (TextView) base.findViewById(R.id.date);
    }
}