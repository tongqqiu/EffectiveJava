package org.tongqing.effective;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tongqingqiu on 2/1/14.
 */
public class HeterogeneousTypesafeContainer {
    private Map<Class<?>, Object> favorites =
            new HashMap<Class<?>, Object>();

    public <T> void putFavorites(Class<T> type, T instance) {
        if(type == null)
            throw new NullPointerException("Type is null");
        favorites.put(type, type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));       // dynamic cast
    }

    public static void main(String[] args) {
        HeterogeneousTypesafeContainer f = new HeterogeneousTypesafeContainer();
        f.putFavorites(String.class, "Java");
        f.putFavorites(Integer.class, 1);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);

        System.out.printf("%s %x", favoriteString, favoriteInteger);
    }


}
