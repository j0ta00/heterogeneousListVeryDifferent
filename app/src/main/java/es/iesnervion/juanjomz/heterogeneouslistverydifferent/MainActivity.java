package es.iesnervion.juanjomz.heterogeneouslistverydifferent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView selection;
    private static Arma hacha=new Arma("hacha",R.drawable.hacha);
    private static Arma espada=new Arma("espada",R.drawable.espada);
    private static Arma escudo=new Arma("escudo",R.drawable.escudo);
    private static Pocion pocion=new Pocion("Poción de aumento de vida",R.drawable.pocion,true);
    private static Alimento pan=new Alimento("Pan",25);
    private static final Object[] items=new Object[]{pan,espada,escudo,espada,hacha,pan,espada,escudo,espada,hacha};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView ls=findViewById(R.id.lista);
        ls.setAdapter(new IconAdapter<Object>(this,items));
        ls.setOnItemClickListener(this::onListItemClick);
        selection=(TextView) findViewById(R.id.selection);
    }
    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selection.setText(items[i].toString());
    }


    public class IconAdapter<T> extends BaseAdapter {
        Context context;
        Object[] datosLista;

        public IconAdapter(@NonNull Context context, @NonNull Object[] objects) {
            this.context = context;
            //this.resource=resource;
            datosLista = objects;
        }

        @Override
        public int getCount() {
            return datosLista.length;
        }

        @Override
        public Object getItem(int i) {
            return datosLista[i];
        }

        @Override
        public long getItemId(int i) {
            return datosLista[i].hashCode();
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent){
            int itemViewType=getItemViewType(position);
            View row=convertView;
            ViewHolderArma viewHolderArma=null;
            ViewHolderPocion viewHolderPocion=null;
            ViewHolderAlimento viewHolderAlimento=null;
            TextView lab,lab2;
            ImageView img;
            RadioGroup rdg;
            Button btn;
            Arma arma;
            Pocion pocion;
            Alimento alimento;
            if (row==null){
                row=rowInflate(position,parent,itemViewType);
                if(itemViewType==1) {
                    lab=row.findViewById(R.id.label);
                    img=row.findViewById(R.id.icon);
                    viewHolderArma=new ViewHolderArma(lab,img);
                    row.setTag(viewHolderArma);
                }else if(itemViewType==2){
                    lab=row.findViewById(R.id.labelRow2);
                    img=row.findViewById(R.id.iconRow2);
                    rdg=row.findViewById(R.id.rdgEquip);
                    viewHolderPocion=new ViewHolderPocion(lab,img,rdg);
                    row.setTag(viewHolderPocion);
                }else{
                    lab=row.findViewById(R.id.labelRow3);
                    btn=row.findViewById(R.id.btnAdd);
                    lab2=row.findViewById(R.id.label2Row3);
                    viewHolderAlimento=new ViewHolderAlimento(lab,btn,lab2);
                    row.setTag(viewHolderAlimento);
                }
            }
            else{
                if(itemViewType==1) {
                    viewHolderArma = (ViewHolderArma) row.getTag();
                }else if(itemViewType==2){
                    viewHolderPocion = (ViewHolderPocion) row.getTag();
                }else{
                    viewHolderAlimento = (ViewHolderAlimento) row.getTag();
                }
            }
            if(itemViewType==1) {
                arma=(Arma)items[position];
                viewHolderArma.getLab().setText(arma.getNombre());
                viewHolderArma.getImgV().setImageResource(arma.getIdFoto());
            }else if(itemViewType==2){
                pocion=(Pocion)items[position];
                viewHolderPocion.getLab().setText(pocion.getNombre());
                viewHolderPocion.getImgV().setImageResource(pocion.getFoto());
            }else{
                alimento=(Alimento)items[position];
                viewHolderAlimento.getLab().setText(alimento.getNombre());
                viewHolderAlimento.getLab2().setText(String.valueOf(alimento.getCantdad()));
            }
            return  row;
        }
        public View rowInflate(int position,ViewGroup parent,int itemViewType){
            View row;
            if(itemViewType==1) {
                row=getLayoutInflater().inflate(R.layout.row1, parent, false);
            }else if(itemViewType==2){
                row=getLayoutInflater().inflate(R.layout.row2, parent, false);
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

    class ViewHolderAlimento{
        TextView lab;
        Button btn;
        TextView lab2;

        ViewHolderAlimento(TextView lab,Button btn,TextView lab2){
            this.lab = lab;
            this.btn = btn;
            this.lab2 = lab2;
        }
        public TextView getLab (){
            return this.lab;
        }

        public Button getBtn (){
            return this.btn;
        }

        public TextView getLab2() {
            return this.lab2;
        }

    }
}