package kmitl.project.bdloner.moneygrow.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import kmitl.project.bdloner.moneygrow.init.CustomTextView;
import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.model.Image;

/**
 * Created by BDLoneR on 18/11/2560.
 */

public class ListImageAdapter extends ArrayAdapter<Image> {
    public ListImageAdapter(Context context, int resource, List<Image> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (null == v) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.img_item, null);
        }
        Image img_model = getItem(position);
        ImageView img = v.findViewById(R.id.image_view_item);
        CustomTextView txtTitle = v.findViewById(R.id.txt_image_title);

        img.setImageResource(img_model.getImage_id());
        txtTitle.setText(img_model.getImage_title());

        return v;
    }
}
