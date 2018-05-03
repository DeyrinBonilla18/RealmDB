package rodriguez.bonilla.uca.com.realmdb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.ViewHolder>{

    private RealmResults<Libro> dataset;
    private ItemClickOyente itemClickOyente;

    public LibrosAdapter(RealmResults<Libro> dataset, ItemClickOyente itemClickOyente) {
        this.dataset = dataset;
        this.itemClickOyente = itemClickOyente;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Libro libro = dataset.get(position);
        holder.tituloTextView.setText(libro.getTitulo());
        holder.autorTextView.setText(libro.getAutor());
        holder.puntajeTextView.setText(libro.getPuntaje() + "");

        holder.setItemClick(libro, itemClickOyente);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloTextView;
        private TextView autorTextView;
        private TextView puntajeTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            autorTextView = (TextView) itemView.findViewById(R.id.autorTextView);
            puntajeTextView = (TextView) itemView.findViewById(R.id.puntajeTextView);
        }

        public void setItemClick(final Libro libro, final ItemClickOyente itemClickOyente) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickOyente.onItemClick(libro);
                }
            });
        }
    }
}
