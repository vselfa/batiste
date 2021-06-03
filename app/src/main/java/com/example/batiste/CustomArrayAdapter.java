package com.example.batiste;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Producte>  {
    private final LayoutInflater layoutInflater;
    List<Producte> llistaProductes;
    public CustomArrayAdapter(Context context, List<Producte> productes) {
        //no se perque fa aso
        super(context, 0, productes);
        layoutInflater = LayoutInflater.from(context);
        llistaProductes = productes;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder;
        ImageView avatar;

        if (convertView == null)   {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.listview_row, null);
            // Els texts
            holder.setCheckBox(convertView.findViewById(R.id.checkBox));
            holder.setTextViewTitle( convertView.findViewById(R.id.producte_nom));
            holder.setTextViewSubtitle(convertView.findViewById(R.id.producte_descripcio));
            // La foto
            holder.setImageView(convertView.findViewById(R.id.producte_avatar));
            // Els botons i la quantitat
            holder.setButtonMes (convertView.findViewById(R.id.button_plus));
            holder.setButtonMenys (convertView.findViewById(R.id.button_minus));
            holder.setTextViewQuantitat( convertView.findViewById(R.id.quantitat));

            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }
        // To show the picture of the product: Glide
        avatar = convertView.findViewById(R.id.producte_avatar);
        Producte producte = getItem(position);
        Glide.with(getContext()).load(producte.getFoto()).into(avatar);

        holder.getTextViewTitle().setText(producte.getNomProducte());
        holder.getTextViewSubtitle().setText(producte.getDescripcioProducte());
        holder.getImageView();


        // Per gestionar la quantitat des dels botons
        TextView tvQuantitat = holder.getTextViewQuantitat();
        CheckBox cbProduct = holder.getCheckBox();

        // To manage the CheckBoxes
        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setChecked(producte.isChecked());
        holder.getCheckBox().setOnClickListener(v -> {
            String message;
            CheckBox checkBox = (CheckBox) v;
            int position1 = (Integer) v.getTag();
            getItem(position1).setChecked(checkBox.isChecked());

            // Control sobre els checkboxes
            if (llistaProductes.get(position1).getSelected()) {
                message = llistaProductes.get(position1).getNomProducte() + " unselected";
                llistaProductes.get(position1).setSelected(false);
                // Posem quantitat a 0 si deseleccionem un producte
                llistaProductes.get(position1).setQuantitat(0);
                tvQuantitat.setText("0");
            }
            else {
                message = llistaProductes.get(position1).getNomProducte() + " selected";
                llistaProductes.get(position1).setSelected(true);
            }
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        // Listener sobre el botó +
        holder.getButtonMes().setOnClickListener(v -> {
            // Sols si està activat el checkbox
            if (cbProduct.isChecked()) {
                // Recuperem la quantitat actual
                int quantitat = llistaProductes.get(position).getQuantitat();
                // La incrementem
                quantitat++;
                // Actualitzem la llista
                llistaProductes.get(position).setQuantitat(quantitat);
                // Però como li la posem a text??
                tvQuantitat.setText(String.valueOf(quantitat));
            }
        });

        // Listener sobre el botó -
        holder.getButtonMenys().setOnClickListener(v -> {
            // Sols si està activat el checkbox
            if (cbProduct.isChecked()) {
                // Recuperem la quantitat actual
                int quantitat = llistaProductes.get(position).getQuantitat();
                // La decrementem
                if (quantitat > 0) quantitat--;
                // Actualitzem la llista
                llistaProductes.get(position).setQuantitat(quantitat);
                // Però como li la posem a text??
                tvQuantitat.setText(String.valueOf(quantitat));
            }
        });

        return convertView;
    }

    static class Holder    {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;
        ImageView foto;
        Button buttonMes, buttonMenys;
        TextView textViewQuantitat;

        public Button getButtonMes() {
            return buttonMes;
        }
        public void setButtonMes(Button buttonMes) {
            this.buttonMes = buttonMes;
        }
        public Button getButtonMenys() {
            return buttonMenys;
        }
        public void setButtonMenys(Button buttonMenys) {
            this.buttonMenys = buttonMenys;
        }
        public TextView getTextViewQuantitat() {
            return textViewQuantitat;
        }
        public void setTextViewQuantitat(TextView textViewQuantitat) {
            this.textViewQuantitat = textViewQuantitat;
        }
        public TextView getTextViewTitle()  {
            return textViewTitle;
        }
        public void setTextViewTitle(TextView textViewTitle)    {
            this.textViewTitle = textViewTitle;
        }
        public TextView getTextViewSubtitle()     {
            return textViewSubtitle;
        }
        public void setTextViewSubtitle(TextView textViewSubtitle)    {
            this.textViewSubtitle = textViewSubtitle;
        }
        public CheckBox getCheckBox()    {
            return checkBox;
        }
        public void setCheckBox(CheckBox checkBox)     {
            this.checkBox = checkBox;
        }
        public void getImageView () {
        }
        public void setImageView ( ImageView foto)     {
            this.foto = foto;
        }
    }
}