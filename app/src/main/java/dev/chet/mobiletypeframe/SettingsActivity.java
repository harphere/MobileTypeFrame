package dev.chet.mobiletypeframe;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public final class SettingsActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(24), dp(32), dp(24), dp(24));
        page.setBackgroundColor(Color.rgb(20, 24, 33));

        TextView title = new TextView(this);
        title.setText("MobileTypeFrame");
        title.setTextSize(25);
        title.setTextColor(Color.WHITE);
        page.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Choose your network icon style");
        subtitle.setTextSize(15);
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setPadding(0, dp(10), 0, dp(18));
        page.addView(subtitle);

        ImageView preview = new ImageView(this);
        LinearLayout.LayoutParams previewParams = new LinearLayout.LayoutParams(dp(130), dp(100));
        previewParams.gravity = Gravity.CENTER_HORIZONTAL;
        page.addView(preview, previewParams);

        RadioGroup group = new RadioGroup(this);
        group.setOrientation(RadioGroup.VERTICAL);
        final String[] shapes = { ShapeProvider.SQUARE, ShapeProvider.WAVES,
                ShapeProvider.OPEN_CORNERS, ShapeProvider.SIDE_WAVES };
        final String[] names = { "Square", "Waves", "Open Corners", "Side Waves" };
        String current = getSharedPreferences(ShapeProvider.PREFS, 0)
                .getString("shape", ShapeProvider.SQUARE);
        // Migrate the removed Circle choice so the picker always has a selection.
        if (!ShapeProvider.SQUARE.equals(current) && !ShapeProvider.WAVES.equals(current)
                && !ShapeProvider.OPEN_CORNERS.equals(current)
                && !ShapeProvider.SIDE_WAVES.equals(current)) {
            current = ShapeProvider.SQUARE;
            getSharedPreferences(ShapeProvider.PREFS, 0).edit()
                    .putString("shape", current).apply();
        }
        for (int i = 0; i < shapes.length; i++) {
            RadioButton option = new RadioButton(this);
            option.setId(View.generateViewId());
            option.setText(names[i]);
            option.setTextSize(18);
            option.setTextColor(Color.WHITE);
            option.setTag(shapes[i]);
            option.setPadding(0, dp(10), 0, dp(10));
            group.addView(option);
            if (shapes[i].equals(current)) option.setChecked(true);
        }
        page.addView(group);
        showPreview(preview, current);
        group.setOnCheckedChangeListener((buttons, checkedId) -> {
            RadioButton selected = buttons.findViewById(checkedId);
            if (selected == null) return;
            String shape = (String) selected.getTag();
            getSharedPreferences(ShapeProvider.PREFS, 0).edit().putString("shape", shape).apply();
            showPreview(preview, shape);
        });

        TextView hint = new TextView(this);
        hint.setText("Reboot after changing the shape to refresh the status bar.");
        hint.setTextColor(Color.LTGRAY);
        hint.setTextSize(14);
        hint.setPadding(0, dp(24), 0, 0);
        page.addView(hint);
        setContentView(page);
    }

    private void showPreview(ImageView preview, String shape) {
        Drawable drawable = new FramedTypeDrawable("5G", getResources().getDisplayMetrics().density, shape);
        preview.setImageDrawable(drawable);
        preview.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
    private int dp(float value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}
