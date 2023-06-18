package com.example.erpnext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CustomAlertDialogBuilder {
    private Context context;
    private String title;
    private String message;
    private Drawable image;
    private String positiveButtonText;
    private String negativeButtonText;
    private DialogInterface.OnClickListener positiveButtonClickListener;
    private DialogInterface.OnClickListener negativeButtonClickListener;

    public CustomAlertDialogBuilder(Context context) {
        this.context = context;
    }

    public CustomAlertDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomAlertDialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomAlertDialogBuilder setImage(Drawable image) {
        this.image = image;
        return this;
    }

    public CustomAlertDialogBuilder setPositiveButton(String text, DialogInterface.OnClickListener listener) {
        this.positiveButtonText = text;
        this.positiveButtonClickListener = listener;
        return this;
    }

    public CustomAlertDialogBuilder setNegativeButton(String text, DialogInterface.OnClickListener listener) {
        this.negativeButtonText = text;
        this.negativeButtonClickListener = listener;
        return this;
    }

    public AlertDialog create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (image != null) {
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(image);
            builder.setView(imageView);
        }

        if (title != null) {
            builder.setTitle(title);
        }

        if (message != null) {
            builder.setMessage(message);
        }

        if (positiveButtonText != null && positiveButtonClickListener != null) {
            builder.setPositiveButton(positiveButtonText, positiveButtonClickListener);
        }

        if (negativeButtonText != null && negativeButtonClickListener != null) {
            builder.setNegativeButton(negativeButtonText, negativeButtonClickListener);
        }

        return builder.create();
    }
}
