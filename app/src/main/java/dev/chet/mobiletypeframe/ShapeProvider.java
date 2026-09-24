package dev.chet.mobiletypeframe;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

/** Exposes only the selected shape; no private user data. */
public final class ShapeProvider extends ContentProvider {
    public static final String SQUARE = "square";
    public static final String WAVES = "waves";
    public static final String OPEN_CORNERS = "open_corners";
    public static final String SIDE_WAVES = "side_waves";
    static final String PREFS = "mobile_type_frame";

    @Override public boolean onCreate() { return true; }

    @Override public Bundle call(String method, String arg, Bundle extras) {
        if (!"getShape".equals(method)) return null;
        Bundle result = new Bundle();
        String shape = getContext().getSharedPreferences(PREFS, 0).getString("shape", SQUARE);
        if (!SQUARE.equals(shape) && !WAVES.equals(shape)
                && !OPEN_CORNERS.equals(shape) && !SIDE_WAVES.equals(shape)) {
            shape = SQUARE;
        }
        result.putString("shape", shape);
        return result;
    }

    @Override public Cursor query(Uri uri, String[] projection, String selection,
                                   String[] selectionArgs, String sortOrder) { return null; }
    @Override public String getType(Uri uri) { return null; }
    @Override public Uri insert(Uri uri, ContentValues values) { return null; }
    @Override public int delete(Uri uri, String selection, String[] selectionArgs) { return 0; }
    @Override public int update(Uri uri, ContentValues values, String selection,
                                String[] selectionArgs) { return 0; }
}
