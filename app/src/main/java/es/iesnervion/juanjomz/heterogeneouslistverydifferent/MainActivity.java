package es.iesnervion.juanjomz.heterogeneouslistverydifferent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static Arma hacha=new Arma("hacha",R.drawable.hacha);
    private static Arma espada=new Arma("espada",R.drawable.espada);
    private static Arma escudo=new Arma("escudo",R.drawable.escudo);
    private static Pocion pocion=new Pocion("Poción de aumento de vida",R.drawable.pocion,true);
    private static Alimento pan=new Alimento("Pan",25);
    private static final Object[] items=new Object[]{hacha,espada,escudo,pocion,pan,hacha,espada,escudo,pocion,pan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView ls=findViewById(R.id.lista);
        ls.setAdapter(new IconAdapter<Object>(this, R.layout.row1, R.id.label, items));


    }public class IconAdapter<T>  extends ArrayAdapter<T> {

        public IconAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            ViewHolderArma holder;
            if (row==null){
                row=rowInflate(position,parent);
            }
            else{
                holder = (ViewHolder) row.getTag();
            }
            holder.getLab().setText(items[position].getNombre());
            holder.getImgV().setImageResource(items[position].getIdFoto());
            return row;
        }
        public View rowInflate(int position,ViewGroup parent){
            View row;
            ViewHolderArma armaHolder;
            TextView lab;
            ImageView imgV;
            RadioGroup equ;
            int itemViewType=getItemViewType(position);
            if(itemViewType==1){
                row=getLayoutInflater().inflate(R.layout.row1, parent, false);
                lab=row.findViewById(R.id.label);
                imgV=row.findViewById(R.id.icon);
                armaHolder=new ViewHolderArma(lab,imgV);
            }else if(itemViewType>1){
                row=getLayoutInflater().inflate(R.layout.row2, parent, false);
                lab=row.findViewById(R.id.label);
                imgV=row.findViewById(R.id.icon);
                equ=row.findViewById(R.id.rdgEquip);
                if(itemViewType==2){
                    equ.check(R.id.rdbEquip);
                }else{
                    equ.check(R.id.rdbUnequip);
                }

            }else{
                row=getLayoutInflater().inflate(R.layout.row3, parent, false);
            }
            return row;
        }
        @Override
        public int getItemViewType(int position) {
            int viewType=0;
            if(items[position] instanceof Arma){
                viewType=1;
            }else if(items[position] instanceof Pocion){
               if(((Pocion) items[position]).isEquipado()){
                   viewType=2;
               }else{
                   viewType=3;
               }
            }
            return viewType;
        }
    }
    class ViewHolderArma {
        TextView lab;
        ImageView imgV;

        ViewHolderArma (TextView lab, ImageView imgV){
            this.lab = lab;
            this.imgV = imgV;
        }

        public TextView getLab (){
            return this.lab;
        }

        public ImageView getImgV (){
            return this.imgV;
        }
    }
    class ViewHolderPocion{
        TextView lab;
        ImageView imgV;
        RadioGroup equ;

        ViewHolderPocion (TextView lab, ImageView imgV, RadioGroup equ){
            this.lab = lab;
            this.imgV = imgV;
            this.equ=equ;
        }

        public TextView getLab (){
            return this.lab;
        }

        public ImageView getImgV (){
            return this.imgV;
        }

        public RadioGroup getEqu() {
            return equ;
        }
    }
}