package id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PokemonContentProvider extends ContentProvider {
    public static final String AUTHORITY = "id.ac.ui.cs.mobileprogramming.ivanairenethomas.pokeface";

    public static final Uri URI_POKEMONS = Uri.parse(
            "content://" + AUTHORITY + "/pokemons");

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_POKEMON_DIR = 1;
    private static final int CODE_POKEMON_ITEM = 2;

    static {
        MATCHER.addURI(AUTHORITY, "pokemons", CODE_POKEMON_DIR);
        MATCHER.addURI(AUTHORITY, "pokemons/*", CODE_POKEMON_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if (code == CODE_POKEMON_DIR || code == CODE_POKEMON_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            PokemonDao pokemonDao = PokefaceApp.getInstance().getDatabase().pokemonDao();
            final Cursor cursor;
            if (code == CODE_POKEMON_DIR) {
                cursor = pokemonDao.selectAllWithCursor();
            } else {
                cursor = pokemonDao.selectByNameWithCursor(String.valueOf(ContentUris.parseId(uri)));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_POKEMON_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + "pokemons";
            case CODE_POKEMON_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + "pokemons";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)) {
            case CODE_POKEMON_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                return ContentUris.withAppendedId(uri, CODE_POKEMON_DIR);
            case CODE_POKEMON_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_POKEMON_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_POKEMON_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_POKEMON_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_POKEMON_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
