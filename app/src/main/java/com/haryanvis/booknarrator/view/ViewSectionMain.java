package com.haryanvis.booknarrator.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.haryanvis.booknarrator.R;

public class ViewSectionMain extends RecyclerView.ViewHolder {
    private ImageView bookImageView ;
    private TextView bookTitleTV;
    private CardView cvBook;
    public ViewSectionMain(@NonNull View itemView) {
        super(itemView);
        bookImageView = itemView.findViewById(R.id.ivBookCover);
        bookTitleTV = itemView.findViewById(R.id.tvBookTitle);
        cvBook = itemView.findViewById(R.id.cvBook);
    }

    public ImageView getBookImageView() {
        return bookImageView;
    }

    public TextView getBookTitleTV() {
        return bookTitleTV;
    }

    public CardView getCvBook() {
        return cvBook;
    }
}
