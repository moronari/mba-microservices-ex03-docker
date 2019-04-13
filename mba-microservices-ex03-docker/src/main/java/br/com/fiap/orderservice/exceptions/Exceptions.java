package br.com.fiap.orderservice.exceptions;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Exceptions {

    public static String generateMessageNotFound(String entity, Map<String, String> searchParams) {
        return String.format("%s Não foi encontrado nenhum parametro %s",
                StringUtils.capitalize(entity), searchParams);
    }
    public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, String... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Entrada invalidas");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}

